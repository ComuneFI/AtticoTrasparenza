/************************************************************************************
 * Copyright (c) 2011, 2018 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.linksmt.attico.cat.core.exception;

/**
 * @author Gianluca Pindinelli
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3807528447659212264L;

	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ServiceException(Throwable throwable) {
		super(throwable);
	}

	public ServiceException(String message) {
		super(message);
	}

}
