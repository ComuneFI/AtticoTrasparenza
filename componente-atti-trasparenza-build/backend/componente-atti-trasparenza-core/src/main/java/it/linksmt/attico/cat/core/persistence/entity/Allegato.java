package it.linksmt.attico.cat.core.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "allegato")
public class Allegato implements Serializable {

	private static final long serialVersionUID = -3454091991338154081L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;
	
	@Column(name="titolo")
	private String titolo;

	@Column(columnDefinition = "TEXT")
	private String descrizione;

	@Column(length = 255)
	private String link;

	@Column(name = "content_type", length = 255)
	private String contentType;

	@Column(nullable = false)
	private boolean principale;
	
	@Column(nullable = false)
	private Boolean riservato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "atto_id")
	private Atto atto;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the atto
	 */
	public Atto getAtto() {
		return atto;
	}

	/**
	 * @param atto the atto to set
	 */
	public void setAtto(Atto atto) {
		this.atto = atto;
	}

	/**
	 * @return the principale
	 */
	public boolean isPrincipale() {
		return principale;
	}

	/**
	 * @param principale the principale to set
	 */
	public void setPrincipale(boolean principale) {
		this.principale = principale;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Boolean isRiservato() {
		return riservato;
	}

	public void setRiservato(Boolean riservato) {
		this.riservato = riservato;
	}

}