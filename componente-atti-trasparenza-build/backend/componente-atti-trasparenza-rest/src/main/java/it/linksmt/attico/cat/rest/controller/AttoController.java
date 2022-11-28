/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.linksmt.attico.cat.core.dto.AttoDto;
import it.linksmt.attico.cat.core.dto.SearchDto;
import it.linksmt.attico.cat.core.exception.ServiceException;
import it.linksmt.attico.cat.core.service.AttoService;
import it.linksmt.attico.cat.rest.criteria.SearchCriteria;
import it.linksmt.attico.cat.rest.criteria.TipoAttoCriteria;
import it.linksmt.attico.cat.rest.dto.DefaultResponse;
import it.linksmt.attico.cat.rest.enumeration.ResponseResult;

/**
 *
 * Rest Controller per l'accesso agli atti presenti nel sistema.
 *
 * @author Gianluca Pindinelli
 *
 */
@RestController
@RequestMapping(value = AttoController.MAIN_PATH)
public class AttoController {

	public static final String MAIN_PATH = "/cat";
	public static final String PUT_ATTO_PATH = "/putAtto";

	private final Logger log = LoggerFactory.getLogger(AttoController.class.getName());

	@Autowired
	private AttoService attoService;
	
	@Value("${numero.massimo.ricerca.atti}")
	private String queryLimit;

//	@Autowired
//	private AllegatoService allegatoService;

	@RequestMapping(method = RequestMethod.GET, value = "/getAll", produces = "application/json")
	@ResponseBody
	public List<AttoDto> getAll(@PageableDefault(size = 2000) Pageable pageRequest) {
		try {
			return attoService.getAllAtti(pageRequest);
		}
		catch (ServiceException e) {
			log.error("getAll :: " + e.getMessage(), e);
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{attoId}", produces = "application/json")
	@ResponseBody
	public AttoDto getAtto(@PathVariable("attoId") long attoId) {

		try {
			return attoService.getAtto(attoId);
		}
		catch (ServiceException e) {
			log.error("getAtto :: " + e.getMessage(), e);
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/searchAtti", produces = "application/json")
	@ResponseBody
	public List<AttoDto> searchAtti(@RequestBody SearchCriteria searchCriteria, @PageableDefault(size = 2000) Pageable pageRequest) {
		try {

//			if(isEmpty(searchCriteria)) {
//				
//			}else {
				SearchDto searchDto = new SearchDto();
				if(searchCriteria.getNotLoadIniziale() == null ) {
					return new ArrayList<AttoDto>();
				}
				
				searchDto.setAnnoAdozione(searchCriteria.getAnnoAdozione());
				searchDto.setAnnoApprovazione(searchCriteria.getAnnoApprovazione());
				searchDto.setAnnoPresentazione(searchCriteria.getAnnoPresentazione());
				searchDto.setAnnoScadenza(searchCriteria.getAnnoScadenza());
				searchDto.setAssessore(searchCriteria.getAssessore());
				searchDto.setCommissione(searchCriteria.getCommissione());
				searchDto.setCompetenza(searchCriteria.getCompetenza());
				searchDto.setEsito(searchCriteria.getEsito());
				searchDto.setMeseAdozione(searchCriteria.getMeseAdozione());
				searchDto.setMeseApprovazione(searchCriteria.getMeseApprovazione());
				searchDto.setMesePresentazione(searchCriteria.getMesePresentazione());
				searchDto.setMeseScadenza(searchCriteria.getMeseScadenza());
				searchDto.setNumeroAdozione(searchCriteria.getNumeroAdozione());
				searchDto.setOggetto(searchCriteria.getOggetto());
				searchDto.setRelatore(searchCriteria.getRelatore());
				searchDto.setStato(searchCriteria.getStato());
				searchDto.setTipiAtto(searchCriteria.getTipiAtto());
				searchDto.setUfficio(searchCriteria.getUfficio());
				searchDto.setSottoTipo(searchCriteria.getSottoTipo());
				searchDto.setProponente(searchCriteria.getProponente());
				
				int limit = 1000;
				try {
					limit = Integer.parseInt(queryLimit);
				} catch (Exception e) {
					//NOTHING TO DO}
				}
				if(attoService.countAtti(searchDto, pageRequest)>limit) {
					return null;
				}
				return attoService.searchAtti(searchDto, pageRequest);
//			}
			
			
		}
		catch (Exception e) {
			log.error("searchAtti :: " + e.getMessage(), e);
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/resetAtti", produces = "application/json")
	@ResponseBody
	public List<AttoDto> resetAtti(@RequestBody SearchCriteria searchCriteria, @PageableDefault(size = 2000) Pageable pageRequest) {
		
		return new ArrayList<AttoDto>();
	}
	
	private boolean isEmpty(SearchCriteria searchCriteria) {
		if(searchCriteria==null) {
			return true;
		}
		
		if(
				(searchCriteria.getAnnoAdozione()!=null && searchCriteria.getAnnoAdozione().intValue()>0) ||
				(searchCriteria.getAnnoApprovazione()!=null && searchCriteria.getAnnoApprovazione().intValue()>0) ||
				(searchCriteria.getAnnoPresentazione()!=null && searchCriteria.getAnnoPresentazione().intValue()>0)||
				(searchCriteria.getAnnoScadenza()!=null && searchCriteria.getAnnoScadenza().intValue()>0)||
				(searchCriteria.getAssessore()!=null && !searchCriteria.getAssessore().isEmpty())||
				(searchCriteria.getCommissione()!=null && !searchCriteria.getCommissione().isEmpty())||
				(searchCriteria.getCompetenza()!=null && !searchCriteria.getCompetenza().isEmpty())||
				(searchCriteria.getEsito()!=null && !searchCriteria.getEsito().isEmpty())||
				(searchCriteria.getMeseAdozione()!=null && searchCriteria.getMeseAdozione().intValue()>0) ||
				(searchCriteria.getMeseApprovazione()!=null && searchCriteria.getMeseApprovazione().intValue()>0) ||
				(searchCriteria.getMesePresentazione()!=null && searchCriteria.getMesePresentazione().intValue()>0)||
				(searchCriteria.getMeseScadenza()!=null && searchCriteria.getMeseScadenza().intValue()>0)||
				(searchCriteria.getNumeroAdozione()!=null && searchCriteria.getNumeroAdozione().intValue()>0)||
				(searchCriteria.getProponente()!=null && !searchCriteria.getProponente().isEmpty())||
				(searchCriteria.getOggetto()!=null && !searchCriteria.getOggetto().isEmpty())||
				(searchCriteria.getRelatore()!=null && !searchCriteria.getRelatore().isEmpty())||
				(searchCriteria.getSottoTipo()!=null && !searchCriteria.getSottoTipo().isEmpty())||
				(searchCriteria.getStato()!=null && !searchCriteria.getStato().isEmpty())||
				(searchCriteria.getUfficio()!=null && !searchCriteria.getUfficio().isEmpty())
				
				) {
			return false;
		}
		
		
		return true;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAttoByTipoNumeroAnno", produces = "application/json")
	@ResponseBody
	public AttoDto getAttoById(@RequestParam(value = "tipo", required = true) String tipo,
							   @RequestParam(value = "numero", required = true) String numero,
							   @RequestParam(value = "anno", required = true) Integer anno) {
		try {
			return attoService.getAttoByTipoNumeroAnno(tipo, numero, anno);
		}
		catch (Exception e) {
			log.error("getAttoByTipoNumeroAnno :: " + e.getMessage(), e);
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getUffici", produces = "application/json")
	@ResponseBody
	public List<String> getUffici(@RequestBody TipoAttoCriteria tipiAttoCriteria) {
		try {
			return attoService.getUffici(tipiAttoCriteria.getTipiAtto());
		}
		catch (ServiceException e) {
			log.error("getUffici :: " + e.getMessage(), e);
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getRelatori", produces = "application/json")
	@ResponseBody
	public List<String> getRelatori(@RequestBody TipoAttoCriteria tipiAttoCriteria) {
		try {
			return attoService.getRelatori(tipiAttoCriteria.getTipiAtto());
		}
		catch (ServiceException e) {
			log.error("getRelatori :: " + e.getMessage(), e);
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getStati", produces = "application/json")
	@ResponseBody
	public List<String> getStati(@RequestBody TipoAttoCriteria tipiAttoCriteria) {
		try {
			return attoService.getStati(tipiAttoCriteria.getTipiAtto());
		}
		catch (ServiceException e) {
			log.error("getStati :: " + e.getMessage(), e);
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getEsiti", produces = "application/json")
	@ResponseBody
	public List<String> getEsiti(@RequestBody TipoAttoCriteria tipiAttoCriteria) {
		try {
			return attoService.getEsiti(tipiAttoCriteria.getTipiAtto());
		}
		catch (ServiceException e) {
			log.error("getEsiti :: " + e.getMessage(), e);
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAssessori", produces = "application/json")
	@ResponseBody
	public List<String> getAssessori(@RequestBody TipoAttoCriteria tipiAttoCriteria) {
		try {
			return attoService.getAssessori(tipiAttoCriteria.getTipiAtto());
		}
		catch (ServiceException e) {
			log.error("getAssessori :: " + e.getMessage(), e);
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getCommissioni", produces = "application/json")
	@ResponseBody
	public List<String> getCommissioni(@RequestBody TipoAttoCriteria tipiAttoCriteria) {
		try {
			return attoService.getCommissioni(tipiAttoCriteria.getTipiAtto());
		}
		catch (ServiceException e) {
			log.error("getCommissioni :: " + e.getMessage(), e);
		}
		return null;
	}

	/*
	 * Giorgio: Gestito tramite apache
	 *
	 * @RequestMapping(value = "/download/{allegatoId}", method = RequestMethod.GET) public
	 * ResponseEntity<ByteArrayResource> download(@PathVariable("allegatoId") long allegatoId)
	 * throws IOException, URISyntaxException, ServiceException {
	 *
	 * AllegatoDto allegatoDto = allegatoService.getAllegatoById(allegatoId);
	 * ResponseEntity<ByteArrayResource> body = null;
	 *
	 * if (allegatoDto != null) { HttpHeaders headers = new HttpHeaders();
	 * headers.add("Access-Control-Allow-Origin", "*"); headers.add("Access-Control-Allow-Headers",
	 * "Content-Type"); headers.add("Content-Disposition", "filename=" + allegatoDto.getNome());
	 * headers.add("Pragma", "no-cache"); headers.add("Expires", "0");
	 *
	 * if (allegatoDto.getContentType() != null) { headers.add("Content-Type",
	 * allegatoDto.getContentType()); }
	 *
	 * URL url = new URL(allegatoDto.getLink()); int fileSize = getFileSize(url);
	 *
	 * ByteArrayResource resource = new ByteArrayResource(downloadFile(url));
	 *
	 * body = ResponseEntity.ok().headers(headers).contentLength(fileSize).body(resource);
	 *
	 * } return body;
	 *
	 * }
	 */

	@RequestMapping(method = RequestMethod.PUT, value = PUT_ATTO_PATH, produces = "application/json")
	@ResponseBody
	public DefaultResponse putAtto(@RequestBody AttoDto attoDto) {

		DefaultResponse defaultResponse = new DefaultResponse();

		try {
			attoService.saveAtto(attoDto);
			defaultResponse.setEsito(ResponseResult.OK);
		}
		catch (ServiceException e) {
			String errorMessage = getFullExceptionMessage(e);
			log.error("putAtto :: " + e.getMessage(), e);
			defaultResponse.setError(errorMessage);
			defaultResponse.setEsito(ResponseResult.ERROR);
		}

		return defaultResponse;
	}

	/**
	 * @param e
	 * @return
	 */
	private String getFullExceptionMessage(Throwable e) {

		String errorMessage = e.getMessage();

		if (e.getCause() != null) {
			errorMessage += "\n Caused by: " + getFullExceptionMessage(e.getCause());
		}

		return errorMessage;
	}

	/*
	 * Giorgio: Gestito tramite apache
	 *
	 * public byte[] downloadFile(URL url) { try { URLConnection conn = url.openConnection();
	 * conn.setConnectTimeout(5000); conn.setReadTimeout(5000); conn.connect();
	 *
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); IOUtils.copy(conn.getInputStream(),
	 * baos);
	 *
	 * return baos.toByteArray(); } catch (IOException e) { log.error("unable to download file :: "
	 * + e.getMessage(), e); } return null; }
	 *
	 * private int getFileSize(URL url) { URLConnection conn = null; try { conn =
	 * url.openConnection(); if (conn instanceof HttpURLConnection) { ((HttpURLConnection)
	 * conn).setRequestMethod("HEAD"); } conn.getInputStream(); return conn.getContentLength(); }
	 * catch (IOException e) { throw new RuntimeException(e); } finally { if (conn instanceof
	 * HttpURLConnection) { ((HttpURLConnection) conn).disconnect(); } } }
	 */
}