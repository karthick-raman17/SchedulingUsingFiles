package com.scheduling.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface SchedulingSerialize {

	void staffSerialize(LinkedList<Doctor> doctorList);

	void patientSerialize(Patient patient);

	LinkedList<Patient> patientDeserialize();

	void slotSerialize(String doctorID, ArrayList<String> slot);

	HashMap<String, ArrayList<String>> slotDeserialize();

	void appointmentSerialize(Appointment appt);

	LinkedList<Appointment> appointmentDeserialize();
}
