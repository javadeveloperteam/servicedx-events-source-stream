package com.servicedx.source.app.service;

import java.lang.invoke.MethodHandles;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.concurrent.ListenableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servicedx.source.app.model.EventRecord;

@Service
public class EventRecordSourceService {
	
	@Value("${kafka.events.topic}")
	private String eventsTopic;
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final KafkaTemplate<String, String> kafkaTemplate;
	private static final ObjectMapper mapper = new ObjectMapper();

	public EventRecordSourceService(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	
	public void publishEvent(EventRecord eventRecord) throws Exception {
		Assert.notNull(eventRecord, "event cannot be null");
		if(logger.isInfoEnabled()) {
			logger.info("event published with record {}", Objects.toString(eventRecord));
		}
		
		ListenableFuture<SendResult<String, String>> record = this.kafkaTemplate.send(eventsTopic,eventRecord.getApplicationName(),mapper.writeValueAsString(eventRecord));
		record.addCallback( result -> {
			logger.info("event result offset {}",result.getRecordMetadata().offset());
		}, error -> {
			logger.error("error in producing the event {}",error);
		});
	}
	
	

}
