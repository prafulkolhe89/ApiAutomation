package com.ibm.silverpop.sms.smpp.story39.fit;

import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.silverpop.sms.smpp.story39.fit.DataProvider;
import com.ibm.silverpop.sms.smpp.story39.fit.MessageStatus;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fit.ColumnFixture;

public class InsertRecordsFixture  extends ColumnFixture{
    public String mobileNumber;
    public String orgId;
    public String dbURI;
    public Boolean isSSLEnable;
    public String dbName = "wca-qa1-db";
    public Integer numberOfRecords;
    public String trustStoreFileName;
    public String keyStoreFileName;
    public String storePass = "servercert";
    
	public boolean Numberofrecordsinserted() throws Exception {
		boolean result = true;
        MongoClient mongoClient =  getMongoClient();
        MongoDatabase database = mongoClient.getDatabase(dbName);
        MongoCollection<Document> bookmarksCollection = database.getCollection("messageStatus");
        List<MessageStatus> list = new DataProvider().generateSmsMessageStatus(orgId, mobileNumber, numberOfRecords);
        if(list != null && !list.isEmpty()) {
        	for (MessageStatus smsMessageStatus : list) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    Document document = Document.parse(mapper.writeValueAsString(smsMessageStatus));
                    bookmarksCollection.insertOne(document);
                } catch (JsonProcessingException e) {
                    throw e;
                } catch(Exception e) {
                    throw e;
                }
            }
        }else {
        	result = false;
        }
        return result;
    }
	
	 private MongoClient getMongoClient() {
	        System.setProperty("javax.net.ssl.trustStore", trustStoreFileName);
	        System.setProperty("javax.net.ssl.trustStorePassword", storePass);
	        System.setProperty("javax.net.ssl.keyStore", keyStoreFileName);
	        System.setProperty("javax.net.ssl.keyStorePassword", storePass);

	        MongoClientOptions.Builder builder = MongoClientOptions.builder();
	        if (isSSLEnable) {
	            builder.sslEnabled(true).sslInvalidHostNameAllowed(true).build();
	        }
	        MongoClient mongoClient = new MongoClient(new MongoClientURI(dbURI, builder));
	        return mongoClient;
	    }
}
