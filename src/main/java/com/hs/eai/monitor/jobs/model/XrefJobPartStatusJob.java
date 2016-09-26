package com.hs.eai.monitor.jobs.model;

// Generated 10-jul-2012 9:36:12 by Hibernate Tools 3.4.0.CR1

import java.util.Date;


public class XrefJobPartStatusJob implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5204916092936772401L;

	private Integer id;
	private Date timestamp;
	private Job job;
	private JobPart jobPart;
	private JobStatus jobStatus;
	private Integer recordcount = 0;
	
	public XrefJobPartStatusJob() {
	}

	public XrefJobPartStatusJob(int id, Job job, JobPart jobPart,
			JobStatus jobStatus) {
		this.id = id;
		this.job = job;
		this.jobPart = jobPart;
		this.jobStatus = jobStatus;
	}

	public XrefJobPartStatusJob(int id, Job job, JobPart jobPart,
			JobStatus jobStatus, Integer recordcount) {
		this.id = id;
		this.job = job;
		this.jobPart = jobPart;
		this.jobStatus = jobStatus;
		this.recordcount = recordcount;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public JobPart getJobPart() {
		return this.jobPart;
	}

	public void setJobPart(JobPart jobPart) {
		this.jobPart = jobPart;
	}

	public JobStatus getJobStatus() {
		return this.jobStatus;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Integer getRecordcount() {
		return  recordcount;
	}

	public void setRecordcount(Integer recordcount) {
		 
		this.recordcount = recordcount;
	}

}
