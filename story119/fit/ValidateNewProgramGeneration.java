package com.ibm.silverpop.sms.smpp.story119.fit;

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
import com.ibm.silverpop.sms.smpp.story39.fit.APIUrlBuilder.API;
import com.ibm.silverpop.sms.smpp.story39.fit.ApiServiceClient;
import com.ibm.silverpop.sms.smpp.story39.fit.BaseServiceClient.Operation;
import com.ibm.silverpop.sms.smpp.story39.fit.ErrorHandler;

import fit.ColumnFixture;

public class ValidateNewProgramGeneration extends ColumnFixture{

	

	public String domainName="localhost:8089";
	
	public String Id;
	public String programName;
	public String type;
	public String status;
	public String code;
	public int page;
	public String size;
	
	static long Totalelements;
	static int totalPages;
	static int totalrecordstobedisplayedonThePage;
	static int numberofElements;
	static String SinglePrgram;
	
	public static Page<ProgramsDTO> response; 
	public static String result;
	
	public String ProgramRetrived() throws Exception 
	{
		SinglePrgram = null;
		
		//Hit the API
		APIUrlBuilder urlBuilder = new APIUrlBuilder(domainName, API.PROGRAMS, null,false);
		
		//urlBuilder.append("programName", programName);
		HashMap<String, String> queryParam = new HashMap<String, String>();
		
		//put(key,value) key comes from the swagger , Value comes from the Wiki page
		queryParam.put("programName", programName);
		queryParam.put("id", Id);
		queryParam.put(type, type);
		queryParam.put(code, code);
		queryParam.put("page", page-1+"");
		queryParam.put("size", size);
		//To ping the required URL.
		ApiServiceClient client = new ApiServiceClient(urlBuilder, new ValidateProgramErrorHandler());
		
		//This line is for fetching entire result
		ResponseEntity<String> result = client.submitRequest(Operation.GET, null, queryParam, String.class);
		
		//This line of Code is for parsing the result into an DTO object in our desired form.
		response = makeRequest(result.getBody());
		totalPages = response.getTotalPages();
		totalrecordstobedisplayedonThePage = response.getSize();
		numberofElements = response.getNumberOfElements();
		SinglePrgram = result.getBody().toString();	
		return response.toString();
		
		
		
	}
	
	public String SingleProgramRetrived() 
	{
		return SinglePrgram;
	}

	public long TotalElements() 
	{
		
		return response.getTotalElements();
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
	
	
	class ValidateProgramErrorHandler implements ErrorHandler {
		public void handleError(HttpClientErrorException e) throws Exception {
			e.printStackTrace();
			throw new Exception(e.getStatusCode().getReasonPhrase());			
		}
	}
	
	
	public Page<ProgramsDTO> makeRequest(String json)
			throws JsonParseException, JsonMappingException, IOException {
		Page<ProgramsDTO> pg = new ObjectMapper().readValue(json, CustomPageImpl.class);
		return pg;
	}
	
	
	static class CustomPageImpl extends PageImpl<ProgramsDTO> {
		boolean last;
		long totalPages;
		boolean first;
		long numberOfElements;
		String sort;

		@JsonCreator
		public CustomPageImpl(@JsonProperty("content") List<ProgramsDTO> content,
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
	
}