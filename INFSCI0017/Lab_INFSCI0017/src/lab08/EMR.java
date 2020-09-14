package lab08;

import javax.swing.JOptionPane;

public class EMR {

	public static void main(String[] args) {
		Patient p = new Patient("Jane", "Doe", "111-11-1111", 'F', 110, 65);
		String s = JOptionPane.showInputDialog("Please enter your symptom: ");
		p.setSymptom(s);
		
		Doctor d = new Doctor("El", "Chen", "222-22-2222");
		d.prescribe(p);
				
		String pProfile = "Name: " + p.getLastName() + ", " + p.getFirstName() + "\n";
		pProfile = pProfile + "SSN: " + p.getSsn() + "\n";
		pProfile = pProfile + "BMI: " + p.calculateBMI() + "\n";
			
		if(p.getDiagnosis() != null){
			pProfile = pProfile + "Diagnosis: " + p.getDiagnosis().getName() + "\n";
		} else{
			pProfile = pProfile + "Diagnosis: none\n";
		}
				
		if(p.getMedication() != null){
			pProfile = pProfile + "Medication: " + p.getMedication().getName() + "\n";
		} else{
			pProfile = pProfile + "Medication: none\n";
		}
		
		JOptionPane.showMessageDialog(null, pProfile);
	}

}
