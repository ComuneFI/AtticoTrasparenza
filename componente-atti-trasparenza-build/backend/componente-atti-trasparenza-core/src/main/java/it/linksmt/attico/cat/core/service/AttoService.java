/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.core.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import it.linksmt.attico.cat.core.dto.AttoDto;
import it.linksmt.attico.cat.core.dto.SearchDto;
import it.linksmt.attico.cat.core.exception.ServiceException;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface AttoService {

	/**
	 * Ritorna tutti gli atti presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceException
	 */
	List<AttoDto> getAllAtti(Pageable pageRequest) throws ServiceException;

	/**
	 * Ritorna l'atto in base all'id passato in input.
	 *
	 * @param idAtto
	 * @return
	 * @throws ServiceException
	 */
	AttoDto getAtto(long idAtto) throws ServiceException;

	/**
	 * Ritorna gli atti in base ai criteri di ricerca passati in input.
	 *
	 * @param searchDto
	 * @param pageRequest
	 * @return
	 * @throws ServiceException
	 */

	List<AttoDto> searchAtti(SearchDto searchDto, Pageable pageRequest) throws ServiceException;
	
	/**
	 * Ritorna gli atti in base ai criteri di ricerca passati in input.
	 *
	 * @param searchDto
	 * @param pageRequest
	 * @return
	 * @throws ServiceException
	 */

	int countAtti(SearchDto searchDto, Pageable pageRequest) throws ServiceException;

	/**
	 *
	 * @param tipiAtto
	 * @return
	 * @throws ServiceException
	 */
	List<String> getUffici(List<String> tipiAtto) throws ServiceException;

	/**
	 *
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	List<String> getRelatori(List<String> list) throws ServiceException;

	/**
	 *
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	List<String> getStati(List<String> list) throws ServiceException;

	/**
	 *
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	List<String> getEsiti(List<String> list) throws ServiceException;

	/**
	 *
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	List<String> getAssessori(List<String> list) throws ServiceException;

	/**
	 *
	 * @return
	 * @throws ServiceException
	 */
	List<String> getCommissioni(List<String> list) throws ServiceException;

	/**
	 * @return
	 */
	List<String> getSottotipi() throws ServiceException;

	/**
	 *
	 * @param attoDto
	 * @throws ServiceException
	 */
	void saveAtto(AttoDto attoDto) throws ServiceException;

	/**
	 * 
	 * @param tipo
	 * @param numero
	 * @param anno
	 * @return
	 */
	AttoDto getAttoByTipoNumeroAnno(String tipo, String numero, Integer anno);

	/**
	 * 
	 * @param idAtto
	 * @return
	 * @throws ServiceException
	 */
	AttoDto getAttoByAttoId(long idAtto) throws ServiceException;

}
