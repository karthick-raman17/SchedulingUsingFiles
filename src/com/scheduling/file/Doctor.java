package com.scheduling.file;

import java.util.LinkedList;

class Doctor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8532045537470477789L;
	String id;
	int startHour;
	int endHour;
	String name;
	int slotsPerHour;
	LinkedList<String> assignedConsultation = new LinkedList<>();
	
	public Doctor(String id,String name, int startHour, int endHour, int slotsPerHour, LinkedList<String> assignedConsultation) {

		if ((endHour - startHour) > 24) {
			return;
		}
		this.id = id;
		this.name = name;
		this.startHour = startHour;
		this.endHour = endHour;
		this.slotsPerHour = slotsPerHour;
		this.assignedConsultation = assignedConsultation;
	}

	
	public int getStartHour() {
		return startHour;
	}
	public String getId() {
		return id;
	}

	public int getSlotsPerHour() {
		return slotsPerHour;
	}

	public void setSlotsPerHour(int slotsPerHour) {
		this.slotsPerHour = slotsPerHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<String> getAssignedConsultation() {
		return assignedConsultation;
	}

	public void setAssignedConsultation(LinkedList<String> assignedConsultation) {
		this.assignedConsultation = assignedConsultation;
	}
	
	
}
