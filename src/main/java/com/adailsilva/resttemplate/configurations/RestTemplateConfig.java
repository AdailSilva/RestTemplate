package com.adailsilva.resttemplate.configurations;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@PropertySource("classpath:application.properties")
public class RestTemplateConfig {

	@Autowired
	CloseableHttpClient httpClient;

	@Value("${api.scheme}")
	private String scheme;
	
	@Value("${api.host}")
	private String host;
	
	@Value("${api.port}")
	private int port;
	
	@Value("${api.path_one}")
	private String pathOne;
	
	@Value("${api.queryparam.key_one}")
	private String queryParamKeyOne;
	
	@Value("${api.queryparam.value_one}")
	private String queryParamValueOne;

	@Bean
	public RestTemplate restTemplate() {
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme(scheme)
				.host(host)
				.port(port)
				.path(pathOne)
				.queryParam(queryParamKeyOne, queryParamValueOne)
				.build();
		
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(uri.toUriString()));
		return restTemplate;
	}

	@Bean
	@ConditionalOnMissingBean
	public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setHttpClient(httpClient);
		return clientHttpRequestFactory;
	}
}
