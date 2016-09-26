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

@Entity
@Table(name = "Employees")
@NamedQueries({ 
		@NamedQuery(name = "Employee.findById", query = "select emp from Employee emp where emp.id=:id"),
		@NamedQuery(name = "Employee.findByName", query = "select emp from Employee emp where emp.name=:name"),
		@NamedQuery(name = "Employee.findByWindowsUserId", query = "select emp from Employee emp where emp.windowsUser=:windowsUser"),
		@NamedQuery(name = "Employee.findAll", query = "select emp from Employee emp ") })
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "numeric", name = "empl_id")
	private Integer id;

	@Column(name = "empl_naam")
	private String name;

	@Column(name = "empl_wuser")
	private String windowsUser;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWindowsUser() {
		return windowsUser;
	}

	public void setWindowsUser(String windowsUser) {
		this.windowsUser = windowsUser;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", windowsUser=" + windowsUser + "]";
	}

}
