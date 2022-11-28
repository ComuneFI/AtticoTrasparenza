/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.linksmt.attico.cat.core.converter.AttoConverter;
import it.linksmt.attico.cat.core.dto.AttoDto;
import it.linksmt.attico.cat.core.dto.SearchDto;
import it.linksmt.attico.cat.core.exception.ConverterException;
import it.linksmt.attico.cat.core.exception.ServiceException;
import it.linksmt.attico.cat.core.persistence.entity.Allegato;
import it.linksmt.attico.cat.core.persistence.entity.Atto;
import it.linksmt.attico.cat.core.persistence.entity.Parere;
import it.linksmt.attico.cat.core.persistence.entity.TipoAtto;
import it.linksmt.attico.cat.core.persistence.repository.AllegatoRepository;
import it.linksmt.attico.cat.core.persistence.repository.AttoRepository;
import it.linksmt.attico.cat.core.persistence.repository.AttoRepositoryCustom;
import it.linksmt.attico.cat.core.persistence.repository.ParereRepository;
import it.linksmt.attico.cat.core.persistence.repository.TipoAttoRepository;
import it.linksmt.attico.cat.core.service.AttoService;
import it.linksmt.attico.cat.core.util.StatoAnnullamentoEnum;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service
@Transactional
public class AttoServiceImpl implements AttoService {

	@Autowired
	private AttoRepository attoRepository;

	@Autowired
	private AttoRepositoryCustom attoRepositoryCustom;

	@Autowired
	private AttoConverter attoConverter;

	@Autowired
	private AllegatoRepository allegatoRepository;

	@Autowired
	private ParereRepository parereRepository;

	@Autowired
	private TipoAttoRepository tipoAttoRepository;

	private final Logger log = LoggerFactory.getLogger(AttoServiceImpl.class.getName());
	
	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AttoService#getAttoByTipoNumeroAnno()
	 */
	@Override
	public AttoDto getAttoByTipoNumeroAnno(String tipo, String numero, Integer anno) {		
		AttoDto dto = null;
		try {			
			Atto atto = attoRepositoryCustom.getAttoByTipoNumeroAnno(tipo, numero, anno);
			dto = attoConverter.toDto(atto);
			SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
			if(dto.getTipoAttoDto()!=null && dto.getTipoAttoDto().getCodice()!=null && !dto.getTipoAttoDto().getCodice().isEmpty()) {
				String annoAdozione = getYearFormat.format(dto.getDataAdozione());
	        	List<Atto>  attoCollegato = attoRepositoryCustom.getAttoByTipoNumeroAnnullamentoAnno(dto.getTipoAttoDto().getCodice(), dto.getNumeroAdozione(), new Integer(annoAdozione));
				if(attoCollegato!=null && attoCollegato.size()>0) {
					dto.setTipoAttoCollegamento(attoCollegato.get(0).getTipoAttoAnnullamento());
					dto.setDataAttoCollegamento(attoCollegato.get(0).getDataAttoAnnullamento());
					dto.setNumeroAttoCollegamento(attoCollegato.get(0).getNumeroAdozione());
				}
	        }
		} catch (ConverterException e) {
			throw new ServiceException("Impossibile caricare l'atto: " + e.getMessage(), e);
		}
		
		return dto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AttoService#getAllAtti()
	 */
	@Override
	public List<AttoDto> getAllAtti(Pageable pageRequest) throws ServiceException {

		List<AttoDto> dtos = null;

		Page<Atto> findAll = attoRepository.findAll(pageRequest);

		try {
			if (findAll != null) {
				dtos = new ArrayList<>();
				for (Atto atto : findAll) {
					AttoDto dto = attoConverter.toDto(atto);
					dtos.add(dto);
				}
			}
		}
		catch (ConverterException e) {
			throw new ServiceException("Impossibile caricare la lista degli atti: " + e.getMessage(), e);
		}

		return dtos;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AttoService#getAtto(long)
	 */
	@Override
	public AttoDto getAtto(long idAtto) throws ServiceException {

		AttoDto dto = null;

		Atto findById = attoRepository.findById(idAtto);

		try {
			if (findById != null) {
				dto = attoConverter.toDto(findById);
			}
		}
		catch (ConverterException e) {
			throw new ServiceException("Impossibile caricare l'atto: " + e.getMessage(), e);
		}

		return dto;
	}
	
	
	@Override
	public AttoDto getAttoByAttoId(long idAtto) throws ServiceException {

		AttoDto dto = null;

		Atto findById = attoRepository.findByAttoId(idAtto);

		try {
			if (findById != null) {
				dto = attoConverter.toDto(findById);
				SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
				if(dto.getTipoAttoDto()!=null && dto.getTipoAttoDto().getCodice()!=null && !dto.getTipoAttoDto().getCodice().isEmpty()) {
					String annoAdozione = getYearFormat.format(dto.getDataAdozione());
		        	List<Atto>  attoCollegato = attoRepositoryCustom.getAttoByTipoNumeroAnnullamentoAnno(dto.getTipoAttoDto().getCodice(), dto.getNumeroAdozione(), new Integer(annoAdozione));
					if(attoCollegato!=null && attoCollegato.size()>0) {
						dto.setTipoAttoCollegamento(attoCollegato.get(0).getTipoAttoAnnullamento());
						dto.setDataAttoCollegamento(attoCollegato.get(0).getDataAttoAnnullamento());
						dto.setNumeroAttoCollegamento(attoCollegato.get(0).getNumeroAdozione());
					}
		        }
			}
		}
		catch (ConverterException e) {
			throw new ServiceException("Impossibile caricare l'atto: " + e.getMessage(), e);
		}

		return dto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.linksmt.attico.cat.core.service.AttoService#searchAtti(it.linksmt.attico.cat.core.dto.
	 * SearchDto, org.springframework.data.domain.Pageable)
	 */
	@Override
	public List<AttoDto> searchAtti(SearchDto searchDto, Pageable pageRequest) throws ServiceException {

		List<AttoDto> dtos = null;

		List<Atto> search = attoRepositoryCustom.search(searchDto, pageRequest);

		try {
			if (search != null) {
				dtos = new ArrayList<>();
				SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
				for (Atto atto : search) {
					AttoDto dto = attoConverter.toDto(atto);
					//cerco eventuale atto modificato dall'atto
			        String annoAdozione = getYearFormat.format(dto.getDataAdozione());
			        if(dto.getTipoAttoDto()!=null && dto.getTipoAttoDto().getCodice()!=null && !dto.getTipoAttoDto().getCodice().isEmpty()) {
			        	List<Atto>  attoCollegato = attoRepositoryCustom.getAttoByTipoNumeroAnnullamentoAnno(dto.getTipoAttoDto().getCodice(), dto.getNumeroAdozione(), new Integer(annoAdozione));
						if(attoCollegato!=null && attoCollegato.size()>0) {
							dto.setTipoAttoCollegamento(attoCollegato.get(0).getTipoAttoAnnullamento());
							dto.setDataAttoCollegamento(attoCollegato.get(0).getDataAttoAnnullamento());
							dto.setNumeroAttoCollegamento(attoCollegato.get(0).getNumeroAdozione());
						}
			        }
					dtos.add(dto);
				}
			}
		}
		catch (ConverterException e) {
			throw new ServiceException("Impossibile caricare la lista degli atti: " + e.getMessage(), e);
		}

		return dtos;
	}
	
	@Override
	public int countAtti(SearchDto searchDto, Pageable pageRequest) throws ServiceException {

		int count = attoRepositoryCustom.count(searchDto, pageRequest);

		return count;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AttoService#getUffici(java.util.List)
	 */
	@Override
	public List<String> getUffici(List<String> tipiAtto) throws ServiceException {
		return attoRepositoryCustom.getSelectValues("ufficio", "tipoAtto.codice in ('" + StringUtils.join(tipiAtto, "', '") + "')");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AttoService#getRelatori()
	 */
	@Override
	public List<String> getRelatori(List<String> tipiAtto) throws ServiceException {
		return attoRepositoryCustom.getSelectValues("relatore", "tipoAtto.codice in ('" + StringUtils.join(tipiAtto, "', '") + "')");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AttoService#getStati()
	 */
	@Override
	public List<String> getStati(List<String> tipiAtto) throws ServiceException {
		return attoRepositoryCustom.getSelectValues("stato", "tipoAtto.codice in ('" + StringUtils.join(tipiAtto, "', '") + "')");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AttoService#getEsiti()
	 */
	@Override
	public List<String> getEsiti(List<String> tipiAtto) throws ServiceException {
		return attoRepositoryCustom.getSelectValues("esito", "tipoAtto.codice in ('" + StringUtils.join(tipiAtto, "', '") + "')");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AttoService#getAssessori()
	 */
	@Override
	public List<String> getAssessori(List<String> tipiAtto) throws ServiceException {
		return attoRepositoryCustom.getSelectValues("assessore", "tipoAtto.codice in ('" + StringUtils.join(tipiAtto, "', '") + "')");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AttoService#getSottotipi()
	 */
	@Override
	public List<String> getSottotipi() throws ServiceException {
		return attoRepositoryCustom.getSelectValues("sottoTipo", null);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AttoService#saveAtto(it.linksmt.attico.cat.core.dto.
	 * AttoDto)
	 */
	@Override
	public void saveAtto(AttoDto attoDto) throws ServiceException {

		try {
			// Ricerca atto prima del salvataggio per eventuale update
			Atto fromDto = attoConverter.fromDto(attoDto);

			List<Allegato> allegatiToSave = fromDto.getAllegati();
			List<Parere> pareriToSave = fromDto.getPareri();

			Atto findByAttoId = attoRepository.findByAttoId(attoDto.getAttoId());
			if (findByAttoId != null) {

				findByAttoId.setAssessore(fromDto.getAssessore());
				findByAttoId.setDataAdozione(fromDto.getDataAdozione());
				findByAttoId.setDataApprovazione(fromDto.getDataApprovazione());
				findByAttoId.setDataEsecutivita(fromDto.getDataEsecutivita());
				findByAttoId.setDataPresentazione(fromDto.getDataPresentazione());
				findByAttoId.setDataPubblicazione(fromDto.getDataPubblicazione());
				findByAttoId.setDataFinePubblicazione(fromDto.getDataFinePubblicazione());
				findByAttoId.setDataScadenza(fromDto.getDataScadenza());
				findByAttoId.setEsito(fromDto.getEsito());
				findByAttoId.setNumeroAdozione(fromDto.getNumeroAdozione());
				findByAttoId.setOggetto(fromDto.getOggetto());
				findByAttoId.setProponente(fromDto.getProponente());
				findByAttoId.setRelatore(fromDto.getRelatore());
				findByAttoId.setSottoTipo(fromDto.getSottoTipo());
				findByAttoId.setStato(fromDto.getStato());
				findByAttoId.setUfficio(fromDto.getUfficio());
				findByAttoId.setRiservato(fromDto.getRiservato());
				if(fromDto.getStatoAnnullamento()!=null) {
					findByAttoId.setStatoAnnullamento(fromDto.getStatoAnnullamento());
				}
				findByAttoId.setVisibilitaDocumenti(fromDto.getVisibilitaDocumenti());

				// Votazioni
				findByAttoId.setNumPresenti(fromDto.getNumPresenti());

				findByAttoId.setNumAstenuti(fromDto.getNumAstenuti());
				findByAttoId.setNomiAstenuti(fromDto.getNomiAstenuti());

				findByAttoId.setNumContrari(fromDto.getNumContrari());
				findByAttoId.setNomiContrari(fromDto.getNomiContrari());

				findByAttoId.setNumFavorevoli(fromDto.getNumFavorevoli());
				findByAttoId.setNomiFavorevoli(fromDto.getNomiFavorevoli());

				findByAttoId.setNumNpv(fromDto.getNumNpv());
				findByAttoId.setNomiNpv(fromDto.getNomiNpv());
			}
			else {
				findByAttoId = fromDto;
			}

			// Tipo atto
			if (fromDto.getTipoAtto() != null) {
				TipoAtto tipoAtto = null;
				if (fromDto.getTipoAtto().getId() > 0) {
					tipoAtto = tipoAttoRepository.findOne(fromDto.getTipoAtto().getId());
				}
				else if (fromDto.getTipoAtto().getCodice() != null) {
					tipoAtto = tipoAttoRepository.findByCodice(fromDto.getTipoAtto().getCodice());
				}
				if (tipoAtto == null) {
					throw new ServiceException("Tipologia di atto non supportata.");
				}
				findByAttoId.setTipoAtto(tipoAtto);
			}

			// Atto Revocato
			if ((attoDto.getAttoRevocatoId() != null) && (attoDto.getAttoRevocatoId().longValue() > 0)) {

				Atto attoRevocato = attoRepository.findByAttoId(attoDto.getAttoRevocatoId().longValue());
				if (attoRevocato != null) {
					if(attoDto.getDescrizioneStatoAnnullamento()!=null && !attoDto.getDescrizioneStatoAnnullamento().isEmpty()) {
						attoRevocato.setDescrizioneStatoAnnullamento(attoDto.getDescrizioneStatoAnnullamento());
						attoRevocato.setStatoAnnullamento(attoDto.getStatoAnnullamento());
					}
					else if(attoDto.getTipoCollegamentoAtto()!=null&&!attoDto.getTipoCollegamentoAtto().isEmpty()) {
						String tipoCollegamento  = attoDto.getTipoCollegamentoAtto();
						switch (tipoCollegamento) {
						case "Modificato-Integrato":
							attoRevocato.setStatoAnnullamento(StatoAnnullamentoEnum.MODIFICATO.getCodice());
							break;
						default:
							attoRevocato.setStatoAnnullamento(StatoAnnullamentoEnum.REVOCATO.getCodice());
							break;
						}
						
						
					}else {
						attoRevocato.setStatoAnnullamento(StatoAnnullamentoEnum.REVOCATO.getCodice());
					}

					attoRevocato.setNumeroAttoAnnullamento(attoDto.getNumeroAdozione());
					attoRevocato.setDataAttoAnnullamento(attoDto.getDataAdozione());
					if (attoDto.getTipoAttoDto() != null) {
						attoRevocato.setTipoAttoAnnullamento(attoDto.getTipoAttoDto().getCodice());
					}
					attoRepository.save(attoRevocato);
				}
				else {
					log.warn("Impossibile impostare lo stato revocato per l'atto con id: " + attoDto.getAttoRevocatoId().longValue());
				}
			}

			// Pareri
			if (pareriToSave != null) {
				List<Parere> pareri = findByAttoId.getPareri();
				parereRepository.delete(pareri);
				findByAttoId.setPareri(null);
			}

			// Allegati
			if (allegatiToSave != null) {
				List<Allegato> allegati = findByAttoId.getAllegati();
				allegatoRepository.delete(allegati);
				findByAttoId.setAllegati(null);
			}

			Atto attoSaved = attoRepository.save(findByAttoId);
			attoDto.setId(attoSaved.getId());

			if (allegatiToSave != null) {
				for (Allegato allegato : allegatiToSave) {
					allegato.setAtto(attoSaved);
				}
				attoSaved.setAllegati(allegatiToSave);
			}

			if (pareriToSave != null) {
				for (Parere parere : pareriToSave) {
					parere.setAtto(attoSaved);
				}
				attoSaved.setPareri(pareriToSave);
			}
			
			attoSaved = attoRepository.save(attoSaved);
		}
		catch (Throwable e) {
			throw new ServiceException("Impossibile salvare l'atto con ID: " + attoDto.getAttoId() + " :: eccezione : " + e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AttoService#getCommissioni(java.util.List)
	 */
	@Override
	public List<String> getCommissioni(List<String> tipiAtto) throws ServiceException {
		return attoRepositoryCustom.getCommissioni(tipiAtto);
	}

}
