package com.hs.eai.monitor.project.model;

import java.io.Serializable;

public class ProjectsPlanning implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String jiraIssue;
	private String projectName;
	private String activity;
	private String assignee;
	private String description;
	private String remark;
	private String progress;
	private String week;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJiraIssue() {
		return jiraIssue;
	}
	public void setJiraIssue(String jiraIssue) {
		this.jiraIssue = jiraIssue;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getExcpectedHours() {
		return excpectedHours;
	}
	public void setExcpectedHours(String excpectedHours) {
		this.excpectedHours = excpectedHours;
	}
	private String excpectedHours;
	
	
	
	
}
