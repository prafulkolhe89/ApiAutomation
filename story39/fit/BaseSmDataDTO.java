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

public class BaseSmDataDTO implements Serializable {
    private static final long serialVersionUID = -672265054853129206L;

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
    private AddressDTO sourceAddress;
    private ReceiptDataDTO receiptData;
    private AddressDTO destAddress;

    public String getServiceType() {
        return serviceType;
    }
    public Integer getEsmClass() {
        return esmClass;
    }
    public Integer getProtocolId() {
        return protocolId;
    }
    public Integer getPriority() {
        return priority;
    }
    public String getScheduleDeliveryTime() {
        return scheduleDeliveryTime;
    }
    public String getValidityPeriod() {
        return validityPeriod;
    }
    public Integer getRegisteredDelivery() {
        return registeredDelivery;
    }
    public Integer getReplaceIfPresent() {
        return replaceIfPresent;
    }
    public Integer getDataCoding() {
        return dataCoding;
    }
    public Integer getDefaultMsgId() {
        return defaultMsgId;
    }
    public String getShortMessage() {
        return shortMessage;
    }
    public String getMessageState() {
        return messageState;
    }
    public AddressDTO getSourceAddress() {
        return sourceAddress;
    }
    public ReceiptDataDTO getReceiptData() {
        return receiptData;
    }
    public AddressDTO getDestAddress() {
        return destAddress;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public void setEsmClass(Integer esmClass) {
        this.esmClass = esmClass;
    }
    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public void setScheduleDeliveryTime(String scheduleDeliveryTime) {
        this.scheduleDeliveryTime = scheduleDeliveryTime;
    }
    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
    public void setRegisteredDelivery(Integer registeredDelivery) {
        this.registeredDelivery = registeredDelivery;
    }
    public void setReplaceIfPresent(Integer replaceIfPresent) {
        this.replaceIfPresent = replaceIfPresent;
    }
    public void setDataCoding(Integer dataCoding) {
        this.dataCoding = dataCoding;
    }
    public void setDefaultMsgId(Integer defaultMsgId) {
        this.defaultMsgId = defaultMsgId;
    }
    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }
    public void setMessageState(String messageState) {
        this.messageState = messageState;
    }
    public void setSourceAddress(AddressDTO sourceAddress) {
        this.sourceAddress = sourceAddress;
    }
    public void setReceiptData(ReceiptDataDTO receiptData) {
        this.receiptData = receiptData;
    }
    public void setDestAddress(AddressDTO destAddress) {
        this.destAddress = destAddress;
    }
}