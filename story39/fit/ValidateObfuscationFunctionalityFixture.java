package com.ibm.silverpop.sms.smpp.story39.fit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.ibm.silverpop.sms.smpp.story39.fit.APIUrlBuilder.API;
import com.ibm.silverpop.sms.smpp.story39.fit.APIUrlBuilder.APIEndpoint;
import com.ibm.silverpop.sms.smpp.story39.fit.BaseServiceClient.Operation;

import fit.ColumnFixture;

public class ValidateObfuscationFunctionalityFixture extends ColumnFixture {

	public String domainName;
	public String mobileNumber;
	public String orgId;

	public String outcomeOfObfuscation() {
		APIUrlBuilder urlBuilder = new APIUrlBuilder(domainName, API.MESSAGESTATUSSERVICE, APIEndpoint.obfuscate);
		urlBuilder.append("orgId", orgId);
		urlBuilder.append("mobileNumber", mobileNumber);
		ApiServiceClient client = new ApiServiceClient(urlBuilder, new ValidateObfuscationErrorHandler());
		try {
			ResponseEntity<String> result = client.submitRequest(Operation.PUT, null, null, String.class);
			return result.getBody();
		} catch (Exception e) {
			return "No records found for given entries";
		}

	}

	class ValidateObfuscationErrorHandler implements ErrorHandler {
		public void handleError(HttpClientErrorException e) throws Exception {
			e.printStackTrace();
			throw new Exception(e.getStatusCode().getReasonPhrase());
		}
	}

	/*
	 * public static void main(String[] args) throws Exception {
	 * ValidateObfuscationFunctionality functionality = new
	 * ValidateObfuscationFunctionality(); functionality.domainName =
	 * "localhost:8080"; functionality.mobileNumber = "9970802412";
	 * functionality.orgId = "orgId"; functionality.dbURI =
	 * "mongodb://pt-teltel15243.persistent.co.in:27017/"; functionality.dbName =
	 * "testdatabase"; functionality.isSSLEnable = true;
	 * functionality.numberOfRecords = 23; functionality.trustStoreFileName =
	 * "D:\\mongo_cer\\mongoStore.ts"; functionality.keyStoreFileName =
	 * "D:\\mongo_cer\\MongoClientKeyCert29.jks"; functionality.storePass =
	 * "StorePass";
	 * 
	 * System.out.println(functionality.responceGenerated()); }
	 */

}
