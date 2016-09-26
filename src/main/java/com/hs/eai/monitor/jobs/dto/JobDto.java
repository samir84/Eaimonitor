package com.hs.eai.monitor.jobs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hs.eai.monitor.jobs.model.Job;

public class JobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @JsonProperty
	private Job job;
	private String countryCode;
	private String remark;
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
