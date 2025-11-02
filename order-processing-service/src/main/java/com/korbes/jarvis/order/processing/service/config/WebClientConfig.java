package com.korbes.jarvis.order.processing.service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
  private final WebClient.Builder webclient;
  @Value("${inventory.processing.service.base.url}")
  private String inventoryServiceBaseUrl;

  @Bean("inventoryProcessingWebClient")
  public WebClient inventoryProcessingWebClient() {
    return webclient.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .baseUrl(inventoryServiceBaseUrl).build();
  }

}
