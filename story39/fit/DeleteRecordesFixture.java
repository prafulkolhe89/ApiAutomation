package com.ibm.silverpop.sms.smpp.story39.fit;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fit.ColumnFixture;

public class DeleteRecordesFixture extends ColumnFixture {
    public String mobileNumber;
    public String orgId;
    public String dbURI;
    public Boolean isSSLEnable;
    public String dbName = "wca-qa1-db";
    public Integer numberOfRecords;
    public String trustStoreFileName;
    public String keyStoreFileName;
    public String storePass = "servercert";
    
	 public boolean recordsDeletionStatus(){
	        MongoClient mongoClient =  getMongoClient();
	        MongoDatabase database = mongoClient.getDatabase(dbName);
	        MongoCollection<Document> bookmarksCollection = database.getCollection("messageStatus");
	            try {
	                bookmarksCollection.deleteMany(new Document("smppSmsMessage.number", mobileNumber));
	            } catch (Exception e) {
	                e.printStackTrace();
	                return false;
	            }
	            
	        return true;
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
