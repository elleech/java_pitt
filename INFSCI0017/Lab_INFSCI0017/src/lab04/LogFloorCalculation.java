package lab04;

import javax.swing.JOptionPane;

public class LogFloorCalculation {

	public static void main(String[] args) {
		String strX, strBase;
		int x, base;
		int count = 0;
		
		do {
			strX = JOptionPane.showInputDialog("Please enter the X: ");
			x = Integer.parseInt(strX);
		} while (x < 0);
		
		do {
			strBase = JOptionPane.showInputDialog("Please enter the base: ");
			base = Integer.parseInt(strBase);
		} while (base <= 1);
		
		do {
			x = x/base;
			count++;
		} while (x >= base);
		
		JOptionPane.showMessageDialog(null, "The floor of log" + strBase + "(" + strX + ") is: " + count);
	}

}
