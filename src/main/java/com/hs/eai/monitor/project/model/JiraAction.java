package com.hs.eai.monitor.project.model;

import java.io.Serializable;
import java.sql.Timestamp;


public class JiraAction implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer id;
	private Integer issueid;
	private String author;
	private String actiontype;
	private String actionlevel;
	private String rolelevel;
	private String actionbody;
	private Timestamp created;
	private String updateauthor;
	private Timestamp updated;
	private String actionnum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIssueid() {
		return issueid;
	}
	public void setIssueid(Integer issueid) {
		this.issueid = issueid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	public String getActionlevel() {
		return actionlevel;
	}
	public void setActionlevel(String actionlevel) {
		this.actionlevel = actionlevel;
	}
	public String getRolelevel() {
		return rolelevel;
	}
	public void setRolelevel(String rolelevel) {
		this.rolelevel = rolelevel;
	}
	public String getActionbody() {
		
		actionbody = actionbody.replaceAll("(\\r|\\n|\\r\\n)+", "<br \\\\>");
		//String repl = str.replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")
		return actionbody;
	}
	public void setActionbody(String actionbody) {
		this.actionbody = actionbody;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public String getUpdateauthor() {
		return updateauthor;
	}
	public void setUpdateauthor(String updateauthor) {
		this.updateauthor = updateauthor;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public String getActionnum() {
		return actionnum;
	}
	public void setActionnum(String actionnum) {
		this.actionnum = actionnum;
	}
	
	
	
}