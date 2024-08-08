package com.cg.in.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

@Configuration
public class WireMockConfig {

	 @Value("${wiremock.server.port}")
	  private int wireMockServerPort;

	    @Bean(initMethod = "start", destroyMethod = "stop")
	    public WireMockServer wireMockServer() {
	        return new WireMockServer(WireMockConfiguration.wireMockConfig().port(wireMockServerPort));
	    }
}
