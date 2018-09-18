package com.scheduling.file;

class Appointment implements java.io.Serializable {

	private String slotTime;
	
	private String doctorID;
	
	private String patientID;
	
	private String consultationID;

	public Appointment(String slotTime, String doctorID, String patientID, String consultationID) {	
		this.slotTime = slotTime;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.consultationID = consultationID;
	}

	@Override
	public String toString() {
		return "Appointment [slotTime=" + slotTime + ", doctorID=" + doctorID + ", patientID=" + patientID
				+ ", consultationID=" + consultationID + "]";
	}
	
	
}
