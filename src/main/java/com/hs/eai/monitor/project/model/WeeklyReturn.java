package com.hs.eai.monitor.project.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class WeeklyReturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer employee;
	@NotNull
	private Integer project;
	@NotNull
	private Integer activity;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date date;
	@NotNull
	@DecimalMax("10.0") @DecimalMin("0.0") 
	private Double hours;
	@NotEmpty
	private String description;
	private String deletionFlag;
	private Integer reportingActivity;

	public Integer getEmployee() {
		return employee;
	}

	public WeeklyReturn() {

	}

	public void setEmployee(Integer employee) {
		this.employee = employee;
	}

	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		// super.setStart(Date.toLocaleString());
		// super.setEnd(Date.toLocaleString());
		// super.setId((int)Date.getTime());
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
		// super.setTitle(description);
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

	@Override
	public String toString() {
		return "WeeklyReturnDto [employee=" + employee + ", project=" + project + ", activity=" + activity + ", Date="
				+ date + ", hours=" + hours + ", description=" + description + ", deletionFlag=" + deletionFlag
				+ ", reportingActivity=" + reportingActivity + "]";
	}

}
