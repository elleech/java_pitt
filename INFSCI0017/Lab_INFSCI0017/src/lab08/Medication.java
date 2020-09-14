package lab08;

public class Medication {

	private int medicationID;
	private String name;

	// Constructor: Initialize corresponding properties
	public Medication(int medicationID, String name) {
		this.medicationID = medicationID;
		this.name = name;
	}
	
	// Getters & Setters
	public int getMedicationID() {return medicationID;}
	public String getName() {return name;}

}
