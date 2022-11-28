package it.linksmt.attico.cat.core.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "atto")
public class Atto implements Serializable {

	private static final long serialVersionUID = 607218386740943755L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "atto_id", unique = true)
	private Long attoId;

	@Column(name = "numero_adozione", nullable = false, length = 50)
	private String numeroAdozione;

	@Column(name = "data_adozione")
	private Date dataAdozione;

	@Column(name = "data_esecutivita")
	private Date dataEsecutivita;

	@Column(name = "data_pubblicazione")
	private Date dataPubblicazione;

	@Column(name = "data_fine_pubblicazione")
	private Date dataFinePubblicazione;
	
	@Column(name = "data_presentazione")
	private Date dataPresentazione;

	@Column(name = "data_approvazione")
	private Date dataApprovazione;

	@Column(name = "data_scadenza")
	private Date dataScadenza;

	@Column(length = 255)
	private String ufficio;

	@Column(length = 255)
	private String relatore;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String oggetto;

	@OneToMany(mappedBy = "atto", cascade = CascadeType.ALL)
	private List<Allegato> allegati;

	@ManyToOne
	@JoinColumn(name = "tipo_atto_id")
	private TipoAtto tipoAtto;

	@Column(length = 100)
	private String stato;

	@Column(length = 100)
	private String esito;

	@Column(length = 255)
	private String assessore;
	
	@Column(length = 255)
	private String proponente;

	@Column(name = "sotto_tipo", length = 100)
	private String sottoTipo;

	private Boolean riservato;
	
	@Column(name = "visibilita_documenti")
	private Boolean visibilitaDocumenti;
	
	@Column(name = "stato_annullamento", length = 3)
	private String statoAnnullamento;
	
	@Column(name = "descrizione_stato_annullamento", length = 255)
	private String descrizioneStatoAnnullamento;
	
	@Column(name = "numero_atto_annullamento", length = 50)
	private String numeroAttoAnnullamento;
	
	@Column(name = "data_atto_annullamento")
	private Date dataAttoAnnullamento;
	
	@Column(name = "tipo_atto_annullamento")
	private String tipoAttoAnnullamento;
		
	@Column(name = "num_presenti")
	private Integer numPresenti;
	
	@Column(name = "num_astenuti")
	private Integer numAstenuti;
	
	@Column(name = "nomi_astenuti", length = 1000)
	private String nomiAstenuti;
	
	@Column(name = "num_npv")
	private Integer numNpv;
	
	@Column(name = "nomi_npv", length = 1000)
	private String nomiNpv;
	
	@Column(name = "num_contrari")
	private Integer numContrari;
	
	@Column(name = "nomi_contrari", length = 1000)
	private String nomiContrari;
	
	@Column(name = "num_favorevoli")
	private Integer numFavorevoli;
	
	@Column(name = "nomi_favorevoli", length = 1000)
	private String nomiFavorevoli;
	
	@OneToMany(mappedBy = "atto", cascade = CascadeType.ALL)
	private List<Parere> pareri;


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
	 * @return the numeroAdozione
	 */
	public String getNumeroAdozione() {
		return numeroAdozione;
	}

	/**
	 * @param numeroAdozione the numeroAdozione to set
	 */
	public void setNumeroAdozione(String numeroAdozione) {
		this.numeroAdozione = numeroAdozione;
	}

	/**
	 * @return the dataAdozione
	 */
	public Date getDataAdozione() {
		return dataAdozione;
	}

	/**
	 * @param dataAdozione the dataAdozione to set
	 */
	public void setDataAdozione(Date dataAdozione) {
		this.dataAdozione = dataAdozione;
	}

	/**
	 * @return the dataEsecutivita
	 */
	public Date getDataEsecutivita() {
		return dataEsecutivita;
	}

	/**
	 * @param dataEsecutivita the dataEsecutivita to set
	 */
	public void setDataEsecutivita(Date dataEsecutivita) {
		this.dataEsecutivita = dataEsecutivita;
	}

	/**
	 * @return the dataPubblicazione
	 */
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	/**
	 * @param dataPubblicazione the dataPubblicazione to set
	 */
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	/**
	 * @return the dataPresentazione
	 */
	public Date getDataPresentazione() {
		return dataPresentazione;
	}

	/**
	 * @param dataPresentazione the dataPresentazione to set
	 */
	public void setDataPresentazione(Date dataPresentazione) {
		this.dataPresentazione = dataPresentazione;
	}

	/**
	 * @return the dataApprovazione
	 */
	public Date getDataApprovazione() {
		return dataApprovazione;
	}

	/**
	 * @param dataApprovazione the dataApprovazione to set
	 */
	public void setDataApprovazione(Date dataApprovazione) {
		this.dataApprovazione = dataApprovazione;
	}

	/**
	 * @return the dataScadenza
	 */
	public Date getDataScadenza() {
		return dataScadenza;
	}

	/**
	 * @param dataScadenza the dataScadenza to set
	 */
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
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

	/**
	 * @return the allegati
	 */
	public List<Allegato> getAllegati() {
		return allegati;
	}

	/**
	 * @param allegati the allegati to set
	 */
	public void setAllegati(List<Allegato> allegati) {
		this.allegati = allegati;
	}

	/**
	 * @return the tipoAtto
	 */
	public TipoAtto getTipoAtto() {
		return tipoAtto;
	}

	/**
	 * @param tipoAtto the tipoAtto to set
	 */
	public void setTipoAtto(TipoAtto tipoAtto) {
		this.tipoAtto = tipoAtto;
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

	/**
	 * @return the attoId
	 */
	public Long getAttoId() {
		return attoId;
	}

	/**
	 * @param attoId the attoId to set
	 */
	public void setAttoId(Long attoId) {
		this.attoId = attoId;
	}

	/**
	 * @return the riservato
	 */
	public Boolean getRiservato() {
		return riservato;
	}

	/**
	 * @param riservato the riservato to set
	 */
	public void setRiservato(Boolean riservato) {
		this.riservato = riservato;
	}

	/**
	 * @return the statoAnnullamento
	 */
	public String getStatoAnnullamento() {
		return statoAnnullamento;
	}

	/**
	 * @param statoAnnullamento
	 */
	public void setStatoAnnullamento(String statoAnnullamento) {
		this.statoAnnullamento = statoAnnullamento;
	}
	
	/**
	 * @return the numeroAttoAnnullamento
	 */
	public String getNumeroAttoAnnullamento() {
		return numeroAttoAnnullamento;
	}

	/**
	 * @param numeroAttoAnnullamento
	 */
	public void setNumeroAttoAnnullamento(String numeroAttoAnnullamento) {
		this.numeroAttoAnnullamento = numeroAttoAnnullamento;
	}

	/**
	 * @return the dataAttoAnnullamento
	 */
	public Date getDataAttoAnnullamento() {
		return dataAttoAnnullamento;
	}

	/**
	 * @param dataAttoAnnullamento
	 */
	public void setDataAttoAnnullamento(Date dataAttoAnnullamento) {
		this.dataAttoAnnullamento = dataAttoAnnullamento;
	}

	/**
	 * @return the tipoAttoAnnullamento
	 */
	public String getTipoAttoAnnullamento() {
		return tipoAttoAnnullamento;
	}

	/**
	 * @param tipoAttoAnnullamento
	 */
	public void setTipoAttoAnnullamento(String tipoAttoAnnullamento) {
		this.tipoAttoAnnullamento = tipoAttoAnnullamento;
	}
	
	public String getDescrizioneStatoAnnullamento() {
		return descrizioneStatoAnnullamento;
	}

	public void setDescrizioneStatoAnnullamento(String descrizioneStatoAnnullamento) {
		this.descrizioneStatoAnnullamento = descrizioneStatoAnnullamento;
	}

	/**
	 * @return the numPresenti
	 */
	public Integer getNumPresenti() {
		return numPresenti;
	}

	/**
	 * @param numPresenti
	 */
	public void setNumPresenti(Integer numPresenti) {
		this.numPresenti = numPresenti;
	}

	/**
	 * @return the numAstenuti
	 */
	public Integer getNumAstenuti() {
		return numAstenuti;
	}

	/**
	 * @param numAstenuti
	 */
	public void setNumAstenuti(Integer numAstenuti) {
		this.numAstenuti = numAstenuti;
	}

	/**
	 * @return the nomiAstenuti
	 */
	public String getNomiAstenuti() {
		return nomiAstenuti;
	}

	/**
	 * @param nomiAstenuti
	 */
	public void setNomiAstenuti(String nomiAstenuti) {
		this.nomiAstenuti = nomiAstenuti;
	}

	/**
	 * @return the numNpv
	 */
	public Integer getNumNpv() {
		return numNpv;
	}

	/**
	 * @param numNpv
	 */
	public void setNumNpv(Integer numNpv) {
		this.numNpv = numNpv;
	}

	/**
	 * @return the nomiNpv
	 */
	public String getNomiNpv() {
		return nomiNpv;
	}

	/**
	 * @param nomiNpv
	 */
	public void setNomiNpv(String nomiNpv) {
		this.nomiNpv = nomiNpv;
	}

	/**
	 * @return the numContrari
	 */
	public Integer getNumContrari() {
		return numContrari;
	}

	/**
	 * @param numContrari
	 */
	public void setNumContrari(Integer numContrari) {
		this.numContrari = numContrari;
	}

	/**
	 * @return the nomiContrari
	 */
	public String getNomiContrari() {
		return nomiContrari;
	}

	/**
	 * @param nomiContrari
	 */
	public void setNomiContrari(String nomiContrari) {
		this.nomiContrari = nomiContrari;
	}

	/**
	 * @return the numFavorevoli
	 */
	public Integer getNumFavorevoli() {
		return numFavorevoli;
	}

	/**
	 * @param numFavorevoli
	 */
	public void setNumFavorevoli(Integer numFavorevoli) {
		this.numFavorevoli = numFavorevoli;
	}

	/**
	 * @return the nomiFavorevoli
	 */
	public String getNomiFavorevoli() {
		return nomiFavorevoli;
	}

	/**
	 * @param nomiFavorevoli
	 */
	public void setNomiFavorevoli(String nomiFavorevoli) {
		this.nomiFavorevoli = nomiFavorevoli;
	}

	/**
	 * @return the pareri
	 */
	public List<Parere> getPareri() {
		return pareri;
	}

	/**
	 * @param pareri
	 */
	public void setPareri(List<Parere> pareri) {
		this.pareri = pareri;
	}

	public Date getDataFinePubblicazione() {
		return dataFinePubblicazione;
	}

	public void setDataFinePubblicazione(Date dataFinePubblicazione) {
		this.dataFinePubblicazione = dataFinePubblicazione;
	}
	
	public Boolean getVisibilitaDocumenti() {
		return visibilitaDocumenti;
	}

	public void setVisibilitaDocumenti(Boolean visibilitaDocumenti) {
		this.visibilitaDocumenti = visibilitaDocumenti;
	}

	public boolean getScaduto() {
		boolean scaduto = false;
		Date now = new Date();
		if(this.riservato == null || this.riservato.equals(false)) {
			if(this.dataFinePubblicazione != null && this.dataFinePubblicazione.before(now)) {
				scaduto = true;
			}
		}
		return scaduto;
	}
}