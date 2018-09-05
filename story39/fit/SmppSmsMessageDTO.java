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

public class SmppSmsMessageDTO implements Serializable {
    private static final long serialVersionUID = -3203875372390097563L;

    private  String programId;
    private  String orgId;
    private  String message;
    private  String number;
    private  Long mailingId;
    private  String code;
    private  Long jobId;
    private  Long reportId;
    private  Long recipientId;
    private  String eventSource;
    private  String charset;
    private  String uniqueId;

    public String getProgramId() {
        return programId;
    }
    public String getOrgId() {
        return orgId;
    }
    public String getMessage() {
        return message;
    }
    public String getNumber() {
        return number;
    }
    public Long getMailingId() {
        return mailingId;
    }
    public String getCode() {
        return code;
    }
    public Long getJobId() {
        return jobId;
    }
    public Long getReportId() {
        return reportId;
    }
    public Long getRecipientId() {
        return recipientId;
    }
    public String getEventSource() {
        return eventSource;
    }
    public String getCharset() {
        return charset;
    }
    public String getUniqueId() {
        return uniqueId;
    }
    public void setProgramId(String programId) {
        this.programId = programId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setMailingId(Long mailingId) {
        this.mailingId = mailingId;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }
    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }
    public void setEventSource(String eventSource) {
        this.eventSource = eventSource;
    }
    public void setCharset(String charset) {
        this.charset = charset;
    }
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}