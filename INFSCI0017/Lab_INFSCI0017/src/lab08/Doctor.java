package lab08;

import javax.swing.JOptionPane;

public class Doctor {

	private String employeeID;
	private String firstName, lastName;
	private String ssn;

	// Initialize corresponding properties
	public Doctor(String firstName, String lastName, String ssn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		
		// Initialize patientID property based on a social security number (ssn property) 
		// with all dashes and spaces stripped 
		// followed by first letter of first name and first letter of last name
		this.employeeID = firstName.substring(0, 1) + lastName.substring(0, 1) + 
						  ssn.replaceAll("-", "").replaceAll(" ", "");
	}
	
	// Method prescribe(...) matches the symptom of the patient parameter 
	// with diagnosis and medication
	public void prescribe(Patient patient) {
		String[][] match = {{"Headache", "Dehydration", "Tylenol"}, 
							{"Cough", "Common cold", "Cough drops"}, 
							{"Fever", "Influenza", "Tamiflu"}};
		boolean found = false;
		for (int i = 0; i < match.length; i++) {
			if (patient.getSymptom().equalsIgnoreCase(match[i][0])) {
				Diagnosis diagnosis = new Diagnosis(i+1, match[i][1]);
				Medication medication = new Medication(i+1, match[i][2]);
				patient.setDiagnosis(diagnosis);
				patient.setMedication(medication);
				found = true;
				break;
			}
		}
		
		if (!found) {
			patient.setDiagnosis(null);
			patient.setMedication(null);
			JOptionPane.showMessageDialog(null, "You cannot be diagnosed");
		}
	}

	// Getters & Setters
	public String getEmployeeID() {return employeeID;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getSsn() {return ssn;}

}
