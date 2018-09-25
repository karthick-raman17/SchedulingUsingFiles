package com.scheduling.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.UUID;

import dnl.utils.text.table.TextTable;

public class MainClass extends AbstractSchedulingSerialize {
	static Scanner scanObject = new Scanner(System.in);

	LinkedList<Doctor> doctorList = new LinkedList<>();
	LinkedList<Patient> patientList = new LinkedList<>();
	LinkedList<Consultation> consultationList = new LinkedList<>();
	LinkedList<Appointment> appointmentList = new LinkedList<>();
	LinkedList<String> assignedServices = new LinkedList<>();
	LinkedList<String> assignedStaffs = new LinkedList<>();
	HashMap<String, ArrayList<String>> doctorSlotList = new HashMap<>();
	ArrayList<String> slots = new ArrayList<>();

	private void initializeSetup() throws IOException {
		String doctorFilePath = "/Users/Shared/SchedulingFolder/Doctor.txt";
		String slotFilePath = "/Users/Shared/SchedulingFolder/Slots.txt";
		String consultationFilePath = "/Users/Shared/SchedulingFolder/Consultation.txt";
		File doctorFile = new File(doctorFilePath);
		File consultationFile = new File(consultationFilePath);
		File slotFile = new File(slotFilePath);

		Doctor karthickObject = new Doctor("S" + generateUUID().substring(0, 10), "Karthick", 7, 14, 4, null);
		Doctor ramanObject = new Doctor("S" + generateUUID().substring(0, 10), "Raman", 14, 21, 4, null);
		Doctor satyaObject = new Doctor("S" + generateUUID().substring(0, 10), "Satya", 8, 17, 4, null);

		assignedStaffs.add(karthickObject.id);
		assignedStaffs.add(ramanObject.id);
		assignedStaffs.add(satyaObject.id);

		Consultation cardiologists = new Consultation("C" + generateUUID().substring(0, 8), "Cardiologists", "15",
				"100$", assignedStaffs);
		Consultation neurologists = new Consultation("C" + generateUUID().substring(0, 8), "Neurologists", "15", "100$",
				assignedStaffs);
		Consultation orthopaedic = new Consultation("C" + generateUUID().substring(0, 8), "Orthopaedic", "15", "100$",
				assignedStaffs);
		Consultation ENT = new Consultation("C" + generateUUID().substring(0, 8), "ENT", "15", "100$", assignedStaffs);
		Consultation skinSpecialists = new Consultation("C" + generateUUID().substring(0, 8), "Skin specialists", "15",
				"100$", assignedStaffs);

		assignedServices.add(cardiologists.id);
		assignedServices.add(neurologists.id);
		assignedServices.add(orthopaedic.id);
		assignedServices.add(ENT.id);
		assignedServices.add(skinSpecialists.id);

		// Initial assigning service for the default staffs
		karthickObject.setAssignedConsultation(assignedServices);
		ramanObject.setAssignedConsultation(assignedServices);
		satyaObject.setAssignedConsultation(assignedServices);

		doctorList.add(karthickObject);
		doctorList.add(ramanObject);
		doctorList.add(satyaObject);

		consultationList.add(cardiologists);
		consultationList.add(neurologists);
		consultationList.add(orthopaedic);
		consultationList.add(ENT);
		consultationList.add(skinSpecialists);

		if (doctorFile.createNewFile()) {
			doctorSerialize(doctorList);
		}

		if (consultationFile.createNewFile()) {
			consultationSerialize(consultationList);
		}

		Slot karthickSlot = new Slot(karthickObject.id, karthickObject.startHour, karthickObject.endHour,
				karthickObject.slotsPerHour);
		Slot ramanSlot = new Slot(ramanObject.id, ramanObject.startHour, ramanObject.endHour, ramanObject.slotsPerHour);
		Slot satyaSlot = new Slot(satyaObject.id, satyaObject.startHour, satyaObject.endHour, satyaObject.slotsPerHour);

		doctorSlotList.put(karthickSlot.doctorID, karthickSlot.getAllSlotsInString());
		doctorSlotList.put(ramanSlot.doctorID, ramanSlot.getAllSlotsInString());
		doctorSlotList.put(satyaSlot.doctorID, satyaSlot.getAllSlotsInString());

		if (slotFile.createNewFile()) {
			doctorSlotSerialize(doctorSlotList);
		}

	}

	private LinkedList<Consultation> getAllConsultationsFromFile() {

		if (!consultationDeserialize().isEmpty()) {
			consultationList.clear();
			consultationList.addAll(consultationDeserialize());
		} else {
			consultationList.addAll(consultationDeserialize());
		}
		return consultationList;
	}

	private LinkedList<Doctor> getAllDoctorsFromFile() {

		if (!doctorDeserialize().isEmpty()) {
			doctorList.clear();
			doctorList.addAll(doctorDeserialize());
		} else {
			doctorList.addAll(doctorDeserialize());
		}

		return doctorList;
	}

	private HashMap<String, ArrayList<String>> getAllDoctorsSlotsFromFile() {
		if (!doctorSlotDeserialize().isEmpty()) {
			doctorSlotList.clear();
			doctorSlotList.putAll(doctorSlotDeserialize());
		} else {
			doctorSlotList.putAll(doctorSlotDeserialize());
		}
		return doctorSlotList;
	}

	private LinkedList<Patient> getAllPatientsFromFile() {
		if (!patientDeserialize().isEmpty()) {
			patientList.clear();
			patientList.addAll(patientDeserialize());
		} else {
			patientList.addAll(patientDeserialize());
		}
		return patientList;
	}

	private LinkedList<Appointment> getAllAppointmentsFromFile() {

		if (!appointmentDeserialize().isEmpty()) {
			appointmentList.clear();
			appointmentList.addAll(appointmentDeserialize());
		} else {
			appointmentList.addAll(appointmentDeserialize());
		}
		return appointmentList;

	}

	private void BookingPage() {
		String patientName;
		String patientEmail;
		String patientId;
		String appointmentId;
		int patientAge;

		int selectedConsultationIndex;
		int selectedDoctorIndex;
		int selectedSlot;

		getAllConsultationsFromFile();

		System.out.println("\nAvailable Consultations:\n");
		for (int cIndex = 0; cIndex < consultationList.size(); cIndex++) {
			System.out.println(cIndex + 1 + ". " + consultationList.get(cIndex).getName());
		}

		System.out.println("\nSelect the consultation:");

		selectedConsultationIndex = scanObject.nextInt();

		LinkedList<String> doctorListFromSelectedConsultation = new LinkedList<>();
		doctorListFromSelectedConsultation = consultationList.get(selectedConsultationIndex - 1).getAssingedDoctors();

		String selectedConsultation = "";
		for (int i = 0; i < consultationList.size(); i++) {
			if ((selectedConsultationIndex - 1) == i) {
				selectedConsultation = consultationList.get(i).id;
			}
		}

		System.out.println("\nAvailable Doctors:\n");

		getAllDoctorsFromFile();

		for (int adIndex = 0; adIndex < doctorListFromSelectedConsultation.size(); adIndex++) {

			for (int dIndex = 0; dIndex < doctorList.size(); dIndex++) {

				if (doctorListFromSelectedConsultation.get(adIndex).equals(doctorList.get(dIndex).id)) {
					System.out.println(adIndex + 1 + "." + doctorList.get(dIndex).name);
				}
			}
			// System.out.println(sIndex + 1 + "." + staffList.get(sIndex).name);
		}

		System.out.println("\n Select the doctor:");

		selectedDoctorIndex = scanObject.nextInt();

		String selectedDoctor = "";

		for (int i = 0; i < doctorListFromSelectedConsultation.size(); i++) {
			if ((selectedDoctorIndex - 1) == i) {
				selectedDoctor = doctorListFromSelectedConsultation.get(i);
			}
		}
		getAllDoctorsSlotsFromFile();
	

		slots = doctorSlotList.get(selectedDoctor);

		for (int i = 0; i < slots.size(); i++) {
			System.out.println(i + 1 + "->" + slots.get(i));
		}

		System.out.println("\n Select the slot:");

		selectedSlot = scanObject.nextInt();

		System.out.println("Provide your informations:\n");
		System.out.print("Name :");
		patientName = scanObject.next();
		System.out.print("Email: ");
		patientEmail = scanObject.next();
		System.out.print("Age: ");
		patientAge = scanObject.nextInt();
		patientId = "cus" + generateUUID().substring(0, 14);
		Patient patient = new Patient(patientId, patientName, patientEmail, patientAge);

		getAllPatientsFromFile();
		patientList.add(patient);
		patientSerialize(patientList);

		appointmentId = "appt" + generateUUID().substring(0, 8);
		try {
			Appointment appt = new Appointment(appointmentId, selectedDoctor, selectedConsultation, patientId,
					slots.remove(selectedSlot - 1), "100");
			getAllAppointmentsFromFile();
			appointmentList.add(appt);
			appointmentSerialize(appointmentList);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Slot is not available");
		}

		System.out.println("Slot booked sucessfully!!");
		System.out.println("Your contact Id :"+patientId +" "+"Appointment Id: "+appointmentId);
		doctorSlotSerialize(doctorSlotList);
	}

	public static void main(String[] args) throws IOException {

		int choice;

		MainClass mainClassObject = new MainClass();
		mainClassObject.initializeSetup();

		do {
			System.out.println("\nLogin as: \n1.Admin\n2.User");
			choice = scanObject.nextInt();

			switch (choice) {
			case 1:
				mainClassObject.admin();
				break;
			case 2:
				mainClassObject.BookingPage();
				break;
			}
		} while (choice >= 1 || choice <= 2);

	}

	private void admin() {

		System.out.println("Admin Profile");

		int choice;

		do {
			System.out.println("\n1.Create Staff\n2.Delete Staff\n3.Reports Menu\n4.Back to main menu");
			choice = scanObject.nextInt();
			int selectedConsultationIndex;
			String doctorName;
			int startHour;
			int endHour;
			int slotsPerHour;
			int decision;
			boolean flag = true;
			switch (choice) {
			case 1:
				System.out.println("Enter the staff name");
				doctorName = scanObject.next();
				System.out.println("Enter the startHour");
				startHour = scanObject.nextInt();
				System.out.println("Enter the endHour");
				endHour = scanObject.nextInt();
				System.out.println("Enter the number of slots per hour");
				slotsPerHour = scanObject.nextInt();
				getAllConsultationsFromFile();
				LinkedList<Consultation> localConsultObj = new LinkedList<>();
				LinkedList<String> localAssignConsult = new LinkedList<>();
				if (!localConsultObj.isEmpty()) {
					localConsultObj.clear();
					localConsultObj.addAll(consultationList);
				} else {
					localConsultObj.addAll(consultationList);
				}

				while (flag == true) {
					System.out.println("Choose the services to assign");
					for (int cIndex = 0; cIndex < localConsultObj.size(); cIndex++) {
						System.out.println(cIndex + 1 + ". " + localConsultObj.get(cIndex).getName());
					}

					System.out.println("\nSelect the consultation:");

					selectedConsultationIndex = scanObject.nextInt();

					String selectedConsultation = "";

					for (int i = 0; i < localConsultObj.size(); i++) {
						if ((selectedConsultationIndex - 1) == i) {
							selectedConsultation = localConsultObj.get(i).id;
							localAssignConsult.add(localConsultObj.get(i).getId());
							localConsultObj.remove(i);
						}
					}

					System.out.println("Do you want assign another services?:\n1.YES \n2.NO");
					decision = scanObject.nextInt();
					if (decision != 1) {
						flag = false;
					}

				}
				String doctorId = "S" + generateUUID().substring(0, 10);
				Doctor newDoctorObject = new Doctor(doctorId, doctorName, startHour, endHour, slotsPerHour,
						localAssignConsult);
				LinkedList<Doctor> localDoctorsList = new LinkedList<>();
				localDoctorsList.addAll(getAllDoctorsFromFile());
				
				localDoctorsList.add(newDoctorObject);

				LinkedList<String> doctorFromFile = new LinkedList<>();
				for (int acIndex = 0; acIndex < localAssignConsult.size(); acIndex++) {
					for (int allCIndex = 0; allCIndex < consultationList.size(); allCIndex++) {
						if (localAssignConsult.get(acIndex).equals(consultationList.get(allCIndex).id)) {
							doctorFromFile.addAll(consultationList.get(allCIndex).getAssingedDoctors());
							doctorFromFile.add(doctorId);
							consultationList.get(allCIndex).setAssingedDoctors(doctorFromFile);

						}
					}
				}

				Slot newDoctorSlot = new Slot(doctorId, startHour, endHour, slotsPerHour);
				HashMap<String, ArrayList<String>> localDoctorSlotList = new HashMap<>();
				localDoctorSlotList.putAll(getAllDoctorsSlotsFromFile());
				
				localDoctorSlotList.put(newDoctorSlot.doctorID, newDoctorSlot.getAllSlotsInString());


				doctorSlotSerialize(localDoctorSlotList);
				consultationSerialize(consultationList);
				doctorSerialize(localDoctorsList);
				System.out.println(doctorName+" staff is created Sucessfully");
				System.out.println(doctorName+" staff ID :"+doctorId);
				break;
			case 2:
				String staffDelete;
				System.out.println("\nChoose the staff to delete:");
				for (Doctor d : doctorDeserialize()) {
					System.out.println(d.id + " " + d.name);
				}
				staffDelete = scanObject.next();
				getAllDoctorsFromFile();
				for(int i = 0; i< doctorList.size();i++) {
					if(doctorList.get(i).id.equals(staffDelete)) {
						doctorList.remove(i);
						System.out.println("Staff Removed");
					}
				}
				doctorSerialize(doctorList);
				getAllConsultationsFromFile();
				for(int i = 0 ; i < consultationList.size();i++) {
					LinkedList<String> st = new LinkedList<>();
					st.addAll(consultationList.get(i).getAssingedDoctors());
					for(int j = 0;  j < st.size(); j ++) {
						if(st.get(j).equals(staffDelete)) {
							st.remove();
							consultationList.get(i).setAssingedDoctors(st);
						}
					}
				}
				consultationSerialize(consultationList);
				break; 
			case 3:
				adminReports();
			case 4:
				return;
			}
		} while (choice >= 1 || choice <= 4);

	}

	private void adminReports() {
		System.out.println("\nReports for : ");

		LinkedList<Patient> patients = new LinkedList<>();
		patients.addAll(getAllPatientsFromFile());
		
		LinkedList<Doctor> doctors = new LinkedList<>();
		doctors.addAll(getAllDoctorsFromFile());
		
		LinkedList<Consultation> consultations = new LinkedList<>();
		consultations.addAll(getAllConsultationsFromFile());
		
		LinkedList<Appointment> appointments = new LinkedList<>();
		appointments.addAll(getAllAppointmentsFromFile());

		int choice;
		do {
			System.out.println("\n1.Staff \n2.Patient \n3.Appointment \n4.Go back to main menu\n");
			choice = scanObject.nextInt();

			switch (choice) {
			case 1:
				System.out.println("\nDoctor details");
				ArrayList<String[]> doctorRows = new ArrayList<String[]>();
				for (int j = 0; j < doctors.size(); j++) {

					LinkedList<String> localacListdoctors = new LinkedList<>();
					localacListdoctors.addAll(doctors.get(j).getAssignedConsultation());
					String assignedServices = "";
					for (int c = 0; c < localacListdoctors.size(); c++) {
						for (int cl = 0; cl < consultations.size(); cl++) {
							if (localacListdoctors.get(c).equals(consultations.get(cl).id)) {
								if (c == 0) {
									assignedServices += consultations.get(cl).name;
								} else {
									assignedServices += ", " + consultations.get(cl).name;
								}
							}
						}
					}

					String[] temp = { doctors.get(j).getName(), Integer.toString(doctors.get(j).getStartHour()),
							Integer.toString(doctors.get(j).getEndHour()), assignedServices };
					doctorRows.add(temp);
				}
				String[] doctorColumn = { "Name", "StartHour", "EndHour", "Assigned Consultations" };
				TextTable doctorTable = new TextTable(doctorColumn, doctorRows.toArray(new String[0][0]));
				doctorTable.printTable();
				doctorRows.clear();
				break;
			case 2:
				System.out.println("\nPatient Details");
				ArrayList<String[]> patientrows = new ArrayList<String[]>();
				String[] patientColumn = { "Name", "Email", "Age" };

				for (int j = 0; j < patients.size(); j++) {

					String[] temp = { patients.get(j).getName(), patients.get(j).getEmail(),
							Integer.toString(patients.get(j).getAge()) };
					patientrows.add(temp);
				}
				TextTable patientTable = new TextTable(patientColumn, patientrows.toArray(new String[0][0]));
				patientTable.printTable();
				patientrows.clear();
				break;
			case 3:
				System.out.println("\nAppointment Details");
				String patientName = "";
				String doctorName = "";
				String consultationName = "";
				ArrayList<String[]> appointmentrows = new ArrayList<String[]>();

				String[] appointmentColumn = { "SlotTime", "Consultation", "Doctor", "Patient", "Appointment Cost" };

				for (int j = 0; j < appointments.size(); j++) {

					for (Patient p : patients) {
						
						if (p.getId().equals(appointments.get(j).getPatientID())) {

							patientName = p.getName() + ", " + p.getEmail();
						}
					}
					for (Doctor d : doctors) {
						if (d.getId().equals(appointments.get(j).getDoctorID())) {
							doctorName = d.getName();
						}
					}
					for (Consultation c : consultations) {
						if (c.getId().equals(appointments.get(j).getConsultationID())) {
							consultationName = c.getName();
						}
					}
					String[] temp = { appointments.get(j).getSlotTime(), consultationName, doctorName, patientName,
							appointments.get(j).getAppointmentCost() };
					appointmentrows.add(temp);
				}
				TextTable appointmentTable = new TextTable(appointmentColumn,appointmentrows.toArray(new String[0][0]));

				appointmentTable.printTable();
				appointmentrows.clear();

				break;
			case 4:
				return;
			}
		} while (choice >= 1 || choice <= 4);

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
