/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.core.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.linksmt.attico.cat.core.persistence.entity.TipoAtto;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
@Repository
public interface TipoAttoRepository extends CrudRepository<TipoAtto, Long> {

	TipoAtto findByCodice(String codice);
}