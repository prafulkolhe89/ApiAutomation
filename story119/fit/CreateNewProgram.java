package com.ibm.silverpop.sms.smpp.story119.fit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.silverpop.sms.smpp.story119.fit.ValidateNewProgramGeneration.CustomPageImpl;
import com.ibm.silverpop.sms.smpp.story39.fit.APIUrlBuilder;
import com.ibm.silverpop.sms.smpp.story39.fit.APIUrlBuilder.API;
import com.ibm.silverpop.sms.smpp.story39.fit.ApiServiceClient;
import com.ibm.silverpop.sms.smpp.story39.fit.BaseServiceClient.Operation;
import com.ibm.silverpop.sms.smpp.story39.fit.ErrorHandler;

import fit.ColumnFixture;

public class CreateNewProgram extends ColumnFixture {
	public String domainName = "localhost:8089";

	public String ProgramDTO;
	public String code = null;
	public String programname = null;
	public String status = null;
	public String type = null;

	public String Current_date_time;
	public String NewMessage;
	public String data;
	public int NumberOfProgramsToBecreated;

	public static ResponseEntity<ProgramsDTO> result;
	public static List<ResponseEntity<ProgramsDTO>> resultfinal = new ArrayList<ResponseEntity<ProgramsDTO>>();

	List<String> statusCodes = null;

	public ResponseEntity<ProgramsDTO> ProgramCreated() throws Exception {
		result = null ;

		// Hit the API
		APIUrlBuilder urlBuilder = new APIUrlBuilder(domainName, API.PROGRAMS, null, false);
		// To ping the required URL.
		ApiServiceClient client = new ApiServiceClient(urlBuilder, new ValidateProgramErrorHandler());		
		
		ProgramsDTO objProgramDTO = new ProgramsDTO();
		objProgramDTO.setCode(code);
		objProgramDTO.setProgramName(programname);
		objProgramDTO.setStatus(status);
		objProgramDTO.setType(type);

		try {
			
			result = client.submitRequest(Operation.POST, objProgramDTO, null, ProgramsDTO.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// This line is for fetching entire result
		 // -- significance of the
																							// last two paramaters
		/*if(result == null) {
			
		}	*/																					// passed.

		// ProgramDTO newPro = result.getBody();
		// String returnResult = createXSSInputInJson(result.getBody());

		
			return result;
	
	}

	public String MultipleProgramCreated() throws Exception {

		for (int i = 0; i < NumberOfProgramsToBecreated; i++) {

			// Hit the API
			APIUrlBuilder urlBuilder = new APIUrlBuilder(domainName, API.PROGRAMS, null, false);
			// To ping the required URL.
			ApiServiceClient client = new ApiServiceClient(urlBuilder, new ValidateProgramErrorHandler());

			ProgramsDTO objProgramDTO = new ProgramsDTO();
			objProgramDTO.setCode(code);
			objProgramDTO.setProgramName(programname);
			objProgramDTO.setStatus(status);
			objProgramDTO.setType(type);

			// This line is for fetching entire result
			result = client.submitRequest(Operation.POST, objProgramDTO, null, ProgramsDTO.class); // -- significance of
																									// the last two
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet();
			CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget);
			// paramaters
			closebaleHttpResponse.getStatusLine().getStatusCode();																			// passed.

			// List<ResponseEntity<ProgramsDTO>> resultfinal = new
			// ArrayList<ResponseEntity<ProgramsDTO>>();
			resultfinal.add(result);
		}

		statusCodes = new ArrayList<String>();
		for (ResponseEntity<ProgramsDTO> responseEntity : resultfinal) {
			statusCodes.add(responseEntity.getStatusCode().toString());
		}

		return statusCodes.toString();
	}
	
	
	
	
	public String HttpStatusCodes() {
		
		if (result!=null) {
			return this.result.getStatusCode().toString();
		} else {
			return "400";
		}
		
	}

	public String NameOfProgramCreated() {
		if (result!=null) {
			return this.result.getBody().getProgramName();
		} else {
			return "Error";
		}
		
		
	}

	public String CodeforProgramcreated() {
		return this.result.getBody().getCode();
	}

	public String StatusforProgramcreated() {
		return this.result.getBody().getStatus();
	}

	public String typeoftheprogramCreated() {
		return this.result.getBody().getType();
	}

	private String createXSSInputInJson(ProgramsDTO programs2) {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = "";
		try {
			jsonStr = mapperObj.writeValueAsString(programs2);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	class ValidateProgramErrorHandler implements ErrorHandler {
		public void handleError(HttpClientErrorException e) throws Exception {
			if(e.getStatusCode().value() == HttpStatus.SC_BAD_REQUEST) {
				throw new Exception(e.getStatusCode().toString());
			}
			throw new Exception(e.getStatusCode().getReasonPhrase());
		}
	}

	public Page<ProgramsDTO> makeRequest(String json) throws JsonParseException, JsonMappingException, IOException {
		Page<ProgramsDTO> pg = new ObjectMapper().readValue(json, CustomPageImpl.class);
		return pg;
	}

}
