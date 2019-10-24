package com.servicedx.source.app.model;

import java.io.Serializable;

public class EventRecord implements Serializable {

		
	private Long eventId;
	private String eventRecordId;
	private Long applicationId;	
	private Long assetId;
	private String eventName;
	private String applicationName;
	private String assetName;
	private String payload;
	private String tenantId;
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventRecordId() {
		return eventRecordId;
	}
	public void setEventRecordId(String eventRecordId) {
		this.eventRecordId = eventRecordId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getAssetId() {
		return assetId;
	}
	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}
	public EventRecord() {
		super();
	
	}
	
	


}
