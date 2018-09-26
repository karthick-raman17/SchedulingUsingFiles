package com.scheduling.file;

import java.io.EOFException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AbstractSchedulingSerialize implements SchedulingSerialize {
	Patient patientObject;
	Appointment appointment;
	Doctor doctorObject;
	Consultation consultationObject;
	Slot slotObject;

	String doctorFilePath = "/Users/Shared/SchedulingFolder/Doctor.txt";
	String patientFilePath = "/Users/Shared/SchedulingFolder/Patient.txt";
	String slotFilePath = "/Users/Shared/SchedulingFolder/Slots.txt";
	String appointmentFilePath = "/Users/Shared/SchedulingFolder/Appointments.txt";
	String consultationFilePath = "/Users/Shared/SchedulingFolder/Consultation.txt";
	LinkedList<Patient> patientDeserializeObject = new LinkedList<>();
	List<Patient> patientSerializeObject = new LinkedList<>();

	LinkedList<Appointment> apptSerializeObject = new LinkedList<>();
	LinkedList<Appointment> apptListFromFile = new LinkedList<>();

	LinkedList<Doctor> doctorSerializeObject = new LinkedList<>();
	LinkedList<Doctor> doctorDeserializeObject = new LinkedList<>();

	HashMap<String, ArrayList<String>> staffSlotDeserializeList = new HashMap<>();
	HashMap<String, ArrayList<String>> doctorSlotList = new HashMap<>();

	LinkedList<Consultation> consultationDeserializeObject = new LinkedList<>();
	LinkedList<Consultation> consultationSerializeObject = new LinkedList<>();

	public void doctorSerialize(LinkedList<Doctor> doctorList) {
		
		try {
			doctorSerializeObject.clear();
			FileOutputStream file = new FileOutputStream(doctorFilePath,false);
			ObjectOutputStream out = new ObjectOutputStream(file);

			if (doctorDeserialize() != null || !(doctorDeserialize().isEmpty())) {
				doctorSerializeObject.addAll(doctorDeserialize());
				doctorSerializeObject.addAll(doctorList);
			} else {
				doctorSerializeObject.addAll(doctorList);
			}
			out.writeObject(doctorSerializeObject);
			//System.out.println("Staff details stored");
			out.close();
			file.close();
		} catch (IOException ex) {

			System.out.println("IOException is caught when writing");

		}

	}

	public LinkedList<Doctor> doctorDeserialize() {
		
		try {
			doctorDeserializeObject.clear();
			FileInputStream file = new FileInputStream(doctorFilePath);

			ObjectInputStream out = new ObjectInputStream(file);

			boolean cont = true;
			while (cont) {
				doctorDeserializeObject = (LinkedList<Doctor>) out.readObject();
				if (doctorObject != null) {
					doctorDeserializeObject.add(doctorObject);
				} else
					cont = false;
			}
			out.close();

			file.close();

		} catch (EOFException ex) {
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("ClassNotFoundException is caught");
		}
		return doctorDeserializeObject;
	}

	public void consultationSerialize(LinkedList<Consultation> consultationList) {

		try {
			consultationSerializeObject.clear();
			FileOutputStream file = new FileOutputStream(consultationFilePath);
			ObjectOutputStream out = new ObjectOutputStream(file);

			if (consultationDeserialize() != null || !(consultationDeserialize().isEmpty())) {
				consultationSerializeObject.addAll(consultationDeserialize());
				consultationSerializeObject.addAll(consultationList);
			} else {
				consultationSerializeObject.addAll(consultationList);
			}
			out.writeObject(consultationSerializeObject);
			out.close();

			file.close();

		} catch (IOException ex) {

			System.out.println("IOException is caught when writing");

		}
	}

	public LinkedList<Consultation> consultationDeserialize() {
		try {
			consultationDeserializeObject.clear();
			FileInputStream file = new FileInputStream(consultationFilePath);
			ObjectInputStream out = new ObjectInputStream(file);
			
			boolean cont = true;
			while (cont) {
				consultationDeserializeObject = (LinkedList<Consultation>) out.readObject();
				if (consultationObject != null) {
					consultationDeserializeObject.add(consultationObject);
				} else
					cont = false;
			}
			out.close();
			file.close();

		} catch (EOFException ex) {
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("ClassNotFoundException is caught");
		}
		return consultationDeserializeObject;

	}

	public void patientSerialize(LinkedList<Patient> patientList) {
		patientSerializeObject.clear();
		try {

			FileOutputStream file = new FileOutputStream(patientFilePath,false);
			ObjectOutputStream out = new ObjectOutputStream(file);

			if (patientDeserialize() != null || !(patientDeserialize().isEmpty())) {
				patientSerializeObject.addAll(patientDeserialize());
				patientSerializeObject.addAll(patientList);
			} else {
				patientSerializeObject.addAll(patientList);
			}
			out.writeObject(patientSerializeObject);
			out.close();
			file.close();
			//System.out.println("Patient details stored");
		} catch (IOException ex) {

			System.out.println("IOException is caught when writing");

		}
	}

	public LinkedList<Patient> patientDeserialize() {
		try {
			patientDeserializeObject.clear();
			
			FileInputStream file = new FileInputStream(patientFilePath);

			ObjectInputStream out = new ObjectInputStream(file);

			boolean cont = true;
			while (cont) {
				patientDeserializeObject = (LinkedList<Patient>) out.readObject();
				if (patientObject != null) {
					patientDeserializeObject.add(patientObject);
				} else
					cont = false;
			}
			out.close();

			file.close();

		} catch (EOFException ex) {
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("ClassNotFoundException is caught");
		}
		return patientDeserializeObject;

	}

	public void doctorSlotSerialize(HashMap<String, ArrayList<String>> doctorSlotList) {

		try {
		
			FileOutputStream file = new FileOutputStream(slotFilePath,false);
			ObjectOutputStream out = new ObjectOutputStream(file);

			if (doctorSlotDeserialize() != null || !(doctorSlotDeserialize().isEmpty())) {
				doctorSlotList.putAll(doctorSlotDeserialize());
				doctorSlotList.putAll(doctorSlotList);
			} else {
				doctorSlotList.putAll(doctorSlotList);
			}
			out.writeObject(doctorSlotList);

			out.close();

			file.close();

		} catch (IOException ex) {

			System.out.println("IOException is caught when writing");

		}

	}

	public HashMap<String, ArrayList<String>> doctorSlotDeserialize() {
		try {
			
			FileInputStream file = new FileInputStream(slotFilePath);

			ObjectInputStream out = new ObjectInputStream(file);

			boolean cont = true;
			while (cont) {
				staffSlotDeserializeList = (HashMap<String, ArrayList<String>>) out.readObject();
				if (slotObject != null) {
					staffSlotDeserializeList.putAll(staffSlotDeserializeList);
				} else
					cont = false;
			}
			out.close();

			file.close();

		} catch (EOFException ex) {
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {

			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("ClassNotFoundException is caught");
		}
		return staffSlotDeserializeList;

	}

	public void appointmentSerialize(LinkedList<Appointment> appointmentList) {

		try {
			apptSerializeObject.clear();
			FileOutputStream file = new FileOutputStream(appointmentFilePath,false);
			ObjectOutputStream out = new ObjectOutputStream(file);

			if (appointmentDeserialize() != null || !(appointmentDeserialize().isEmpty())) {
				apptSerializeObject.addAll(appointmentDeserialize());
				apptSerializeObject.addAll(appointmentList);
			} else {
				apptSerializeObject.addAll(appointmentList);
			}
			out.writeObject(apptSerializeObject);

			out.close();

			file.close();

		} catch (IOException ex) {

			System.out.println("IOException is caught when writing");

		}
	}

	public LinkedList<Appointment> appointmentDeserialize() {
		try {
			apptListFromFile.clear();
			FileInputStream file = new FileInputStream(appointmentFilePath);
			ObjectInputStream out = new ObjectInputStream(file);

			boolean cont = true;
			while (cont) {
				apptListFromFile = (LinkedList<Appointment>) out.readObject();
				if (appointment != null) {
					apptListFromFile.add(appointment);
				} else
					cont = false;
			}
			out.close();
			file.close();

		} catch (EOFException ex) {
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("ClassNotFoundException is caught");
		}
		return apptListFromFile;

	}
}
