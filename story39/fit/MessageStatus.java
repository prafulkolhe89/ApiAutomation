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
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class MessageStatus implements Serializable {
	private static final long serialVersionUID = -7765708158070806173L;

	@Indexed(background = true)
	private String id;
	@Indexed(background = true)
	private String smsId;
	@Indexed(background = true, sparse = true)
	private List<String> smppId;
	private SmppSmsMessage smppSmsMessage;
	private List<BaseSmData> deliverSmData;
	private List<BaseSmData> submitSmData;
	private Status status;
	private Timestamp timestamp;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSmsId() {
		return smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
	public List<String> getSmppId() {
		return smppId;
	}
	public void setSmppId(List<String> smppId) {
		this.smppId = smppId;
	}
	public SmppSmsMessage getSmppSmsMessage() {
		return smppSmsMessage;
	}
	public void setSmppSmsMessage(SmppSmsMessage smppSmsMessage) {
		this.smppSmsMessage = smppSmsMessage;
	}
	public List<BaseSmData> getDeliverSmData() {
		return deliverSmData;
	}
	public void setDeliverSmData(List<BaseSmData> deliverSmData) {
		this.deliverSmData = deliverSmData;
	}
	public List<BaseSmData> getSubmitSmData() {
		return submitSmData;
	}
	public void setSubmitSmData(List<BaseSmData> submitSmData) {
		this.submitSmData = submitSmData;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}