package com.hs.eai.monitor.project.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import com.hs.eai.monitor.project.model.AccesActivity;
import com.hs.eai.monitor.project.model.AccessProject;
import com.hs.eai.monitor.project.model.Employee;



public class WeeklyReturnDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID id = UUID.randomUUID();
	public UUID getId() {
		return id;
	}
	private Employee employee;
	private AccessProject project;
	private AccesActivity activity;
	private Date date;
	private Double hours;
	private String description;
	private String deletionFlag;
	private Integer reportingActivity;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public AccessProject getProject() {
		return project;
	}
	public void setProject(AccessProject project) {
		this.project = project;
	}
	public AccesActivity getActivity() {
		return activity;
	}
	public void setActivity(AccesActivity activity) {
		this.activity = activity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getHours() {
		return hours;
	}
	public void setHours(Double hours) {
		this.hours = hours;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeletionFlag() {
		return deletionFlag;
	}
	public void setDeletionFlag(String deletionFlag) {
		this.deletionFlag = deletionFlag;
	}
	public Integer getReportingActivity() {
		return reportingActivity;
	}
	public void setReportingActivity(Integer reportingActivity) {
		this.reportingActivity = reportingActivity;
	}
	
		
}
