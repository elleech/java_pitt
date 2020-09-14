package lab02;

import javax.swing.JOptionPane;

public class P3DecomposingMoney {

	public static void main(String[] args) {
		String strIn = JOptionPane.showInputDialog("Enter your money ($1-$9999): ");
		int numIn = Integer.parseInt(strIn);
		if (numIn/1000 != 0) {
			int numG = numIn/1000;
			System.out.println(numG + " grands");
		}
		if (numIn/100%10 != 0) {
			int numBe = numIn/100%10;
			System.out.println(numBe + " Benjamins");
		}
		if (numIn/10%10 != 0) {
			int numS = numIn/10%10;
			System.out.println(numS + " sawbucks");
		}
		if (numIn%10 != 0) {
			int numBu = numIn%10;
			System.out.println(numBu + " bucks");
		}

	}

}
