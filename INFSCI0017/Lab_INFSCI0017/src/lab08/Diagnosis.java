package lab08;

public class Diagnosis {

	private int diagnosisID;
	private String name;

	// Constructor: Initialize corresponding properties
	public Diagnosis(int diagnosisID, String name) {
		this.diagnosisID = diagnosisID;
		this.name = name;
	}

	// Getters & Setters
	public int getDiagnosisID() {return diagnosisID;}
	public String getName() {return name;}

}
