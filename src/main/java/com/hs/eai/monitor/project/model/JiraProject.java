package com.hs.eai.monitor.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;

public class JiraProject implements Serializable {

private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer assigneetype;
	private Integer avatar;
	private String description;
	private String lead;
	private String originalkey;
	private Integer pcounter;
	private String pkey;
	private String pname;
	private String projecttype;
	private String url;

	public JiraProject() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAssigneetype() {
		return this.assigneetype;
	}

	public void setAssigneetype(Integer assigneetype) {
		this.assigneetype = assigneetype;
	}

	public Integer getAvatar() {
		return this.avatar;
	}

	public void setAvatar(Integer avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLead() {
		return this.lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}

	public String getOriginalkey() {
		return this.originalkey;
	}

	public void setOriginalkey(String originalkey) {
		this.originalkey = originalkey;
	}

	public Integer getPcounter() {
		return this.pcounter;
	}

	public void setPcounter(Integer pcounter) {
		this.pcounter = pcounter;
	}

	public String getPkey() {
		return this.pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getProjecttype() {
		return this.projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", assigneetype=" + assigneetype + ", avatar=" + avatar + ", description="
				+ description + ", lead=" + lead + ", originalkey=" + originalkey + ", pcounter=" + pcounter + ", pkey="
				+ pkey + ", pname=" + pname + ", projecttype=" + projecttype + ", url=" + url + "]";
	}


}
