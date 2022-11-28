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
public class ConverterException extends Exception {

	private static final long serialVersionUID = 3807528447659212264L;

	public ConverterException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ConverterException(Throwable throwable) {
		super(throwable);
	}

	public ConverterException(String message) {
		super(message);
	}

}
