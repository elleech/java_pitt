package lab08;

public class Patient {

	private String patientID;
	private String firstName, lastName;
	private String ssn;
	private char gender;
	
	private Diagnosis diagnosis;
	private Medication medication;
	private String symptom;

	private double weight, height;

	// Initialize corresponding properties
	public Patient(String firstName, String lastName, String ssn, char gender, 
				   double weight, double height) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.gender = gender;
		this.weight = weight;
		this.height = height;
		
		// Initialize patientID property based on a social security number (ssn property) 
		// with all dashes and spaces stripped 
		// followed by first letter of first name and first letter of last name
		this.patientID = firstName.substring(0, 1) + lastName.substring(0, 1) + 
						 ssn.replaceAll("-", "").replaceAll(" ", "");
	}
	
	// Method calculateBMI() returns BMI value 
	public double calculateBMI() {
		return 703.0*weight/Math.pow(height, 2);
	}

	// Getters & Setters
	public String getPatientID() {return patientID;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getSsn() {return ssn;}
	public char getGender() {return gender;}
	public Diagnosis getDiagnosis() {return diagnosis;}
	public Medication getMedication() {return medication;}
	public String getSymptom() {return symptom;}
	public void setDiagnosis(Diagnosis diagnosis) {this.diagnosis = diagnosis;}
	public void setMedication(Medication medication) {this.medication = medication;}
	public void setSymptom(String symptom) {this.symptom = symptom;}

}
