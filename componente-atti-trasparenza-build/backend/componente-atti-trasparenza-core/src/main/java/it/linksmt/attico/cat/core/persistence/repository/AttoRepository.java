/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.core.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.linksmt.attico.cat.core.persistence.entity.Atto;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
@Repository
public interface AttoRepository extends CrudRepository<Atto, Long> {

	Page<Atto> findAll(Pageable pageRequest);

	Atto findById(long attoId);

	Atto findByAttoId(long attoId);
	
	@Query("select atto from Atto atto where atto.dataFinePubblicazione IS NULL OR DATE(atto.dataFinePubblicazione) >= DATE(NOW())")
	Page<Atto> findAllPubblicati(Pageable pageRequest);

}