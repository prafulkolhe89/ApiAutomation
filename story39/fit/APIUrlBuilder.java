package com.ibm.silverpop.sms.smpp.story39.fit;

public class APIUrlBuilder {
	private final String SLASH = "/";
	StringBuffer path;
	String dataDomain;
	APIEndpoint apiEndpoint;
	API api;

	public APIUrlBuilder(String dataDomain, API api, APIEndpoint endpoint) {
		this.path = new StringBuffer();
		this.dataDomain = dataDomain;
		this.apiEndpoint = endpoint;
		this.api = api;
		buildBasePath(true);
	}

	public APIUrlBuilder(String dataDomain, API api, APIEndpoint apiEndpoint, boolean includeVersion) {
		this.dataDomain = dataDomain;
		this.apiEndpoint = apiEndpoint;
		this.path = new StringBuffer();
		this.api = api;
		buildBasePath(includeVersion);
	}

	public APIUrlBuilder append(String key, String param) {
		path.append(SLASH).append(key).append(SLASH).append(param);
		return this;
	}

	APIUrlBuilder buildBasePath(boolean includeVersion) {
		if (includeVersion) {
			path.append(SLASH).append("v1");

		}

		path.append(SLASH).append(api.getName());

		if (apiEndpoint != null) {
			path.append(SLASH).append(apiEndpoint.toString());
		}
		return this;
	}

	public String getPath() {
		return path.toString();
	}

	public String getDataDomain() {
		return dataDomain;
	}

	public enum API {
		MESSAGESTATUSSERVICE {
			@Override
			String getName() {
				return "messageStatusService";
			}
		},
		PROGRAMS {
			@Override
			String getName() {
				// return value should match with the base parameter passed in the API.
				return "programs";
			}
		};

		abstract String getName();
	}

	public enum APIEndpoint {
		obfuscate
	}
}
