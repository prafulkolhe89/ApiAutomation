package com.ibm.silverpop.sms.smpp.story39.fit;

import java.util.ArrayList;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;

public class ApiServiceClient extends BaseServiceClient {
    private static final String SCHEME = "http";
    protected String servicePath;
    protected MediaType acceptHeader;

    public ApiServiceClient(APIUrlBuilder urlBuilder) {
        this.servicePath = urlBuilder.getPath();
        this.errorHandler = new InternalServicesErrorHandler();
        this.host = urlBuilder.getDataDomain();
    }

    public ApiServiceClient(APIUrlBuilder urlBuilder, ErrorHandler errorHandler) {
        this.servicePath = urlBuilder.getPath();
        if (errorHandler == null) {
            this.errorHandler = new InternalServicesErrorHandler();
        }
        this.errorHandler = errorHandler;
        this.host = urlBuilder.getDataDomain();
    }

    @Override
    public URIBuilder getUriBuilder() {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME);
        uriBuilder.setHost(getHost());
        uriBuilder.setPath(servicePath);
        return uriBuilder;
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlJTMjU2OjEifQ.eyJ1c2VySWQiOiIxMTA0YjcyLTE1ZDQyYzY2YzljLWZlMTU0YTEzZTdhZmY2NjEyMDBlZGNmNzNhNjIzY2Q2IiwiZW1haWxBZGRyZXNzIjoic2hlbGx1c2VyQHVzLmlibS5jb20iLCJvcmdhbml6YXRpb25JZCI6IjEwYzM1MTUtMTJjY2M5NDQ2YjMtMjc2ZTAxNGQwY2E3MTM4OTQ4M2U2Mzk5ZjdmMDY5M2YiLCJvcmdhbml6YXRpb25OYW1lIjoiUmljaHRlciBEZXYiLCJhcGlVUkwiOiJodHRwczovL2VuZ2FnZS1xYTEuYWRtMDEuY29tIiwib2ZmZXJpbmdJZCI6IndjYSIsIm9mZmVyaW5nTmFtZSI6IkNhbXBhaWduIEF1dG9tYXRpb24iLCJpdWkiOiI1MTEyQUY1UEFNIiwiZXhwIjoxNTYzNjMxMjY5LCJpYXQiOjE1MzIwOTYwMzgsImlzcyI6IlJTMjU2Iiwic3ViIjoic2hlbGx1c2VyQHVzLmlibS5jb20ifQ.EVJrH5OS466N9F73JlKBcahKHXHlsCVbIFVsPf8n_J5KNWgIonPxxkKeKFHU-rudC3sAaRAVVm-Yw-fcFQ6N9nDOH74tZUnLTi6yE0lRTGoQu3xnj4KH9go4Fzg01lGYNIz_6un-TYECQmTWhlQIcyJM9CjBFUrjnoGvZel2Cx1lXbbAv08g7X1s5Y2jG1KAM6soPnM6BrYleQlUxrVwur8QabUCAtsVXY5rju6e-e1w-3rWU0qD3XKSZnEOQRChwx9Fm8Cpxsya-2AjRvM7oEpYFAzmVAVZHQDGjjlABqk0dD-XLLcPMpwRuZqt-6w-5tlcJnm07Z04_luC6BA9Uq7fYHlfOKUfP3zwnOMMAbQSkDqZ1sw-gnHLbNunK4k5q9VJgBmWX7IIfYU2IjLSN1G9RoVJVHT5AvG2mKI2znPS4kbgWZ86gAvahG8dpWOifI4ExEEoqYAWYO6ULsjwBkoBLQUq3kpkkblPA7VRv7hqA7DISlcLO9LQDTsScPhc2pyI1A8K6HOHKAZV5y4Z6_bvAYwojeg-iU9ZhowQhqEG_kC89-faQW7jJWX8454WlyQe2hc3uUM-rT9d3MPJU71t7t89882dwpGeN-mieR3NdI2DtYLUP-k3BxDakAVNgsEYvIVKoffI4ppIU6bAbDZ-Qdu8uCmASGP78diOPaA");
        
        ArrayList<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();

        if (acceptHeader != null) {
            acceptableMediaTypes.add(acceptHeader);
        } else {
            acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        }
        headers.setAccept(acceptableMediaTypes);
        return headers;
    }

    public void addAcceptHeader(MediaType acceptHeader) {
        this.acceptHeader = acceptHeader;
    }

}

class InternalServicesErrorHandler implements ErrorHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(ApiServiceClient.class);

    public void handleError(HttpClientErrorException e) throws Exception {
        LOGGER.info("Service exception : [responseCode: {}] [errorMessage: {}]",
                new Object[] { e.getStatusCode(), e.getStatusText() });
        throw new InternalServiceException(e);
    }

}
