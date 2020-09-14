package lab02;

import javax.swing.JOptionPane;

public class P1Pythagorean {

	public static void main(String[] args) {
		String strA = JOptionPane.showInputDialog("Enter the length of one side: ");
		String strB = JOptionPane.showInputDialog("Enter the length of another side: ");
		double numA = Double.parseDouble(strA);
		double numB = Double.parseDouble(strB);
		double numC = Math.sqrt(Math.pow(numA, 2)+Math.pow(numB, 2));
		int convert = (int) Math.round(numC*100);
		double result = convert/100.0;
		JOptionPane.showMessageDialog(null, "The hypotenuse is: " + result);

	}

}
