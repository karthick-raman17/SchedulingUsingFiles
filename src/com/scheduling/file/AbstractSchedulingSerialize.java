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
	Patient pat;
	Appointment appointment;

	String doctorFilePath = "/Users/Shared/SchedulingFolder/Doctor.txt";
	String patientFilePath = "/Users/Shared/SchedulingFolder/Patient.txt";
	String slotFilePath = "/Users/Shared/SchedulingFolder/Slots.txt";
	String apptFilePath = "/Users/Shared/SchedulingFolder/Appointments.txt";

	LinkedList<Patient> patientDeserializeObject = new LinkedList<>();
	List<Patient> patientSerializeObject = new LinkedList<>();
	LinkedList<Appointment> apptSerializeObject = new LinkedList<>();
	LinkedList<Appointment> apptDeserializeObject = new LinkedList<>();
	HashMap<String, ArrayList<String>> staffSlotDeserializeList = new HashMap<>();
	HashMap<String, ArrayList<String>> staffSlotList = new HashMap<>();

	public void staffSerialize(LinkedList<Doctor> doctorList) {

		try {

			File docFile = new File(doctorFilePath);
			docFile.createNewFile();

			FileOutputStream file = new FileOutputStream(docFile);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(doctorList);

			out.close();

			file.close();

			System.out.println("Staff details stored");

		} catch (IOException ex) {

			System.out.println("IOException is caught when writing");

		}

	}

	public void patientSerialize(Patient patient) {
		FileOutputStream file = null;
		ObjectOutputStream out = null;
		try {

			File patientFile = new File(patientFilePath);

			if (patientFile.createNewFile()) {
				file = new FileOutputStream(patientFile);
				out = new ObjectOutputStream(file);
				patientSerializeObject.add(patient);
				out.writeObject(patientSerializeObject);
			}

			else {
				file = new FileOutputStream(patientFile);
				out = new ObjectOutputStream(file);

				if (patientDeserialize() != null) {
					patientSerializeObject.addAll(patientDeserialize());
					patientSerializeObject.add(patient);
				} else {
					patientSerializeObject.add(patient);
				}
				out.writeObject(patientSerializeObject);
			}

			out.close();
			file.close();

			System.out.println("Patient details stored");

		} catch (IOException ex) {

			System.out.println("IOException is caught when writing");

		}
	}

	public LinkedList<Patient> patientDeserialize() {
		try {

			FileInputStream file = new FileInputStream(patientFilePath);

			ObjectInputStream out = new ObjectInputStream(file);

			boolean cont = true;
			while (cont) {
				patientDeserializeObject = (LinkedList<Patient>) out.readObject();
				if (pat != null) {
					patientDeserializeObject.add(pat);
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

	public void slotSerialize(String doctorID, ArrayList<String> slot) {

		FileOutputStream file = null;
		ObjectOutputStream out = null;
		try {

			File slotFile = new File(slotFilePath);

			if (slotFile.createNewFile()) {
				file = new FileOutputStream(slotFile);
				out = new ObjectOutputStream(file);
				// staffSlotList.put(doctorID, slot);

				out.writeObject(staffSlotList);
			}

			else {
				file = new FileOutputStream(slotFile);
				out = new ObjectOutputStream(file);
				if (slotDeserialize() != null) {
					staffSlotList.putAll(slotDeserialize());
					staffSlotList.put(doctorID, slot);
				} else {
					staffSlotList.put(doctorID, slot);
				}
				out.writeObject(staffSlotList);
			}
			out.close();

			file.close();

		} catch (IOException ex) {

			System.out.println("IOException is caught when writing");

		}
	}

	public HashMap<String, ArrayList<String>> slotDeserialize() {
		try {

			FileInputStream file = new FileInputStream(slotFilePath);

			ObjectInputStream out = new ObjectInputStream(file);

			boolean cont = true;
			while (cont) {
				staffSlotDeserializeList = (HashMap<String, ArrayList<String>>) out.readObject();
				if (pat != null) {
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

	public void appointmentSerialize(Appointment appointment) {

		FileOutputStream file = null;
		ObjectOutputStream out = null;
		try {

			File appointmentFile = new File(apptFilePath);

			if (appointmentFile.createNewFile()) {
				file = new FileOutputStream(appointmentFile);
				out = new ObjectOutputStream(file);
				apptSerializeObject.add(appointment);
				out.writeObject(apptSerializeObject);
			}

			else {
				file = new FileOutputStream(appointmentFile);
				out = new ObjectOutputStream(file);

				if (patientDeserialize() != null) {
					apptSerializeObject.addAll(appointmentDeserialize());
					apptSerializeObject.add(appointment);
				} else {
					apptSerializeObject.add(appointment);
				}
				out.writeObject(apptSerializeObject);
			}
			out.close();

			file.close();

			System.out.println("Appointment details saved");

		} catch (IOException ex) {

			System.out.println("IOException is caught when writing");

		}
	}

	public LinkedList<Appointment> appointmentDeserialize() {
		try {

			FileInputStream file = new FileInputStream(apptFilePath);

			ObjectInputStream out = new ObjectInputStream(file);

			boolean cont = true;
			while (cont) {
				apptDeserializeObject = (LinkedList<Appointment>) out.readObject();
				if (appointment != null) {
					apptDeserializeObject.add(appointment);
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
		return apptDeserializeObject;

	}
}
