/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.rest.criteria;

import java.io.Serializable;
import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = -3867079122427154808L;

	private String oggetto;
	private String notLoadIniziale;
	private String ufficio;
	private String competenza;
	private String relatore;
	private String proponente;
	private Integer numeroAdozione;
	private Integer annoAdozione;
	private Integer meseAdozione;
	private Integer annoPresentazione;
	private Integer mesePresentazione;
	private Integer annoApprovazione;
	private Integer meseApprovazione;
	private Integer annoScadenza;
	private Integer meseScadenza;
	private String stato;
	private String esito;
	private String assessore;
	private String commissione;
	private String sottoTipo;
	private List<String> tipiAtto;

	/**
	 * @return the oggetto
	 */
	public String getOggetto() {
		return oggetto;
	}

	/**
	 * @param oggetto the oggetto to set
	 */
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	
	public String getNotLoadIniziale() {
		return notLoadIniziale;
	}

	public void setNotLoadIniziale(String notLoadIniziale) {
		this.notLoadIniziale = notLoadIniziale;
	}

	/**
	 * @return the ufficio
	 */
	public String getUfficio() {
		return ufficio;
	}

	/**
	 * @param ufficio the ufficio to set
	 */
	public void setUfficio(String ufficio) {
		this.ufficio = ufficio;
	}

	/**
	 * @return the competenza
	 */
	public String getCompetenza() {
		return competenza;
	}

	/**
	 * @param competenza the competenza to set
	 */
	public void setCompetenza(String competenza) {
		this.competenza = competenza;
	}

	/**
	 * @return the relatore
	 */
	public String getRelatore() {
		return relatore;
	}

	/**
	 * @param relatore the relatore to set
	 */
	public void setRelatore(String relatore) {
		this.relatore = relatore;
	}

	/**
	 * @return the numeroAdozione
	 */
	public Integer getNumeroAdozione() {
		return numeroAdozione;
	}

	/**
	 * @param numeroAdozione the numeroAdozione to set
	 */
	public void setNumeroAdozione(Integer numeroAdozione) {
		this.numeroAdozione = numeroAdozione;
	}

	/**
	 * @return the annoAdozione
	 */
	public Integer getAnnoAdozione() {
		return annoAdozione;
	}

	/**
	 * @param annoAdozione the annoAdozione to set
	 */
	public void setAnnoAdozione(Integer annoAdozione) {
		this.annoAdozione = annoAdozione;
	}

	/**
	 * @return the meseAdozione
	 */
	public Integer getMeseAdozione() {
		return meseAdozione;
	}

	/**
	 * @param meseAdozione the meseAdozione to set
	 */
	public void setMeseAdozione(Integer meseAdozione) {
		this.meseAdozione = meseAdozione;
	}

	/**
	 * @return the annoPresentazione
	 */
	public Integer getAnnoPresentazione() {
		return annoPresentazione;
	}

	/**
	 * @param annoPresentazione the annoPresentazione to set
	 */
	public void setAnnoPresentazione(Integer annoPresentazione) {
		this.annoPresentazione = annoPresentazione;
	}

	/**
	 * @return the mesePresentazione
	 */
	public Integer getMesePresentazione() {
		return mesePresentazione;
	}

	/**
	 * @param mesePresentazione the mesePresentazione to set
	 */
	public void setMesePresentazione(Integer mesePresentazione) {
		this.mesePresentazione = mesePresentazione;
	}

	/**
	 * @return the annoApprovazione
	 */
	public Integer getAnnoApprovazione() {
		return annoApprovazione;
	}

	/**
	 * @param annoApprovazione the annoApprovazione to set
	 */
	public void setAnnoApprovazione(Integer annoApprovazione) {
		this.annoApprovazione = annoApprovazione;
	}

	/**
	 * @return the meseApprovazione
	 */
	public Integer getMeseApprovazione() {
		return meseApprovazione;
	}

	/**
	 * @param meseApprovazione the meseApprovazione to set
	 */
	public void setMeseApprovazione(Integer meseApprovazione) {
		this.meseApprovazione = meseApprovazione;
	}

	/**
	 * @return the annoScadenza
	 */
	public Integer getAnnoScadenza() {
		return annoScadenza;
	}

	/**
	 * @param annoScadenza the annoScadenza to set
	 */
	public void setAnnoScadenza(Integer annoScadenza) {
		this.annoScadenza = annoScadenza;
	}

	/**
	 * @return the meseScadenza
	 */
	public Integer getMeseScadenza() {
		return meseScadenza;
	}

	/**
	 * @param meseScadenza the meseScadenza to set
	 */
	public void setMeseScadenza(Integer meseScadenza) {
		this.meseScadenza = meseScadenza;
	}

	/**
	 * @return the stato
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * @return the esito
	 */
	public String getEsito() {
		return esito;
	}

	/**
	 * @param esito the esito to set
	 */
	public void setEsito(String esito) {
		this.esito = esito;
	}

	/**
	 * @return the assessore
	 */
	public String getAssessore() {
		return assessore;
	}

	/**
	 * @param assessore the assessore to set
	 */
	public void setAssessore(String assessore) {
		this.assessore = assessore;
	}

	/**
	 * @return the commissione
	 */
	public String getCommissione() {
		return commissione;
	}

	/**
	 * @param commissione the commissione to set
	 */
	public void setCommissione(String commissione) {
		this.commissione = commissione;
	}

	/**
	 * @return the tipiAtto
	 */
	public List<String> getTipiAtto() {
		return tipiAtto;
	}

	/**
	 * @param tipiAtto the tipiAtto to set
	 */
	public void setTipiAtto(List<String> tipiAtto) {
		this.tipiAtto = tipiAtto;
	}

	/**
	 * @return the sottoTipo
	 */
	public String getSottoTipo() {
		return sottoTipo;
	}

	/**
	 * @param sottoTipo the sottoTipo to set
	 */
	public void setSottoTipo(String sottoTipo) {
		this.sottoTipo = sottoTipo;
	}

	/**
	 * @return the proponente
	 */
	public String getProponente() {
		return proponente;
	}

	/**
	 * @param proponente the proponente to set
	 */
	public void setProponente(String proponente) {
		this.proponente = proponente;
	}

}
