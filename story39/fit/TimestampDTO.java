/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.silverpop.sms.smpp.story39.fit;


import java.io.Serializable;

public class TimestampDTO implements Serializable {
	private static final long serialVersionUID = -2282460463679048376L;

	private Long initiatedTimestamp;
	private Long sentTimestamp;
	private Long deliveredTimestamp;
	private Long lastModifiedTimestamp;

	public Long getInitiatedTimestamp() {
		return initiatedTimestamp;
	}
	public void setInitiatedTimestamp(Long initiatedTimestamp) {
		this.initiatedTimestamp = initiatedTimestamp;
	}
	public Long getSentTimestamp() {
		return sentTimestamp;
	}
	public void setSentTimestamp(Long sentTimestamp) {
		this.sentTimestamp = sentTimestamp;
	}
	public Long getDeliveredTimestamp() {
		return deliveredTimestamp;
	}
	public void setDeliveredTimestamp(Long deliveredTimestamp) {
		this.deliveredTimestamp = deliveredTimestamp;
	}
	public Long getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}
	public void setLastModifiedTimestamp(Long lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
	}
}