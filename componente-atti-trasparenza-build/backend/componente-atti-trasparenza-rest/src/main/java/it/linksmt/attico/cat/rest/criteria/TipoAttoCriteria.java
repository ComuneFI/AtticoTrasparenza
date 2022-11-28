/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
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
public class TipoAttoCriteria implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2525100889633976466L;

	private List<String> tipiAtto;

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

}
