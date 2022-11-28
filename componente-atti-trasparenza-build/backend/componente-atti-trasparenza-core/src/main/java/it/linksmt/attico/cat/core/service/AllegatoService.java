/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.core.service;

import it.linksmt.attico.cat.core.dto.AllegatoDto;
import it.linksmt.attico.cat.core.exception.ServiceException;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface AllegatoService {

	/**
	 *
	 * @param allegatoId
	 * @return
	 * @throws ServiceException
	 */
	AllegatoDto getAllegatoById(long allegatoId) throws ServiceException;
}
