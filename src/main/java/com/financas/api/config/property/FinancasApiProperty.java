package com.financas.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("financas-api")
public class FinancasApiProperty {
	
	private String originAllowed = "http://127.0.0.1:5500";

	private final SecurityApi security = new SecurityApi();

	public SecurityApi getSecurityApi() {
		return security;
	}

	public String getOriginAllowed() {
		return originAllowed;
	}

	public void setOriginAllowed(String originAllowed) {
		this.originAllowed = originAllowed;
	}



	public static class SecurityApi {

		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

	}

}
