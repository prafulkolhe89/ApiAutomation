package com.ibm.silverpop.sms.smpp.story39.fit;

import org.springframework.web.client.HttpClientErrorException;

public interface ErrorHandler {

	void handleError(HttpClientErrorException e) throws Exception;

}
