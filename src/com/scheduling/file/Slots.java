package com.scheduling.file;

import java.util.ArrayList;

class Slot implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -749319611753905325L;
	ArrayList<Boolean> slots = new ArrayList<>();
	ArrayList<String> slotsInString = new ArrayList<>();
	int startHour;
	int endHour;
	int slotSizeInmin;
	String doctorID;
	int slotSize;

	public Slot(String doctorID, int startHour, int endHour, int slotSizeInmin) {
		if ((endHour - startHour) > 24) {
			return;
		}
		this.doctorID = doctorID;
		this.startHour = startHour;
		this.endHour = endHour;
		this.slotSize = slotSizeInmin;

		for (int n = 0; n < (endHour - startHour) * (60 / slotSizeInmin); n++) {
			slots.add(n, true);
		}

	}

	public ArrayList<String> getAllSlotsInString() {
		return printAllSlots(slots);
	}

	private ArrayList<String> printAllSlots(ArrayList<Boolean> availableSlots) {
		ArrayList<String> slotsInString = new ArrayList<>();

		String temp;
		int iter = 0;
		for (int hour_i = this.startHour; hour_i < this.endHour; hour_i++) {
			for (int min = 0; min < 60; min += 60 / this.slotSize, iter++) {
				temp = hour_i + ":" + min;
				slotsInString.add(temp);
			}
		}

		return slotsInString;
	}

}
