package com.hs.eai.monitor.project.model;

import java.io.Serializable;

public class WorklogDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6469931418570278943L;
	private String employee;
	private int issuenum;
	private int project;
	private int activity;
	private String date;
	private Integer hours;
	private String description;
	
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public int getIssuenum() {
		return issuenum;
	}
	public void setIssuenum(int issuenum) {
		this.issuenum = issuenum;
	}
	public int getProjectId() {
		return project;
	}
	public void setProject(int projectId) {
		this.project = projectId;
	}
	public void setActivity(int activityId) {
		this.activity = activityId;
	}
	public int getActivity() {
		return activity;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
