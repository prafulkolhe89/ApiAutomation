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


public class MessageStatusDTO implements Serializable {
    private static final long serialVersionUID = 7906213728880809840L;

    private String id;
    private String smsId;
    private List<String> smppId;
    private SmppSmsMessageDTO smppSmsMessageDTO;
    private List<BaseSmDataDTO> deliverSmDataDTO;
    private List<BaseSmDataDTO> submitSmDataDTO;
    private StatusDTO statusDTO;
    private TimestampDTO timestampDTO;

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
	public SmppSmsMessageDTO getSmppSmsMessageDTO() {
		return smppSmsMessageDTO;
	}
	public void setSmppSmsMessageDTO(SmppSmsMessageDTO smppSmsMessageDTO) {
		this.smppSmsMessageDTO = smppSmsMessageDTO;
	}
	public List<BaseSmDataDTO> getDeliverSmDataDTO() {
		return deliverSmDataDTO;
	}
	public void setDeliverSmDataDTO(List<BaseSmDataDTO> deliverSmDataDTO) {
		this.deliverSmDataDTO = deliverSmDataDTO;
	}
	public List<BaseSmDataDTO> getSubmitSmDataDTO() {
		return submitSmDataDTO;
	}
	public void setSubmitSmDataDTO(List<BaseSmDataDTO> submitSmDataDTO) {
		this.submitSmDataDTO = submitSmDataDTO;
	}
	public StatusDTO getStatusDTO() {
		return statusDTO;
	}
	public void setStatusDTO(StatusDTO statusDTO) {
		this.statusDTO = statusDTO;
	}
	public TimestampDTO getTimestampDTO() {
		return timestampDTO;
	}
	public void setTimestampDTO(TimestampDTO timestampDTO) {
		this.timestampDTO = timestampDTO;
	}
}