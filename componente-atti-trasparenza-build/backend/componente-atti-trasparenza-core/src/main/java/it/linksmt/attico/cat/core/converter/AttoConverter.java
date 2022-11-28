/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.core.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import it.linksmt.attico.cat.core.dto.AllegatoDto;
import it.linksmt.attico.cat.core.dto.AttoDto;
import it.linksmt.attico.cat.core.dto.ParereDto;
import it.linksmt.attico.cat.core.dto.TipoAttoDto;
import it.linksmt.attico.cat.core.dto.VotazioniDto;
import it.linksmt.attico.cat.core.exception.ConverterException;
import it.linksmt.attico.cat.core.persistence.entity.Allegato;
import it.linksmt.attico.cat.core.persistence.entity.Atto;
import it.linksmt.attico.cat.core.persistence.entity.Parere;
import it.linksmt.attico.cat.core.persistence.entity.TipoAtto;
import it.linksmt.attico.cat.core.util.StatoAnnullamentoEnum;

/**
 * @author Gianluca Pindinelli
 *
 */
@Component
public class AttoConverter {
	
	@Value("${atto.allegati.relative.path}")
	private String allegatiPath;

	/**
	 * @param atto
	 * @return
	 * @throws ConverterException
	 */
	public AttoDto toDto(Atto atto) throws ConverterException {

		AttoDto attoDto = null;

		try {
			if (atto != null) {
				attoDto = new AttoDto();
				boolean scaduto = atto.getScaduto();
				boolean revocato = false;
				if(atto.getStatoAnnullamento() != null) {
					revocato = atto.getStatoAnnullamento().equalsIgnoreCase(StatoAnnullamentoEnum.REVOCATO.getCodice());
				}
				boolean visibilitaDocumenti = true;
				if(atto.getVisibilitaDocumenti() != null && !atto.getVisibilitaDocumenti().booleanValue()) {
					visibilitaDocumenti=false;
				}
					
				attoDto.setScaduto(scaduto);
				attoDto.setDataAdozione(atto.getDataAdozione());
				attoDto.setDataEsecutivita(atto.getDataEsecutivita());
				attoDto.setDataPubblicazione(atto.getDataPubblicazione());
				attoDto.setDataFinePubblicazione(atto.getDataFinePubblicazione());
				attoDto.setDataApprovazione(atto.getDataApprovazione());
				attoDto.setDataPresentazione(atto.getDataPresentazione());
				attoDto.setDataScadenza(atto.getDataScadenza());
				attoDto.setNumeroAdozione(atto.getNumeroAdozione());
				attoDto.setOggetto(atto.getOggetto());
				TipoAtto tipoAtto = atto.getTipoAtto();
				if (tipoAtto != null) {
					TipoAttoDto tipoAttoDto = new TipoAttoDto();
					tipoAttoDto.setCodice(tipoAtto.getCodice());
					tipoAttoDto.setId(tipoAtto.getId());
					tipoAttoDto.setNome(tipoAtto.getNome());
					attoDto.setTipoAttoDto(tipoAttoDto);
				}
				attoDto.setUfficio(atto.getUfficio());
				attoDto.setStato(atto.getStato());
				attoDto.setRiservato(atto.getRiservato());
				
				// Stato Annullamento
				if(atto.getDescrizioneStatoAnnullamento()!=null && !atto.getDescrizioneStatoAnnullamento().isEmpty()) {
					attoDto.setStatoAnnullamento(atto.getDescrizioneStatoAnnullamento());
				}else if ((atto.getStatoAnnullamento() != null) && 
					(atto.getStatoAnnullamento().trim().length() > 0)) {
					attoDto.setStatoAnnullamento(StatoAnnullamentoEnum.getDescrizione(
							atto.getStatoAnnullamento().trim()));
				}
				attoDto.setDataAttoAnnullamento(atto.getDataAttoAnnullamento());
				attoDto.setNumeroAttoAnnullamento(atto.getNumeroAttoAnnullamento());
				attoDto.setTipoAttoAnnullamento(atto.getTipoAttoAnnullamento());
				
				if(!scaduto) {
					attoDto.setId(atto.getId());
					attoDto.setRelatore(atto.getRelatore());
					attoDto.setEsito(atto.getEsito());
					attoDto.setAssessore(atto.getAssessore());
					attoDto.setProponente(atto.getProponente());
					if(!revocato) {
						attoDto.setAttoId(atto.getAttoId());
					}
	
					if(!revocato && visibilitaDocumenti) {
						List<Allegato> allegati = atto.getAllegati();
						boolean isRiservato = atto.getRiservato() != null && Boolean.TRUE.equals(atto.getRiservato());
						if (allegati != null && !isRiservato) {
							List<AllegatoDto> allegatiDto = new ArrayList<>();
							for (Allegato allegato : allegati) {
								AllegatoDto allegatoDto = new AllegatoDto();
								allegatoDto.setDescrizione(allegato.getDescrizione());
								allegatoDto.setId(allegato.getId());
								allegatoDto.setLink(allegatiPath + allegato.getLink());
								allegatoDto.setNome(allegato.getNome());
								allegatoDto.setTitolo(allegato.getTitolo());
								allegatoDto.setPrincipale(allegato.isPrincipale());
								allegatoDto.setRiservato(allegato.isRiservato()!=null && allegato.isRiservato());
								allegatoDto.setContentType(allegato.getContentType());
								allegatiDto.add(allegatoDto);
							}
							attoDto.setAllegati(allegatiDto);
						}
					}
					
					List<Parere> pareri = atto.getPareri();
					if (pareri != null && (pareri.size() > 0)) {
						List<ParereDto> pareriDto = new ArrayList<>();
						for(Parere parere : pareri) {
							ParereDto parereDto = new ParereDto();
							parereDto.setId(parere.getId());
							parereDto.setNome(parere.getNome());
							parereDto.setTesto(parere.getTesto());
							parereDto.setDataInvio(parere.getDataInvio());
							parereDto.setDataParere(parere.getDataParere());
							parereDto.setDataScadenza(parere.getDataScadenza());
							pareriDto.add(parereDto);
						}
						attoDto.setPareri(pareriDto);
					}
					
					attoDto.setVotazioni(buildVotazioni(atto));
				}
			}
		}
		catch (Exception e) {
			throw new ConverterException("Impossibile convertire l'oggetto : " + e.getMessage(), e);
		}

		return attoDto;

	}
	
	
	private VotazioniDto buildVotazioni(Atto atto) {
		if (notValid(atto.getNumPresenti()) && notValid(atto.getNumAstenuti()) && notValid(atto.getNomiAstenuti()) && 
			notValid(atto.getNumContrari()) && notValid(atto.getNomiContrari()) && notValid(atto.getNumFavorevoli()) && 
			notValid(atto.getNomiFavorevoli()) && notValid(atto.getNumNpv()) && notValid(atto.getNomiNpv())) {
			return null;
		}
		
		VotazioniDto votazioni = new VotazioniDto();
		votazioni.setNumPresenti(atto.getNumPresenti());
		
		votazioni.setNumAstenuti(atto.getNumAstenuti());
		votazioni.setNomiAstenuti(atto.getNomiAstenuti());
		
		votazioni.setNumContrari(atto.getNumContrari());
		votazioni.setNomiContrari(atto.getNomiContrari());
		
		votazioni.setNumFavorevoli(atto.getNumFavorevoli());
		votazioni.setNomiFavorevoli(atto.getNomiFavorevoli());
		
		votazioni.setNumNpv(atto.getNumNpv());
		votazioni.setNomiNpv(atto.getNomiNpv());
		
		return votazioni;
	}
	
	private boolean notValid(String val) {
		return (val == null) || (val.trim().length() == 0);
	}
	
	private boolean notValid(Integer val) {
		return (val == null) || (val.intValue() < 1);
	}
	

	/**
	 *
	 * @param dto
	 * @return
	 * @throws ConverterException
	 */
	public Atto fromDto(AttoDto dto) throws ConverterException {

		Atto atto = null;

		try {
			if (dto != null) {
				atto = new Atto();
				atto.setDataAdozione(dto.getDataAdozione());
				atto.setDataEsecutivita(dto.getDataEsecutivita());
				atto.setDataPubblicazione(dto.getDataPubblicazione());
				atto.setDataFinePubblicazione(dto.getDataFinePubblicazione());
				atto.setDataApprovazione(dto.getDataApprovazione());
				atto.setDataPresentazione(dto.getDataPresentazione());
				atto.setDataScadenza(dto.getDataScadenza());
				atto.setId(dto.getId());
				atto.setNumeroAdozione(dto.getNumeroAdozione());
				atto.setOggetto(dto.getOggetto());
				TipoAttoDto tipoAttoDto = dto.getTipoAttoDto();
				if (tipoAttoDto != null) {
					TipoAtto tipoAtto = new TipoAtto();
					tipoAtto.setCodice(tipoAttoDto.getCodice());
					tipoAtto.setId(tipoAttoDto.getId());
					tipoAtto.setNome(tipoAttoDto.getNome());
					atto.setTipoAtto(tipoAtto);
				}
				atto.setUfficio(dto.getUfficio());
				atto.setRelatore(dto.getRelatore());
				atto.setStato(dto.getStato());
				atto.setEsito(dto.getEsito());
				atto.setAssessore(dto.getAssessore());
				atto.setProponente(dto.getProponente());
				atto.setRiservato(dto.getRiservato());
				atto.setAttoId(dto.getAttoId());
				atto.setVisibilitaDocumenti(dto.getVisibilitaDocumenti());
				if(dto.getNumeroAttoAnnullamento()!=null) {
					atto.setDataAttoAnnullamento(dto.getDataAttoAnnullamento());
					atto.setNumeroAttoAnnullamento(dto.getNumeroAttoAnnullamento());
					atto.setTipoAttoAnnullamento(dto.getTipoAttoDto().getCodice());
					atto.setStatoAnnullamento(dto.getStatoAnnullamento());
					atto.setDescrizioneStatoAnnullamento(dto.getDescrizioneStatoAnnullamento());
				}

				List<AllegatoDto> allegatiDto = dto.getAllegati();
				if (allegatiDto != null) {
					List<Allegato> allegati = new ArrayList<>();
					for (AllegatoDto allegatoDto : allegatiDto) {
						Allegato allegato = new Allegato();
						if (allegatoDto.getId() > 0) {
							allegato.setId(allegatoDto.getId());
						}
						allegato.setDescrizione(allegatoDto.getDescrizione());
						allegato.setLink(allegatoDto.getLink());
						allegato.setNome(allegatoDto.getNome());
						allegato.setTitolo(allegatoDto.getTitolo());
						allegato.setPrincipale(allegatoDto.isPrincipale());
						allegato.setContentType(allegatoDto.getContentType());
						allegato.setRiservato(allegatoDto.isRiservato());
						allegati.add(allegato);
					}
					atto.setAllegati(allegati);
				}
			
				// Pareri
				List<ParereDto> pareriDto = dto.getPareri();
				if (pareriDto != null) {
					List<Parere> pareri = new ArrayList<>();
					for (ParereDto parereDto : pareriDto) {
						Parere parere = new Parere();
						if (parereDto.getId() > 0) {
							parere.setId(parereDto.getId());
						}
						parere.setOrigine(parereDto.getOrigine());
						parere.setNome(parereDto.getNome());
						parere.setTesto(parereDto.getTesto());
						parere.setDataInvio(parereDto.getDataInvio());
						parere.setDataParere(parereDto.getDataParere());
						parere.setDataScadenza(parereDto.getDataScadenza());
						pareri.add(parere);
					}
					atto.setPareri(pareri);
				}
				
				// Votazioni
				VotazioniDto votazioni = dto.getVotazioni();
				if (votazioni != null) {
					atto.setNumPresenti(votazioni.getNumPresenti());
					
					atto.setNumAstenuti(votazioni.getNumAstenuti());
					atto.setNomiAstenuti(votazioni.getNomiAstenuti());
					
					atto.setNumContrari(votazioni.getNumContrari());
					atto.setNomiContrari(votazioni.getNomiContrari());
					
					atto.setNumFavorevoli(votazioni.getNumFavorevoli());
					atto.setNomiFavorevoli(votazioni.getNomiFavorevoli());
					
					atto.setNumNpv(votazioni.getNumNpv());
					atto.setNomiNpv(votazioni.getNomiNpv());
				}
			}
		}
		catch (Exception e) {
			throw new ConverterException("Impossibile convertire l'oggetto : " + e.getMessage(), e);
		}

		return atto;
	}

}
