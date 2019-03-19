package com.clamaud.pulsar;

import java.util.stream.IntStream;

import org.apache.pulsar.client.api.CompressionType;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageBuilder;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerTurorial {

	private static final String SERVICE_URL = "pulsar://10.1.1.1:6650";
	private static final String TOPIC_NAME = "test-topic";
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(ProducerTurorial.class);
	
	public static void main(String[] args) throws PulsarClientException {

		PulsarClient client = PulsarClient.builder()
				  .serviceUrl(SERVICE_URL)
				  .build();
				 
		Producer<byte[]> producer = client.newProducer()
		  .topic(TOPIC_NAME)
		  .compressionType(CompressionType.LZ4)
		  .create();
		
		IntStream.range(1, 50).forEach(i -> {
		    String content = String.format("hi-pulsar-%d", i);
		    LOGGER.info(content);
		    Message<byte[]> msg = MessageBuilder.create()
		      .setContent(content.getBytes())
		      .build();
		    try {
				MessageId msgId = producer.send(msg);
			} catch (PulsarClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

}
