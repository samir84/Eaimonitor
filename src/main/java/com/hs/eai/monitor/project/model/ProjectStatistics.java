package com.hs.eai.monitor.project.model;

import java.io.Serializable;

public class ProjectStatistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProjectDetails projectDetails;
	private String percentOpenIssues;
	private String percentReopenIssues;
	private String percentResolved;
	private String percentClosed;
	private String inprogressIssues;
	
	public ProjectStatistics(ProjectDetails projectDetails){
		this.projectDetails = projectDetails;
	}
	
	public ProjectDetails getProjectDetails() {
		return projectDetails;
	}
	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}
	public String getPercentOpenIssues() {
		System.out.println("percentOpenIssues:"+percentOpenIssues);
		return percentOpenIssues;
	}
	public void setPercentOpenIssues(String percentOpenIssues) {
		this.percentOpenIssues = percentOpenIssues;
	}
	public String getPercentReopenIssues() {
		return percentReopenIssues;
	}
	public void setPercentReopenIssues(String percentReopenIssues) {
		this.percentReopenIssues = percentReopenIssues;
	}
	public String getPercentResolved() {
		return percentResolved;
	}
	public void setPercentResolved(String percentResolved) {
		this.percentResolved = percentResolved;
	}
	public String getPercentClosed() {
		return percentClosed;
	}
	public void setPercentClosed(String percentClosed) {
		this.percentClosed = percentClosed;
	}
	public String getInprogressIssues() {
		return inprogressIssues;
	}
	public void setInprogressIssues(String inprogressIssues) {
		this.inprogressIssues = inprogressIssues;
	}
	
	

}
