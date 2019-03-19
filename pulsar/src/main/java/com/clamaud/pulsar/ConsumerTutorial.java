package com.clamaud.pulsar;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerTutorial {

	private static Logger LOGGER = LoggerFactory.getLogger(ConsumerTutorial.class);
	
	private static final String SERVICE_URL = "pulsar://localhost:6650";
	private static final String TOPIC_NAME = "test-topic";
	private static final String SUBSCRIPTION_NAME = "clamaud";
	
	public static void main(String[] args) throws PulsarClientException {
		
		
		PulsarClient client = PulsarClient.builder()
				  .serviceUrl(SERVICE_URL)
				  .build();
		
		
		Consumer<byte[]> consumer = client.newConsumer()
				  .topic(TOPIC_NAME)
				  .subscriptionType(SubscriptionType.Shared)
				  .subscriptionName(SUBSCRIPTION_NAME)
				  .subscribe();
		
		do {
			  // Wait for a message
			  Message msg = consumer.receive();

			  LOGGER.info(String.format("Message received: %s", new String(msg.getData())));
			  
			  // Acknowledge the message so that it can be deleted by the message broker
			  consumer.acknowledge(msg);
			} while (true);

		
	}

}
