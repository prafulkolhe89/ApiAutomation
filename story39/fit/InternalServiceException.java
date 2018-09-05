package com.ibm.silverpop.sms.smpp.story39.fit;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class InternalServiceException extends Exception {

    private static final long serialVersionUID = -8245156039607966190L;
    private HttpStatus statusCode;
	
	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public InternalServiceException(HttpClientErrorException error) {
		super(error.getStatusText());
		statusCode = error.getStatusCode();
	}
}
