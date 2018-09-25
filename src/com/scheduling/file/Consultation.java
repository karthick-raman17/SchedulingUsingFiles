package com.scheduling.file;

import java.util.LinkedList;

class Consultation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5766666307286847346L;
	String name;
	String duration;
	String cost;
	LinkedList<String> assingedDoctors;
	String id;

	Consultation(String id, String name, String duration, String cost, LinkedList<String> assingedDoctors) {

		this.id = id;
		this.name = name;
		this.duration = duration;
		this.cost = cost;
		this.assingedDoctors = assingedDoctors;

	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public LinkedList<String> getAssingedDoctors() {
		return assingedDoctors;
	}

	public void setAssingedDoctors(LinkedList<String> assingedDoctors) {
		this.assingedDoctors = assingedDoctors;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
