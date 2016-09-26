package com.hs.eai.monitor.jobs.model;

// Generated 2-jul-2012 15:30:42 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;




public class JobPartType implements java.io.Serializable {


	private int id;
	private String code;
	private String description;
	private boolean active;
	private Set<JobPart> jobParts = new HashSet<JobPart>(0);

	public JobPartType() {
	}

	public JobPartType(int id, String code, String description, boolean active) {
		this.id = id;
		this.code = code;
		this.description = description;
		this.active = active;
	}

	public JobPartType(int id, String code, String description, boolean active,
			Set<JobPart> jobParts) {
		this.id = id;
		this.code = code;
		this.description = description;
		this.active = active;
		this.jobParts = jobParts;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<JobPart> getJobParts() {
		return this.jobParts;
	}

	public void setJobParts(Set<JobPart> jobParts) {
		this.jobParts = jobParts;
	}

}
