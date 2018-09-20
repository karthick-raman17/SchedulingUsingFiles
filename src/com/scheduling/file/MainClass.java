package com.scheduling.file;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.UUID;

public class MainClass extends AbstractSchedulingSerialize {
	static Scanner scanObject = new Scanner(System.in);
	String customerName;
	String customerEmail;
	String customerID;
	String appointmentID;
	int customerAge;

	LinkedList<Doctor> currentService = new LinkedList<>();

	LinkedList<Doctor> staffList = new LinkedList<>();

	LinkedList<Patient> patientList = new LinkedList<>();

	ArrayList<Consultation> serviceList = new ArrayList<>();

	ArrayList<String> slots = new ArrayList<>();

	Doctor karthickObject = new Doctor("1", "Karthick", 7, 14, 4);
	Doctor ramanObject = new Doctor("2", "Raman", 14, 21, 4);
	Doctor satyaObject = new Doctor("3", "Satya", 8, 17, 4);

	Slots karthickSlot = new Slots(karthickObject.doctorID, karthickObject.startHour, karthickObject.endHour, karthickObject.slotsPerHour);
	Slots ramanSlot = new Slots(ramanObject.doctorID, ramanObject.startHour, ramanObject.endHour, ramanObject.slotsPerHour);
	Slots satyaSlot = new Slots(satyaObject.doctorID, satyaObject.startHour, satyaObject.endHour, satyaObject.slotsPerHour);

	Consultation consultation1 = new Consultation("1", "Cardiologists", "15", "100$", staffList);
	Consultation consultation2 = new Consultation("2", "Neurologists", "15", "100$", staffList);
	Consultation consultation3 = new Consultation("3", "Orthopaedic", "15", "100$", staffList);
	Consultation consultation4 = new Consultation("4", "ENT", "15", "100$", staffList);
	Consultation consultation5 = new Consultation("5", "Skin specialists", "15", "100$", staffList);

	public void addStaff() {
		staffList.add(karthickObject);
		staffList.add(ramanObject);
		staffList.add(satyaObject);
		staffSerialize(staffList);
	}

	public void addConsultation() {
		serviceList.add(consultation1);
		serviceList.add(consultation2);
		serviceList.add(consultation3);
		serviceList.add(consultation4);
		serviceList.add(consultation5);
	}

	public void genSlots() {
		slotSerialize(karthickSlot.doctorID, karthickSlot.getAllSlotsInString());
		slotSerialize(ramanSlot.doctorID, ramanSlot.getAllSlotsInString());
		slotSerialize(satyaSlot.doctorID, satyaSlot.getAllSlotsInString());
	}

	public void displayService() {
		System.out.println("\nAvailable Consultations:\n");
		for (int serviceIndex = 0; serviceIndex < serviceList.size(); serviceIndex++) {
			System.out.println(serviceIndex + 1 + ". " + serviceList.get(serviceIndex).getName());
		}
	}

	public void displayDoctor(int selectedConsultation) {
		currentService = serviceList.get(selectedConsultation - 1).getStaffList();
		System.out.println("\nAvailable Doctors:\n");
		for (int staffIndex = 0; staffIndex < currentService.size(); staffIndex++) {
			System.out.println(staffIndex + 1 + "." + currentService.get(staffIndex).name);
		}
	}

	public void displayAvailableSlots(int selectedDoctor) {
		slots = slotDeserialize().get(currentService.get(selectedDoctor - 1).doctorID);
		if (slots == null) {
			genSlots();
			slots = slotDeserialize().get(currentService.get(selectedDoctor - 1).doctorID);
		}
		for (int i = 0; i < slots.size(); i++) {
			System.out.println(i + 1 + "->" + slots.get(i));
		}
	}

	public void schedule(int selectedDoctor, int selectedSlot, int selectedConsultation) {

		System.out.println("Provide your informations:\n");
		System.out.print("Name :");
		customerName = scanObject.next();
		System.out.print("Email: ");
		customerEmail = scanObject.next();
		System.out.print("Age: ");
		customerAge = scanObject.nextInt();
		customerID = generateUUID();
		Patient patient = new Patient(customerID, customerName, customerEmail, customerAge);
		patientDeserialize();
		patientSerialize(patient);

		System.out.println("Slot booked sucessfully!!");

		appointmentID = generateUUID();
		Appointment appt = new Appointment(appointmentID, slots.remove(selectedSlot - 1),
				Integer.toString(selectedDoctor), customerID, Integer.toString(selectedConsultation));
		appointmentDeserialize();
		appointmentSerialize(appt);
		slotSerialize(Integer.toString(selectedDoctor), slots);

		System.out.println(appointmentDeserialize());
		System.out.println(patientDeserialize());

	}

	public static void main(String[] args) {

		int chooseConsultation;
		int chooseDoctor;
		int chooseSlot;

		MainClass mainClassObject = new MainClass();

		mainClassObject.addStaff();

		mainClassObject.addConsultation();

		mainClassObject.displayService();

		System.out.println("\nSelect the consultation:");

		chooseConsultation = scanObject.nextInt();

		mainClassObject.displayDoctor(chooseConsultation);

		System.out.println("\n Select the doctor:");

		chooseDoctor = scanObject.nextInt();

		mainClassObject.displayAvailableSlots(chooseDoctor);

		System.out.println("\n Select the slot:");

		chooseSlot = scanObject.nextInt();

		mainClassObject.schedule(chooseDoctor, chooseSlot, chooseConsultation);

	}

	private String generateUUID() {
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		return randomUUIDString;
	}

	static {
		File schedulingDirectory = new File("/Users/Shared/SchedulingFolder");

		try {
			schedulingDirectory.mkdir();
		} catch (SecurityException SecExp) {
			System.out.println("Error while creating directory :" + SecExp);
		}

	}

}
