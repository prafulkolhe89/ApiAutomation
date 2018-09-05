package com.ibm.silverpop.sms.smpp.story119.fit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.ibm.silverpop.sms.smpp.story39.fit.APIUrlBuilder;
import com.ibm.silverpop.sms.smpp.story39.fit.APIUrlBuilder.API;
import com.ibm.silverpop.sms.smpp.story39.fit.ApiServiceClient;
import com.ibm.silverpop.sms.smpp.story39.fit.BaseServiceClient.Operation;
import com.ibm.silverpop.sms.smpp.story39.fit.ErrorHandler;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import fit.ColumnFixture;

public class UpdateProgram extends ColumnFixture {
	public String domainName = "localhost:8089";

	public String ProgramDTO;
	public String Code;
	public String programName;
	public String status;
	public String type;
	public String ProgramId;

	ResponseEntity<String> result;
	
    public String dbName = "wca-qa1-db";
    public String dbURI = "mongodb://localhost:27017/admin";
    public  boolean isSSLEnabls=false;
    public String trustStoreFileName = "D:\\Tasks\\Story 39\\cert\\cert\\mongoStore.ts";
    public String keyStoreFileName = "D:\\\\Tasks\\\\Story 39\\\\cert\\\\cert\\MongoClientKeyCert.jks";
    public String storePass = "servercert";


	public ResponseEntity<String> ProgramCreated() throws Exception {
		// Hit the API
		APIUrlBuilder urlBuilder = new APIUrlBuilder(domainName, API.PROGRAMS, null,false);

		// HashMap<String, String> queryParam = new HashMap<String, String>();
		urlBuilder.append("programId", ProgramId);

		// To ping the required URL.
		ApiServiceClient client = new ApiServiceClient(urlBuilder, new ValidateProgramErrorHandler());

		ProgramsDTO objProgramDTO = new ProgramsDTO();
		objProgramDTO.setCode(Code);
		objProgramDTO.setProgramName(programName);
		objProgramDTO.setStatus(status);
		objProgramDTO.setType(type);
		objProgramDTO.setId(ProgramId);

		/*
		 * try { ResponseEntity<String> result = client.submitRequest(Operation.PUT,
		 * objProgramDTO, null, String.class); return result.getBody(); } catch
		 * (Exception e) { return "No records found for given entries"; }
		 */

		// This line is for fetching entire result
		result = client.submitRequest(Operation.PUT, objProgramDTO, null, String.class); // -- significance of the last

		// ProgramDTO newPro = result.getBody();
		// String returnResult = createXSSInputInJson(result.getBody());

		return result;
	}
	
	
	public void FindProgram() 
	{
		  MongoClient mongoClient =  getMongoClient();
	        MongoDatabase database = mongoClient.getDatabase(dbName);
	        
	        
	       // MongoCollection<Document> bookmarksCollection = 
	        		//database.getCollection("programs").drop();
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
	
	
	
	class ValidateProgramErrorHandler implements ErrorHandler {
		public void handleError(HttpClientErrorException e) throws Exception {
			e.printStackTrace();
			throw new Exception(e.getStatusCode().getReasonPhrase());
		}

	}
}
