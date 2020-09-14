package lab07;

import java.util.Random;
import javax.swing.JOptionPane;

public class RollDice {

	public static void main(String[] args) {
		String strNumDice, strNumRoll, strCont;
		boolean cont = true;
		int numDice, numRoll;
		
		Random random = new Random();
		
		while (cont == true) {
			strNumDice = JOptionPane.showInputDialog("Please enter the number of dice: ");
			strNumRoll = JOptionPane.showInputDialog("Please enter the number of rolls: ");
			try {
				numDice = Integer.parseInt(strNumDice);
				numRoll = Integer.parseInt(strNumRoll);
				rollDice(numDice, numRoll, random);
				strCont = JOptionPane.showInputDialog("Continue? 'N' for temination.");
				if (strCont.equalsIgnoreCase("N")) {
					cont = false;
					JOptionPane.showMessageDialog(null, "Goodbye!");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Please enter an integer!");
			}
		}
	}
	
	static void rollDice(int numDice, int numRoll, Random numGen) {
		int[] count = new int[5*numDice+1]; // length: 6*numDice-(numDice-1)
		
		// rolling multiple times
		for (int i = 0; i < numRoll; i++){
			int diceVal = 0;
			// rolling multiple dice and adding their values
			for (int j = 0; j < numDice; j++) {
				diceVal += (numGen.nextInt(6)+1);
			}
			// counting the times of different values that show up
			for (int k = 1*numDice; k <= 6*numDice; k++) {
				if (diceVal == k) count[k-1*numDice]++;
			}
		}
		
		// print the results
		System.out.println("Value\tYourRoll");
		for (int i = 0; i < count.length; i++) {
			System.out.printf("%d:\t%f\n", (i+1*numDice), (double) count[i]/numRoll);
		}
	}
}
