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

public class BaseSmData implements Serializable{
	private static final long serialVersionUID = 8789769015062302133L;
	private String serviceType;
	private Integer esmClass;
	private Integer protocolId;
	private Integer priority;
	private String scheduleDeliveryTime;
	private String validityPeriod;
	private Integer registeredDelivery;
	private Integer replaceIfPresent;
	private Integer dataCoding;
	private Integer defaultMsgId;
	private String shortMessage;
	private String messageState;
	private Address sourceAddress;
	private ReceiptData receiptData;
	private Address destAddress;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getEsmClass() {
		return esmClass;
	}

	public void setEsmClass(Integer esmClass) {
		this.esmClass = esmClass;
	}

	public Integer getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(Integer protocolId) {
		this.protocolId = protocolId;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getScheduleDeliveryTime() {
		return scheduleDeliveryTime;
	}

	public void setScheduleDeliveryTime(String scheduleDeliveryTime) {
		this.scheduleDeliveryTime = scheduleDeliveryTime;
	}

	public String getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public Integer getRegisteredDelivery() {
		return registeredDelivery;
	}

	public void setRegisteredDelivery(Integer registeredDelivery) {
		this.registeredDelivery = registeredDelivery;
	}

	public Integer getReplaceIfPresent() {
		return replaceIfPresent;
	}

	public void setReplaceIfPresent(Integer replaceIfPresent) {
		this.replaceIfPresent = replaceIfPresent;
	}

	public Integer getDataCoding() {
		return dataCoding;
	}

	public void setDataCoding(Integer dataCoding) {
		this.dataCoding = dataCoding;
	}

	public Integer getDefaultMsgId() {
		return defaultMsgId;
	}

	public void setDefaultMsgId(Integer defaultMsgId) {
		this.defaultMsgId = defaultMsgId;
	}

	public String getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}

	public String getMessageState() {
		return messageState;
	}

	public void setMessageState(String messageState) {
		this.messageState = messageState;
	}

	public Address getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(Address sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public ReceiptData getReceiptData() {
		return receiptData;
	}

	public void setReceiptData(ReceiptData receiptData) {
		this.receiptData = receiptData;
	}

	public Address getDestAddress() {
		return destAddress;
	}

	public void setDestAddress(Address destAddress) {
		this.destAddress = destAddress;
	}
}