package com.scheduling.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface SchedulingSerialize {

	void doctorSerialize(LinkedList<Doctor> doctorList);
	
	LinkedList<Doctor> doctorDeserialize();

	void patientSerialize(LinkedList<Patient> patientList);

	LinkedList<Patient> patientDeserialize();

	void doctorSlotSerialize(HashMap<String, ArrayList<String>> doctorSlotList);

	HashMap<String, ArrayList<String>> doctorSlotDeserialize();

	void appointmentSerialize(LinkedList<Appointment> appointmentList);

	LinkedList<Appointment> appointmentDeserialize();
}
