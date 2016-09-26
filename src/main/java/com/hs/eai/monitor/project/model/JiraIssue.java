package com.hs.eai.monitor.project.model;

import java.io.Serializable;
import java.sql.Timestamp;


public class JiraIssue implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String pkey;
	private Integer issuenum;
	private Integer project;
	private String reporter;
	private String assignee;
	private Integer component;
	private Timestamp created;
	private String creator;
	private String description;
	private Timestamp duedate;
	private String environment;
	private Integer fixfor;
	private Long issuestatus;
	private String issuetype;
	private String priority;
	private String resolution;
	private Timestamp resolutiondate;
	private Integer security;
	private String summary;
	private Integer timeestimate;
	private Integer timeoriginalestimate;
	private Integer timespent;
	private Timestamp updated;
	private Integer votes;
	private Integer watches;
	private Integer workflowId;

	public JiraIssue() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAssignee() {
		return this.assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Integer getComponent() {
		return this.component;
	}

	public void setComponent(Integer component) {
		this.component = component;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDuedate() {
		return this.duedate;
	}

	public void setDuedate(Timestamp duedate) {
		this.duedate = duedate;
	}

	public String getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public Integer getFixfor() {
		return this.fixfor;
	}

	public void setFixfor(Integer fixfor) {
		this.fixfor = fixfor;
	}

	public Integer getIssuenum() {
		return this.issuenum;
	}

	public void setIssuenum(Integer issuenum) {
		this.issuenum = issuenum;
	}

	public Long getIssuestatus() {
		return this.issuestatus;
	}

	public void setIssuestatus(Long issuestatus) {
		this.issuestatus = issuestatus;
	}

	public String getIssuetype() {
		return this.issuetype;
	}

	public void setIssuetype(String issuetype) {
		this.issuetype = issuetype;
	}

	public String getPkey() {
		return this.pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Integer getProject() {
		return this.project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}

	public String getReporter() {
		return this.reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getResolution() {
		return this.resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public Timestamp getResolutiondate() {
		return this.resolutiondate;
	}

	public void setResolutiondate(Timestamp resolutiondate) {
		this.resolutiondate = resolutiondate;
	}

	public Integer getSecurity() {
		return this.security;
	}

	public void setSecurity(Integer security) {
		this.security = security;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getTimeestimate() {
		return this.timeestimate;
	}

	public void setTimeestimate(Integer timeestimate) {
		this.timeestimate = timeestimate;
	}

	public Integer getTimeoriginalestimate() {
		return this.timeoriginalestimate;
	}

	public void setTimeoriginalestimate(Integer timeoriginalestimate) {
		this.timeoriginalestimate = timeoriginalestimate;
	}

	public Integer getTimespent() {
		return this.timespent;
	}

	public void setTimespent(Integer timespent) {
		this.timespent = timespent;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Integer getVotes() {
		return this.votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Integer getWatches() {
		return this.watches;
	}

	public void setWatches(Integer watches) {
		this.watches = watches;
	}

	public Integer getWorkflowId() {
		return this.workflowId;
	}

	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}
}
