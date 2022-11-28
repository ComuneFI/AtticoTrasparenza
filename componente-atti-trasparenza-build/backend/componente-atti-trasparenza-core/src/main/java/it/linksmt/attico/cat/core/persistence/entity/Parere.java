package it.linksmt.attico.cat.core.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parere")
public class Parere implements Serializable {

	private static final long serialVersionUID = -8215527871033997440L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 255)
	private String nome;
	
	@Column(length = 500)
	private String testo;
	
	@Column(name = "data_invio")
	private Date dataInvio;
	
	@Column(name = "data_scadenza")
	private Date dataScadenza;
	
	@Column(name = "data_parere")
	private Date dataParere;
	
	@Column(name = "origine")
    private String origine;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "id_atto")
	private Atto atto;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Date getDataInvio() {
		return dataInvio;
	}

	public void setDataInvio(Date dataInvio) {
		this.dataInvio = dataInvio;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public Date getDataParere() {
		return dataParere;
	}

	public void setDataParere(Date dataParere) {
		this.dataParere = dataParere;
	}

	public Atto getAtto() {
		return atto;
	}

	public void setAtto(Atto atto) {
		this.atto = atto;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}
}
