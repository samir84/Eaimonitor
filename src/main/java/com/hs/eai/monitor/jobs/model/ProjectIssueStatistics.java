package com.hs.eai.monitor.jobs.model;

import java.io.Serializable;

public class ProjectIssueStatistics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String year;
	private String month;
	private Integer totalTimeOrgEstimate;
	private Integer totalTimeSpent;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getTotalTimeOrgEstimate() {
		return totalTimeOrgEstimate;
	}
	public void setTotalTimeOrgEstimate(Integer totalTimeOrgEstimate) {
		this.totalTimeOrgEstimate = totalTimeOrgEstimate;
	}
	public Integer getTotalTimeSpent() {
		return totalTimeSpent;
	}
	public void setTotalTimeSpent(Integer totalTimeSpent) {
		this.totalTimeSpent = totalTimeSpent;
	}
}
