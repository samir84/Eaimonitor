package com.hs.eai.monitor.project.model;

public class AccesActivity {

	private Integer id;
	private String activityName;
	private String activation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", activityName=" + activityName + ", activation=" + activation + "]";
	}
}
