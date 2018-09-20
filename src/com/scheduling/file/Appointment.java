package com.scheduling.file;

class Appointment implements java.io.Serializable {

	private String slotTime;
	
	private String doctorID;
	
	private String patientID;
	
	private String consultationID;
	
	private String apptointmentID;

	public Appointment(String apptointmentID, String slotTime, String doctorID, String patientID, String consultationID) {	
		
		this.apptointmentID = apptointmentID;
		this.slotTime = slotTime;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.consultationID = consultationID;
	}

	@Override
	public String toString() {
		return "Appointment [ apptointmentID=" + apptointmentID+ ", doctorID=" + doctorID + ", patientID=" + patientID
				+ ", consultationID=" + consultationID + ", slotTime= " + slotTime + "]";
	}

	
	
	
}
