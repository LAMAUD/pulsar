package com.clamaud.demo.configuration;

import org.apache.pulsar.client.api.CompressionType;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
	private static final String SERVICE_URL = "pulsar://10.1.1.1:6650";
	private static final String TOPIC_NAME = "test-topic";
	
	@Bean
    public Producer<byte[]> producer() throws PulsarClientException {
		
		PulsarClient client = PulsarClient.builder()
				  .serviceUrl(SERVICE_URL)
				  .build();
				 
		Producer<byte[]> producer = client.newProducer()
		  .topic(TOPIC_NAME)
		  .compressionType(CompressionType.LZ4)
		  .create();
		
		return producer;
    }
}
