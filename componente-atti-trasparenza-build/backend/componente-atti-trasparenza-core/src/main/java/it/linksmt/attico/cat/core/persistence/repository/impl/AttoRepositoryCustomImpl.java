/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.core.persistence.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import it.linksmt.attico.cat.core.dto.SearchDto;
import it.linksmt.attico.cat.core.persistence.entity.Atto;
import it.linksmt.attico.cat.core.persistence.repository.AttoRepositoryCustom;

/**
 * @author Gianluca Pindinelli
 *
 */
@Repository
public class AttoRepositoryCustomImpl implements AttoRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Atto getAttoByTipoNumeroAnno(String tipo, String numero, Integer anno) {
		String query = "SELECT a.* FROM atto a JOIN tipo_atto ta ON ta.id = a.tipo_atto_id WHERE 1 = 1";
		
		query += " AND ta.codice = :tipo";
		query += " AND a.numero_adozione = :numero";
		query += " AND YEAR(a.data_adozione) = :anno";
		
		Query nativeQuery = entityManager.createNativeQuery(query, Atto.class);
		
		nativeQuery.setParameter("tipo", tipo);
		nativeQuery.setParameter("numero", numero);
		nativeQuery.setParameter("anno", anno);
		
		return (Atto) nativeQuery.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Atto> getAttoByTipoNumeroAnnullamentoAnno(String tipo, String numero, Integer anno) {
		String query = "SELECT a.* FROM atto a JOIN tipo_atto ta ON ta.id = a.tipo_atto_id WHERE 1 = 1";
		
		query += " AND ta.codice = :tipo";
		query += " AND a.numero_atto_annullamento = :numero";
		query += " AND YEAR(a.data_atto_annullamento) = :anno";
		
		Query nativeQuery = entityManager.createNativeQuery(query, Atto.class);
		
		nativeQuery.setParameter("tipo", tipo);
		nativeQuery.setParameter("numero", numero);
		nativeQuery.setParameter("anno", anno);
		
		return nativeQuery.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.linksmt.attico.cat.core.persistence.repository.AttoRepositoryCustom#search(it.linksmt.
	 * attico.cat. core.dto.SearchDto, org.springframework.data.domain.Pageable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Atto> search(SearchDto searchDto, Pageable pageRequest) {

		String query = "SELECT a.* FROM atto a JOIN tipo_atto ta ON ta.id = a.tipo_atto_id";
		
		if (searchDto.getCommissione() != null && !searchDto.getCommissione().isEmpty()) {
			query += " JOIN parere p on p.id_atto = a.id";
		}
		
		query += " WHERE 1 = 1";
		
		/*
		//aggiunto filtro implicito su fine pubblicazione
		query += " AND (a.data_fine_pubblicazione IS NULL OR DATE(a.data_fine_pubblicazione) >= DATE(NOW()))";
		*/
		
		//aggiunto filtro implicito su inizio pubblicazione
		query += " AND (a.data_pubblicazione IS NULL OR DATE(a.data_pubblicazione) <= DATE(NOW()))";
		

		if (searchDto.getCommissione() != null && !searchDto.getCommissione().isEmpty()) {
			query += " AND p.nome = :commissione";
		}
		
		if (searchDto.getOggetto() != null && !searchDto.getOggetto().isEmpty()) {
			query += " AND LOWER(a.oggetto) LIKE :oggetto";
		}

		if (searchDto.getUfficio() != null && !searchDto.getUfficio().isEmpty()) {
			query += " AND a.ufficio = :ufficio";
		}

		if (searchDto.getRelatore() != null && !searchDto.getRelatore().isEmpty()) {
			query += " AND a.relatore = :relatore";
		}

		if (searchDto.getNumeroAdozione() != null) {
			query += " AND a.numero_adozione = :numeroAdozione";
		}

		if (searchDto.getAnnoAdozione() != null) {
			query += " AND YEAR(a.data_adozione) = :annoAdozione";
		}

		if (searchDto.getMeseAdozione() != null) {
			query += " AND MONTH(a.data_adozione) = :meseAdozione";
		}

		if (searchDto.getAnnoApprovazione() != null) {
			query += " AND YEAR(a.data_approvazione) = :annoApprovazione";
		}

		if (searchDto.getMeseApprovazione() != null) {
			query += " AND MONTH(a.data_approvazione) = :meseApprovazione";
		}

		if (searchDto.getAnnoPresentazione() != null) {
			query += " AND YEAR(a.data_presentazione) = :annoPresentazione";
		}

		if (searchDto.getMesePresentazione() != null) {
			query += " AND MONTH(a.data_presentazione) = :mesePresentazione";
		}

		if (searchDto.getAnnoScadenza() != null) {
			query += " AND YEAR(a.data_scadenza) = :annoScadenza";
		}

		if (searchDto.getMeseScadenza() != null) {
			query += " AND MONTH(a.data_scadenza) = :meseScadenza";
		}

		if (searchDto.getTipiAtto() != null && !searchDto.getTipiAtto().isEmpty()) {
			query += " AND ta.codice in (:tipiAtto)";
		}

		if (searchDto.getStato() != null && !searchDto.getStato().isEmpty()) {
			query += " AND a.stato = :stato";
		}

		if (searchDto.getEsito() != null && !searchDto.getEsito().isEmpty()) {
			query += " AND a.esito = :esito";
		}

		if (searchDto.getAssessore() != null && !searchDto.getAssessore().isEmpty()) {
			query += " AND a.assessore = :assessore";
		}

		if (searchDto.getProponente() != null && !searchDto.getProponente().isEmpty()) {
			query += " AND LOWER(a.proponente) LIKE :proponente";
		}

		if (searchDto.getSottoTipo() != null && !searchDto.getSottoTipo().isEmpty()) {
			query += " AND a.sotto_tipo = :sottoTipo";
		}

		if (pageRequest != null) {
			Sort sort = pageRequest.getSort();
			if (sort != null) {
				query += " ORDER BY ";
				for (Iterator<Order> iterator = sort.iterator(); iterator.hasNext();) {
					Order order = iterator.next();
					query += order.getProperty() + " " + order.getDirection().name();
					if (iterator.hasNext()) {
						query += ",";
					}
				}
			}
		}

		Query nativeQuery = null;
		if (pageRequest != null) {
			nativeQuery = entityManager.createNativeQuery(query, Atto.class).setMaxResults(pageRequest.getPageSize()).setFirstResult(pageRequest.getPageNumber());
		}
		else {
			nativeQuery = entityManager.createNativeQuery(query, Atto.class);
		}

		if (searchDto.getOggetto() != null && !searchDto.getOggetto().isEmpty()) {
			nativeQuery.setParameter("oggetto", "%" + searchDto.getOggetto().toLowerCase() + "%");
		}

		if (searchDto.getUfficio() != null && !searchDto.getUfficio().isEmpty()) {
			nativeQuery.setParameter("ufficio", searchDto.getUfficio());
		}

		if (searchDto.getRelatore() != null && !searchDto.getRelatore().isEmpty()) {
			nativeQuery.setParameter("relatore", searchDto.getRelatore());
		}

		if (searchDto.getNumeroAdozione() != null) {
			nativeQuery.setParameter("numeroAdozione", searchDto.getNumeroAdozione());
		}

		if (searchDto.getAnnoAdozione() != null) {
			nativeQuery.setParameter("annoAdozione", searchDto.getAnnoAdozione());
		}

		if (searchDto.getMeseAdozione() != null) {
			nativeQuery.setParameter("meseAdozione", searchDto.getMeseAdozione());
		}

		if (searchDto.getAnnoApprovazione() != null) {
			nativeQuery.setParameter("annoApprovazione", searchDto.getAnnoApprovazione());
		}

		if (searchDto.getMeseApprovazione() != null) {
			nativeQuery.setParameter("meseApprovazione", searchDto.getMeseApprovazione());
		}

		if (searchDto.getAnnoPresentazione() != null) {
			nativeQuery.setParameter("annoPresentazione", searchDto.getAnnoPresentazione());
		}

		if (searchDto.getMesePresentazione() != null) {
			nativeQuery.setParameter("mesePresentazione", searchDto.getMesePresentazione());
		}

		if (searchDto.getAnnoScadenza() != null) {
			nativeQuery.setParameter("annoScadenza", searchDto.getAnnoScadenza());
		}

		if (searchDto.getMeseScadenza() != null) {
			nativeQuery.setParameter("meseScadenza", searchDto.getMeseScadenza());
		}

		if (searchDto.getTipiAtto() != null && !searchDto.getTipiAtto().isEmpty()) {
			List<String> tipiAttoVal = new ArrayList<String>();
			for (int i = 0; i < searchDto.getTipiAtto().size(); i++) {
				tipiAttoVal.add(searchDto.getTipiAtto().get(i));

			}
			nativeQuery.setParameter("tipiAtto", tipiAttoVal);
		}

		if (searchDto.getStato() != null && !searchDto.getStato().isEmpty()) {
			nativeQuery.setParameter("stato", searchDto.getStato());
		}

		if (searchDto.getEsito() != null && !searchDto.getEsito().isEmpty()) {
			nativeQuery.setParameter("esito", searchDto.getEsito());
		}

		if (searchDto.getAssessore() != null && !searchDto.getAssessore().isEmpty()) {
			nativeQuery.setParameter("assessore", searchDto.getAssessore());
		}

		if (searchDto.getCommissione() != null && !searchDto.getCommissione().isEmpty()) {
			nativeQuery.setParameter("commissione", searchDto.getCommissione());
		}

		if (searchDto.getProponente() != null && !searchDto.getProponente().isEmpty()) {
			nativeQuery.setParameter("proponente", "%" + searchDto.getProponente().toLowerCase() + "%");
		}

		if (searchDto.getSottoTipo() != null && !searchDto.getSottoTipo().isEmpty()) {
			nativeQuery.setParameter("sottoTipo", searchDto.getSottoTipo());
		}

		return nativeQuery.getResultList();
	}
	
	@Override
	public int count(SearchDto searchDto, Pageable pageRequest) {
		List<Atto> list = this.search(searchDto, pageRequest);
		return list!=null?list.size():0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.linksmt.attico.cat.core.persistence.repository.AttoRepositoryCustom#getSelectValues(java.
	 * lang. String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getSelectValues(String columnName, String condition) {

		String query = "SELECT distinct(a." + columnName + ") FROM Atto a WHERE 1=1";

		query += " AND a." + columnName + " IS NOT NULL ";

		if (condition != null) {
			query += " AND " + condition;
		}

		query += " ORDER BY a." + columnName + " ASC";

		Query nativeQuery = entityManager.createQuery(query);

		return nativeQuery.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.linksmt.attico.cat.core.persistence.repository.AttoRepositoryCustom#getCommissioni(java.
	 * util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCommissioni(List<String> tipiAtto) {

		String query = "SELECT distinct(p.nome) FROM Parere p WHERE 1=1 and upper(p.origine) = 'C'";

		if (tipiAtto != null) {
			query += " AND " + "p.atto.tipoAtto.codice in ('" + StringUtils.join(tipiAtto, "', '") + "')";
		}

		query += " ORDER BY p.nome ASC";

		Query nativeQuery = entityManager.createQuery(query);

		return nativeQuery.getResultList();
	}

}
