package com.ibm.silverpop.sms.smpp.story119.fit;

import org.bson.Document;
import org.springframework.data.repository.config.CustomRepositoryImplementationDetector;

import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import fit.ColumnFixture;

public class DeleteAPI extends ColumnFixture {
	
	public String ProgramName;
    public String dbURI = "mongodb://localhost:27017/admin";
    public  boolean isSSLEnabls=false;
    public String dbName = "wca-qa1-db";
    public Integer numberOfRecords;
    public String trustStoreFileName = "D:\\Tasks\\Story 39\\cert\\cert\\mongoStore.ts";
    public String keyStoreFileName = "D:\\\\Tasks\\\\Story 39\\\\cert\\\\cert\\MongoClientKeyCert.jks";
    public String storePass = "servercert";
  
   /* 
    public static void main(String[] args) {
    	DeleteAPI obj = new DeleteAPI();
    	obj.recordsDeletionStatus();
	}
    */
  
	 public boolean recordsDeletionStatus(){
		 //Droping the entire collection from mondo DB
	        MongoClient mongoClient =  getMongoClient();
	        MongoDatabase database = mongoClient.getDatabase(dbName);
	        
	       // MongoCollection<Document> bookmarksCollection = 
	        		database.getCollection("code").drop();
		        
	     /*   MongoClient mongoClient1 = new MongoClient();
	        DB db = mongoClient.getDB("mydb");
	        DBCollection myCollection = db.getCollection("myCollection");
	        myCollection.drop();*/
	        
	        
	      /*  Cursor cursor = (Cursor) bookmarksCollection.find();
	        while (cursor.hasNext()) {
				
	        	System.out.println(cursor);
	        	
			}*/
	        
	       /* MongoClient client = new MongoClient("10.0.2.113" , 27017);
	        MongoDatabase db = client.getDatabase("maindb");
	        db.getCollection("mainCollection").deleteMany(new Document());*/
	        
	       // Cursor cursor = (Cursor) bookmarksCollection.find();
            
	        		
//This code is for deleting the records based on the program name passed through the wiki page .	        		
	        		/*try {
	                DeleteResult deleteResult = bookmarksCollection.deleteMany(new Document("programName", ProgramName.contains("Fit_Program")));
	                System.out.println("====================="+deleteResult.hashCode());
	            } catch (Exception e) {
	                e.printStackTrace();
	                return false;
	            }*/
	        return true;
	    }
	 
	 private MongoClient getMongoClient() {
	        System.setProperty("javax.net.ssl.trustStore", trustStoreFileName);
	        System.setProperty("javax.net.ssl.trustStorePassword", storePass);
	        System.setProperty("javax.net.ssl.keyStore", keyStoreFileName);
	        System.setProperty("javax.net.ssl.keyStorePassword", storePass);

	        MongoClientOptions.Builder builder = MongoClientOptions.builder();
	        if (false) {
	            builder.sslEnabled(true).sslInvalidHostNameAllowed(true).build();
	        }
	        MongoClient mongoClient = new MongoClient(new MongoClientURI(dbURI, builder));
	        return mongoClient;
	    }
	

}
