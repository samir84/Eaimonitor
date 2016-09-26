package com.hs.eai.monitor.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class AccessProject {

	private Integer id;
	private String projectName;
	private String capex ;
	private Long proj_main;
	private String activation;
	private String deletionFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCapex() {
		return capex;
	}

	public void setCapex(String capex) {
		this.capex = capex;
	}

	public Long getProj_main() {
		return proj_main;
	}

	public void setProj_main(Long proj_main) {
		this.proj_main = proj_main;
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public String getDeletionFlag() {
		return deletionFlag;
	}

	public void setDeletionFlag(String deletionFlag) {
		this.deletionFlag = deletionFlag;
	}
	

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + ", capex=" + capex + ", proj_main=" + proj_main
				+ ", activation=" + activation + ", deletionFlag=" + deletionFlag + "]";
	}
}
