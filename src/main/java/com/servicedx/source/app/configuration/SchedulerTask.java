package com.servicedx.source.app.configuration;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servicedx.source.app.model.EventRecord;
import com.servicedx.source.app.service.EventRecordSourceService;

@Component
public class SchedulerTask {
	

	@Autowired
	private EventRecordSourceService eventService;
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	//private final List<String> applications = Arrays.asList("app1","app2","app3","app4","app5");
	//private final List<String> events = Arrays.asList("event1","event2","event3","event4","event5");
	//private final int[] eventIds = new int[] {1,2,3,4,5};
	//private final List<String> assets = Arrays.asList("asset1","asset2","asset3","asset4","asset5");

    private final int[] applicationIds = new int[] {1018,1023};
	private final List<String> applications = Arrays.asList("App1","Washing Machine");
	private final List<String> events = Arrays.asList("event1","Spin Failure");
	private final int[] eventIds = new int[] {1015,1022};
	private final List<String> assets = Arrays.asList("asset1","asset2","asset3","asset4","asset5");
	private Random random = new Random();
	AtomicInteger counter = new AtomicInteger(1);
	private final ObjectMapper mapper = new ObjectMapper();

	@Scheduled(cron = "*/60 * * * * ?")
	public void scheduleTasks() {
		logger.info("task running {}",new Date());
		IntStream.range(0, 2).forEach( i -> {
			try {
				logger.info("Event Counter {}",counter.getAndIncrement());
				eventService.publishEvent(buildEventRecord());
			} catch (Exception e) {				
				e.printStackTrace();
			}
		});
	
	}
	
	public EventRecord buildEventRecord() {
		EventRecord eventRecord = new EventRecord();
		int appRandom = random();
		eventRecord.setApplicationId(Long.valueOf(applicationIds[appRandom]));
		eventRecord.setApplicationName(applications.get(appRandom));
		int eventRandom =random();
		eventRecord.setEventName(events.get(eventRandom));
		eventRecord.setEventId(Long.valueOf(eventIds[eventRandom]));
		Map<String, Map<String,Object>> payLoad = new HashMap<>();
		Map<String,Object> eventMap= new HashMap<>();
		eventMap.put("temperature", "30");
		payLoad.put("event", eventMap);
		
		try {
			eventRecord.setPayload(mapper.writeValueAsString(eventMap));
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		eventRecord.setTenantId("servicedx");
		int assetRandom =random();
		eventRecord.setAssetId(Long.valueOf(assetRandom));
		eventRecord.setAssetName(assets.get(random()));
		eventRecord.setEventRecordId(Objects.toString(UUID.randomUUID()));
		return eventRecord;

	}

	public int random(){
		return random.nextInt(2);
	}
	
}
