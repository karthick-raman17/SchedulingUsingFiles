package com.scheduling.file;

import java.util.LinkedList;

class Consultation {

	String name;
	String duration;
	String cost;
	LinkedList<Doctor> doctorList;
	String consultationID;

	Consultation(String consultationID, String name, String duration, String cost, LinkedList<Doctor> doctorList) {

		this.consultationID = consultationID;
		this.name = name;
		this.duration = duration;
		this.cost = cost;
		this.doctorList = doctorList;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Doctor> getStaffList() {
		return doctorList;
	}

	public void setStaffList(LinkedList<Doctor> doctorList) {
		this.doctorList = doctorList;
	}

}
