package it.linksmt.attico.cat.core.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_atto")
public class TipoAtto implements Serializable {

	private static final long serialVersionUID = 200016433983197434L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String codice;

	@Column(nullable = false, length = 255)
	private String nome;

	@OneToMany(mappedBy = "tipoAtto")
	private List<Atto> atti;

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
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
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
	 * @return the atti
	 */
	public List<Atto> getAtti() {
		return atti;
	}

	/**
	 * @param atti the atti to set
	 */
	public void setAtti(List<Atto> atti) {
		this.atti = atti;
	}

}