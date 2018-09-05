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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataProvider {

    public List<MessageStatus> generateSmsMessageStatus(String orgId, String mobileNumber, int numberOfRecords) {
        List<MessageStatus> list = new ArrayList<MessageStatus>();
        for (int i = 0; i < numberOfRecords; i++) {
            MessageStatus smsMessageStatus = new MessageStatus();
            smsMessageStatus.setId("00"+i);
            smsMessageStatus.setSmsId("00"+i);
            smsMessageStatus.setDeliverSmData(getDeliverSnData(i));
            smsMessageStatus.setSmppSmsMessage(getSmsData(i,mobileNumber, orgId));
            smsMessageStatus.setSmppId(getSmppData());
            smsMessageStatus.setStatus(getStatus(orgId));
            smsMessageStatus.setSubmitSmData(generateSubmitSmData(i));
            smsMessageStatus.setTimestamp(getTimeStamp());
            list.add(smsMessageStatus);
        }

        return list;
    }

    private List<BaseSmData> generateSubmitSmData(int i) {
        BaseSmData baseSmData = new BaseSmData();
        baseSmData.setDefaultMsgId(i);
        baseSmData.setServiceType("SmDataType");
        baseSmData.setSourceAddress(generateAddress(i));
        baseSmData.setReceiptData(generateReceiptdata(i));
        baseSmData.setDestAddress(generateAddress(i));

        List<BaseSmData> baseSmDatas = new ArrayList<BaseSmData>();
        baseSmDatas.add(baseSmData);
        return baseSmDatas;
    }

    private Status getStatus(String orgId) {
        Status status = new Status();
        status.setErrorMessage("Error message");
        status.setOrgId(orgId);
        status.setOriginator("SMPP_SENDER");
        status.setState("INITIATED");
        status.setuBEventStatus("NOT_SENT");
        return status;
    }

    private List<String> getSmppData() {
        List<String> list = new ArrayList<String>();
        list.add("String1");
        list.add("String2");
        list.add("String3");
        return list;
    }

    private SmppSmsMessage getSmsData(int i, String mobileNumber, String orgId) {
        SmppSmsMessage data = new SmppSmsMessage();
        data.setCharset("charset");
        data.setCode("code");
        data.setEventSource("event surce");
        data.setJobId(1l);
        data.setMailingId(1l);
        data.setMessage("sms message");
        data.setNumber(mobileNumber);
        data.setOrgId(orgId);
        data.setProgramId("programId");
        data.setRecipientId(1l);
        data.setReportId(1l);
        data.setUniqueId("uniqueId");
        return data;
    }

    private List<BaseSmData> getDeliverSnData(int i) {
        BaseSmData baseSmData = new BaseSmData();
        baseSmData.setDefaultMsgId(1234);
        baseSmData.setDataCoding(i);
        baseSmData.setDestAddress(generateAddress(i));
        baseSmData.setSourceAddress(generateAddress(i));
        baseSmData.setReceiptData(generateReceiptdata(i));

        List<BaseSmData> baseSmDatas = new ArrayList<BaseSmData>();
        baseSmDatas.add(baseSmData);
        return baseSmDatas;
    }

    private ReceiptData generateReceiptdata(int i) {
        ReceiptData data = new ReceiptData();
        data.setDeliveredCount(i);
        data.setDoneDate(new Date().toString());
        return data;
    }

    private Address generateAddress(int i) {
        Address address = new Address();
        address.setAddress("604 model town");
        address.setNpi(i);
        address.setTon(i);
        return address;
    }

    private Timestamp getTimeStamp() {
        Timestamp timestamp = new Timestamp();
        timestamp.setDeliveredTimestamp(new Date().getTime());
        timestamp.setInitiatedTimestamp(new Date().getTime());
        return timestamp;
    }
}
