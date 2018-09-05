package com.ibm.silverpop.sms.smpp.story39.fit;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.kafka.common.requests.ResponseHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public abstract class BaseServiceClient extends RestTemplate {

    private static Logger log = LoggerFactory.getLogger(BaseServiceClient.class);

    protected static final String REQUEST_MSG = "executing request [url: {}], [header: {}] ";
    protected static final String SUCCESS_MSG = "Http request successful for request [url: {}], [executionTime: {} ms] ,  [statusCode: {}]";
    protected static final String FAILED_MSG = "Http request failed for request [url: {}], [executionTime: {} ms] ,  [statusCode/error: {}]";
    protected static final String URL_ERROR = "Http request failed due to invalid url [url: {}]";

    protected String host;
    protected ErrorHandler errorHandler;
    protected CloseableHttpClient closeableHttpClient;

    public BaseServiceClient() {
        super();
    }

    public String getHost() {
        return host;
    }

    protected abstract HttpHeaders getHeaders() throws Exception;

    protected abstract URIBuilder getUriBuilder();

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private HttpEntity<?> createHttpEntity(Object requestEntity) throws Exception {
        HttpHeaders headers = getHeaders();
        HttpEntity entity = new HttpEntity((requestEntity == null ? null : requestEntity), headers);
        return entity;
    }

    public <T> ResponseEntity<T> submitRequest(Operation operation, Object requestEntity,
            HashMap<String, String> queryParams, Class<T> responseType) throws Exception {
        URIBuilder uriBuilder = getUriBuilder();
        ResponseEntity<T> responseEntity = null;
       
        Long startTime = null;
        URI uri = null;

        try {
            addQueryParams(uriBuilder, queryParams);
            startTime = System.currentTimeMillis();
            uri = uriBuilder.build();
            HttpEntity<?> httpEntity = createHttpEntity(requestEntity);

            log.info(REQUEST_MSG, uri, httpEntity.getHeaders());
            startTime = System.currentTimeMillis();

            responseEntity = super.exchange(uri, operation.getMethod(), httpEntity, responseType);
            log.info(SUCCESS_MSG,
                    new Object[] { uri, timeTaken(startTime), responseEntity.getStatusCode().toString() });

            return responseEntity;
        } catch (HttpClientErrorException e) {
            log.info(FAILED_MSG, new Object[] { uri, timeTaken(startTime), e.getStatusCode().toString() });

            if (errorHandler != null) {
                errorHandler.handleError(e);
                return null;
            } else {
                throw e;
            }
        } catch (URISyntaxException e) {
            log.info(URL_ERROR, new Object[] { uri });
            return null;
        } catch (Exception e) {
            log.info(FAILED_MSG, new Object[] { uri, timeTaken(startTime), e.getMessage() });
            throw e;
        } finally {
            if (closeableHttpClient != null) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    closeableHttpClient = null;
                    if (log.isDebugEnabled()) {
                        log.debug("Error while closing connection to certainApp Services.", e);
                    }
                }
            }
        }
    }

    protected void addQueryParams(URIBuilder uriBuilder, HashMap<String, String> queryParams) {
        if (queryParams != null && !queryParams.isEmpty()) {
            for (String key : queryParams.keySet()) {
                String value = queryParams.get(key);

                if (value != null && !value.isEmpty()) {
                    uriBuilder.addParameter(key, value);
                }
            }
        }

    }

    protected long timeTaken(Long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    public enum Operation {
        GET {
            @Override
            HttpMethod getMethod() {
                return HttpMethod.GET;
            }
        },
        POST {
            @Override
            HttpMethod getMethod() {
                return HttpMethod.POST;
            }
        },
        DELETE {
            @Override
            HttpMethod getMethod() {
                return HttpMethod.DELETE;
            }
        },
        PUT {
            @Override
            HttpMethod getMethod() {
                return HttpMethod.PUT;
            }
        };
        abstract HttpMethod getMethod();
    }
}
