/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.linksmt.attico.cat.core.converter.AllegatoConverter;
import it.linksmt.attico.cat.core.dto.AllegatoDto;
import it.linksmt.attico.cat.core.exception.ConverterException;
import it.linksmt.attico.cat.core.exception.ServiceException;
import it.linksmt.attico.cat.core.persistence.entity.Allegato;
import it.linksmt.attico.cat.core.persistence.repository.AllegatoRepository;
import it.linksmt.attico.cat.core.service.AllegatoService;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service
public class AllegatoServiceImpl implements AllegatoService {

	@Autowired
	private AllegatoRepository allegatoRepository;

	@Autowired
	private AllegatoConverter allegatoConverter;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.attico.cat.core.service.AllegatoService#getAllegatoById(long)
	 */
	@Override
	public AllegatoDto getAllegatoById(long allegatoId) throws ServiceException {

		AllegatoDto allegatoDto = null;

		try {
			Allegato findOne = allegatoRepository.findOne(allegatoId);
			if (findOne != null) {
				allegatoDto = allegatoConverter.toDto(findOne);
			}
		}
		catch (ConverterException e) {
			throw new ServiceException("Impossibile caricare l'allegati: " + e.getMessage(), e);
		}

		return allegatoDto;
	}

}
