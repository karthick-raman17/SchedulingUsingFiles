package com.scheduling.file;

class Appointment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 701547795147570024L;

	private String slotTime;
	
	private String doctorID;
	
	private String patientID;

	private String consultationID;
	
	private String apptointmentID;

	private String appointmentCost;
	public Appointment(String apptointmentID,String doctorID,String consultationID, String patientID, String slotTime,String appointmentCost) {	
		
		this.apptointmentID = apptointmentID;
		this.slotTime = slotTime;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.consultationID = consultationID;
		this.appointmentCost = appointmentCost;
	}

	public String getAppointmentCost() {
		return appointmentCost;
	}

	public void setAppointmentCost(String appointmentCost) {
		this.appointmentCost = appointmentCost;
	}

	public String getSlotTime() {
		return slotTime;
	}

	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}

	public String getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getConsultationID() {
		return consultationID;
	}

	public void setConsultationID(String consultationID) {
		this.consultationID = consultationID;
	}

	
	@Override
	public String toString() {
		return "Appointment [ apptointmentID=" + apptointmentID+ ", doctorID=" + doctorID + ", patientID=" + patientID
				+ ", consultationID=" + consultationID + ", slotTime= " + slotTime + "]";
	}

	
	
	
}
