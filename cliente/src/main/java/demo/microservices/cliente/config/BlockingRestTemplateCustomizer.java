package demo.microservices.cliente.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Configuring Apache HTTP Client
 * 
 * @author eduardo
 *
 */
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
	
	private final Integer maxtotalconnections;
	private final Integer defaultmaxtotalconnections;
	private final Integer connectionRequestTimeout;
	private final Integer sockettimeout;

	public BlockingRestTemplateCustomizer(@Value("${sf.maxtotalconnections}") Integer maxtotalconnections, 
			@Value("${sf.defaultmaxtotalconnections}") Integer defaultmaxtotalconnections,
			@Value("${sf.connectionrequesttimeout}") Integer connectionRequestTimeout, 
			@Value("${sf.sockettimeout}") Integer sockettimeout) {
		this.maxtotalconnections = maxtotalconnections;
		this.defaultmaxtotalconnections = defaultmaxtotalconnections;
		this.connectionRequestTimeout = connectionRequestTimeout;
		this.sockettimeout = sockettimeout;
	}

	public ClientHttpRequestFactory clientHttpRequestFactory() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(maxtotalconnections);
		connectionManager.setDefaultMaxPerRoute(defaultmaxtotalconnections);

		RequestConfig requestConfig = RequestConfig
				.custom()
				.setConnectionRequestTimeout(connectionRequestTimeout)
				.setSocketTimeout(sockettimeout)
				.build();
		
		CloseableHttpClient httpClient = HttpClients
				.custom()
				.setConnectionManager(connectionManager)
				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
				.setDefaultRequestConfig(requestConfig)
				.build();
		
		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}
	
	@Override
	public void customize(RestTemplate restTemplate) {
		restTemplate.setRequestFactory(this.clientHttpRequestFactory());
	}

}
