/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.core.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.linksmt.attico.cat.core.dto.AllegatoDto;
import it.linksmt.attico.cat.core.dto.AttoDto;
import it.linksmt.attico.cat.core.exception.ConverterException;
import it.linksmt.attico.cat.core.persistence.entity.Allegato;
import it.linksmt.attico.cat.core.persistence.entity.Atto;

/**
 * @author Gianluca Pindinelli
 *
 */
@Component
public class AllegatoConverter {

	@Autowired
	private AttoConverter attoConverter;

	/**
	 * @param atto
	 * @return
	 * @throws ConverterException
	 */
	public AllegatoDto toDto(Allegato allegato) throws ConverterException {

		AllegatoDto allegatoDto = null;

		try {
			if (allegato != null) {
				allegatoDto = new AllegatoDto();

				allegatoDto.setDescrizione(allegato.getDescrizione());
				allegatoDto.setId(allegato.getId());
				allegatoDto.setLink(allegato.getLink());
				allegatoDto.setNome(allegato.getNome());
				allegatoDto.setPrincipale(allegato.isPrincipale());
				allegatoDto.setContentType(allegato.getContentType());
				allegatoDto.setTitolo(allegato.getTitolo());
				Atto atto = allegato.getAtto();
				if (atto != null) {
					allegatoDto.setAtto(attoConverter.toDto(atto));
				}
			}
		}
		catch (Exception e) {
			throw new ConverterException("Impossibile convertire l'oggetto : " + e.getMessage(), e);
		}

		return allegatoDto;

	}

	/**
	 *
	 * @param dto
	 * @return
	 * @throws ConverterException
	 */
	public Allegato fromDto(AllegatoDto dto) throws ConverterException {

		Allegato allegato = null;

		try {
			if (dto != null) {
				allegato = new Allegato();
				allegato.setTitolo(dto.getTitolo());
				allegato.setDescrizione(dto.getDescrizione());
				allegato.setId(dto.getId());
				allegato.setLink(dto.getLink());
				allegato.setNome(dto.getNome());
				allegato.setPrincipale(dto.isPrincipale());
				allegato.setContentType(dto.getContentType());

				AttoDto atto = dto.getAtto();
				if (atto != null) {
					allegato.setAtto(attoConverter.fromDto(atto));
				}
			}
		}
		catch (Exception e) {
			throw new ConverterException("Impossibile convertire l'oggetto : " + e.getMessage(), e);
		}

		return allegato;
	}

}
