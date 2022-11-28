/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.core.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import it.linksmt.attico.cat.core.dto.SearchDto;
import it.linksmt.attico.cat.core.persistence.entity.Atto;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public interface AttoRepositoryCustom {

	/**
	 *
	 * @param searchDto
	 * @param pageRequest
	 * @return
	 */
	int count(SearchDto searchDto, Pageable pageRequest);
	
	/**
	 *
	 * @param searchDto
	 * @param pageRequest
	 * @return
	 */
	List<Atto> search(SearchDto searchDto, Pageable pageRequest);

	/**
	 *
	 * @param columnName
	 * @param condition
	 * @return
	 */
	List<String> getSelectValues(String columnName, String condition);

	/**
	 *
	 * @param tipiAtto
	 * @return
	 */
	List<String> getCommissioni(List<String> tipiAtto);

	/**
	 * 
	 * @param tipo
	 * @param numero
	 * @param anno
	 * @return
	 */
	Atto getAttoByTipoNumeroAnno(String tipo, String numero, Integer anno);
	
	/**
	 * 
	 * @param tipo
	 * @param numero
	 * @param anno
	 * @return
	 */
	List<Atto> getAttoByTipoNumeroAnnullamentoAnno(String tipo, String numero, Integer anno);

}