package lab05;

import java.util.Random;
import javax.swing.JOptionPane;

public class DiceRoller {

	public static void main(String[] args) {
		String strNumRoll, strCont;
		boolean cont = true;
		int numRoll;
		
		Random random = new Random();
		
		while (cont == true) {
			strNumRoll = JOptionPane.showInputDialog("Please enter the number of rolls: ");
			numRoll = Integer.parseInt(strNumRoll);
			RollDice(numRoll, random);
			
			strCont = JOptionPane.showInputDialog("Continue? Y/N ");
			if (strCont.equalsIgnoreCase("N")) {cont = false;}
		}
	}
	
	static void RollDice(int numRoll, Random numGen) {
		int dice1, dice2;
		int diceR1, diceR2;
		int count2=0, count3=0, count4=0, count5=0, count6=0, count7=0, count8=0, count9=0, count10=0, count11=0, count12=0;
		int countR2=0, countR3=0, countR4=0, countR5=0, countR6=0, countR7=0, countR8=0, countR9=0, countR10=0, countR11=0, countR12=0;
		
		for (int i = 0; i < numRoll; i++){
			dice1 = numGen.nextInt(6)+1;
			dice2 = numGen.nextInt(6)+1;
			switch (dice1+dice2) {
				case 2: count2++; break;
				case 3: count3++; break;
				case 4: count4++; break;
				case 5: count5++; break;
				case 6: count6++; break;
				case 7: count7++; break;
				case 8: count8++; break;
				case 9: count9++; break;
				case 10: count10++; break;
				case 11: count11++; break;
				case 12: count12++; break;
			}
		}
		
		int numR = numGen.nextInt(1000000);
		for (int i = 0; i < numR; i++){
			diceR1 = numGen.nextInt(6)+1;
			diceR2 = numGen.nextInt(6)+1;
			switch (diceR1+diceR2) {
				case 2: countR2++; break;
				case 3: countR3++; break;
				case 4: countR4++; break;
				case 5: countR5++; break;
				case 6: countR6++; break;
				case 7: countR7++; break;
				case 8: countR8++; break;
				case 9: countR9++; break;
				case 10: countR10++; break;
				case 11: countR11++; break;
				case 12: countR12++; break;
			}

		}
		
		System.out.printf("2:  %f\t%f\n", (double) count2/numRoll, (double) countR2/numR);
		System.out.printf("3:  %f\t%f\n", (double) count3/numRoll, (double) countR3/numR);
		System.out.printf("4:  %f\t%f\n", (double) count4/numRoll, (double) countR4/numR);
		System.out.printf("5:  %f\t%f\n", (double) count5/numRoll, (double) countR5/numR);
		System.out.printf("6:  %f\t%f\n", (double) count6/numRoll, (double) countR6/numR);
		System.out.printf("7:  %f\t%f\n", (double) count7/numRoll, (double) countR7/numR);
		System.out.printf("8:  %f\t%f\n", (double) count8/numRoll, (double) countR8/numR);
		System.out.printf("9:  %f\t%f\n", (double) count9/numRoll, (double) countR9/numR);
		System.out.printf("10: %f\t%f\n", (double) count10/numRoll, (double) countR10/numR);
		System.out.printf("11: %f\t%f\n", (double) count11/numRoll, (double) countR11/numR);
		System.out.printf("12: %f\t%f\n", (double) count12/numRoll, (double) countR12/numR);
	}
}
