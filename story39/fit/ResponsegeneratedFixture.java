package com.ibm.silverpop.sms.smpp.story39.fit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.silverpop.sms.smpp.story39.fit.APIUrlBuilder;
import com.ibm.silverpop.sms.smpp.story39.fit.ApiServiceClient;
import com.ibm.silverpop.sms.smpp.story39.fit.ErrorHandler;
import com.ibm.silverpop.sms.smpp.story39.fit.SmsMessageStatusDTO;
import com.ibm.silverpop.sms.smpp.story39.fit.APIUrlBuilder.API;
import com.ibm.silverpop.sms.smpp.story39.fit.BaseServiceClient.Operation;

import fit.ColumnFixture;

public class ResponsegeneratedFixture extends ColumnFixture {

	public String domainName;
	public String mobileNumber;
	public String orgId;
	public int page;
	public String size;
	
	static long Totalelements;
	static int totalPages;
	static int totalrecordstobedisplayedonThePage;
	static int numberofElements;

	public String responceGenerated() throws Exception {
		APIUrlBuilder urlBuilder = new APIUrlBuilder(domainName, API.MESSAGESTATUSSERVICE, null);
		urlBuilder.append("orgId", orgId);
		urlBuilder.append("mobileNumber", mobileNumber);
		
		HashMap<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("page", page-1+"");
		queryParam.put("size", size);
		
		ApiServiceClient client = new ApiServiceClient(urlBuilder, new ValidateObfuscationErrorHandler());
		ResponseEntity<String> result = client.submitRequest(Operation.GET, null, queryParam, String.class);
		Page<SmsMessageStatusDTO> response = makeRequest(result.getBody());
		Totalelements = response.getTotalElements();
		totalPages = response.getTotalPages();
		totalrecordstobedisplayedonThePage = response.getSize();
		numberofElements = response.getNumberOfElements();
		return "sucess";
	}
	
	
	
	public long TotalElements() 
	{
		return Totalelements;
	}
	
	public int totalPages() 
	{
		return totalPages;
		
	}
	
	public int PageSize() {
	return totalrecordstobedisplayedonThePage;
	}
	
	public int NumberofElements(){
		return numberofElements;
		
	}

	class ValidateObfuscationErrorHandler implements ErrorHandler {
		public void handleError(HttpClientErrorException e) throws Exception {
			e.printStackTrace();
			throw new Exception(e.getStatusCode().getReasonPhrase());
		}
	}

	static class CustomPageImpl extends PageImpl<SmsMessageStatusDTO> {
		boolean last;
		long totalPages;
		boolean first;
		long numberOfElements;
		String sort;

		@JsonCreator
		public CustomPageImpl(@JsonProperty("content") List<SmsMessageStatusDTO> content,
				@JsonProperty("number") int page, @JsonProperty("size") int size,
				@JsonProperty("totalElements") long total, @JsonProperty("last") boolean last,
				@JsonProperty("totalPages") long totalPages, @JsonProperty("first") boolean first,
				@JsonProperty("numberOfElements") long numberOfElements, @JsonProperty("sort") String sort) {
			super(content, new PageRequest(page, size), total);
			this.last = last;
			this.totalPages = totalPages;
			this.first = first;
			this.numberOfElements = numberOfElements;
			this.sort = sort;
		}
	}

	public Page<SmsMessageStatusDTO> makeRequest(String json)
			throws JsonParseException, JsonMappingException, IOException {
		Page<SmsMessageStatusDTO> pg = new ObjectMapper().readValue(json, CustomPageImpl.class);
		return pg;
	}
}
