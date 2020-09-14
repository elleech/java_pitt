package lab02;

import javax.swing.JOptionPane;

public class P2AreaPerimeter {

	public static void main(String[] args) {
		String strR = JOptionPane.showInputDialog("Enter the radius: ");
		double numR = Double.parseDouble(strR);
		double numPeri = 2*Math.PI*numR;
		double numArea = Math.PI*Math.pow(numR, 2);
		double convPeri = ((int) Math.round(numPeri*100))/100.0;
		double convArea = ((int) Math.round(numArea*100))/100.0;
		JOptionPane.showMessageDialog(null, "The circle with radius " + strR + 
											" has an area of " + convArea + 
											" and a perimeter of " + convPeri);

	}

}
