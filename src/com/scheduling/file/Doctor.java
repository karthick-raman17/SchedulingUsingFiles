package com.scheduling.file;

class Doctor implements java.io.Serializable {

	private static final long serialVersionUID = -848169749987438985L;

	String doctorID;
	int startHour;
	int endHour;
	String name;
	int duration;
	
	public Doctor(String doctorID,String name, int startHour, int endHour, int duration) {

		if ((endHour - startHour) > 24) {
			return;
		}
		this.doctorID = doctorID;
		this.name = name;
		this.startHour = startHour;
		this.endHour = endHour;
		this.duration = duration;
	}
}
