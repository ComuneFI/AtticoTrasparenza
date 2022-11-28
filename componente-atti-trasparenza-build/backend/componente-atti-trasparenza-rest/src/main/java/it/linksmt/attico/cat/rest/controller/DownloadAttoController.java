/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.rest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.linksmt.attico.cat.core.dto.AttoDto;
import it.linksmt.attico.cat.core.exception.ServiceException;
import it.linksmt.attico.cat.core.service.AttoService;
import it.linksmt.attico.cat.core.util.StatoAnnullamentoEnum;

/**
 *
 * Rest Controller per il download dei file.
 *
 * @author Simone Bello
 *
 */
@RestController
@RequestMapping(value = DownloadAttoController.MAIN_PATH)
public class DownloadAttoController {

	public static final String MAIN_PATH = "/download";


	private final Logger log = LoggerFactory.getLogger(DownloadAttoController.class.getName());

	@Autowired
	private AttoService attoService;
	
	@Value("${atto.allegati.system.path}")
	private String allegatiFolder;
	


	@RequestMapping(method = RequestMethod.GET, value = "/{attoId}/{fileName:.+}")
	public ResponseEntity<ByteArrayResource> downloadAtto(@PathVariable("attoId") long attoId, @PathVariable("fileName") String fileName) {
		log.info("Download "+ attoId + " File:_"+ fileName);
		try {
			AttoDto atto = attoService.getAttoByAttoId(attoId);
			log.info("Atto scaduto " + (atto!= null ? atto.getScaduto() : "NULL"));
			
			boolean visibilitaDocumenti = true;
			if(atto.getVisibilitaDocumenti() != null && !atto.getVisibilitaDocumenti().booleanValue()) {
				visibilitaDocumenti=false;
			}
			
			if(visibilitaDocumenti && !StringUtils.isEmpty(attoId) && !StringUtils.isEmpty(fileName) && 
					(atto.getScaduto() == null || !atto.getScaduto().booleanValue()) &&
					(atto.getStatoAnnullamento() == null || !atto.getStatoAnnullamento().equalsIgnoreCase(StatoAnnullamentoEnum.REVOCATO.getDescrizione()))) {
				
				
				
				File file = new File(allegatiFolder+attoId+File.separator+fileName);
				if(file.exists()) {
					FileInputStream inputend = new FileInputStream(file);
					ByteArrayResource resultByte = new ByteArrayResource(IOUtils.toByteArray(inputend));
					IOUtils.closeQuietly(inputend);
					HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getName()+ "\"");
					
					return ResponseEntity.ok()
				            .headers(headers)
				            .contentLength(file.length())
				            .contentType(MediaType.APPLICATION_OCTET_STREAM)
				            .body(resultByte);
				
				}
			}
		}
		catch (ServiceException | IOException e) {
			log.error("downloadAtto :: " + e.getMessage(), e);
		}
		return null;
	}
}