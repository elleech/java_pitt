package lab03;

import javax.swing.JOptionPane;

public class UnitConverter {

	public static void main(String[] args) {
		String in = JOptionPane.showInputDialog("Please enter a measurement: ");
		int pos = in.indexOf(" ");
		double num = Double.parseDouble(in.substring(0, pos));
		String unit = in.substring(pos).toLowerCase();
		switch (unit) {
			case " cm":
				num *= 0.3937007874;
				unit = " in";
				break;
			case " in":
				num *= 2.54;
				unit = " cm";
				break;
			case " yd":
				num *= 0.9144;
				unit = " m";
				break;
			case " m":
				num *= 1.0936132983;
				unit = " yd";
				break;
			case " oz":
				num *= 28.3495;
				unit = " gm";
				break;
			case " gm":
				num *= 0.0352739907;
				unit = " oz";
				break;
			case " lb":
				num *= 0.453592;
				unit = " kg";
				break;
			case " kg":
				num *= 2.2046244202;
				unit = " lb";
				break;
			default:
				JOptionPane.showMessageDialog(null, "Please enter a valid input.");
		}
		JOptionPane.showMessageDialog(null, in + " = " + num + unit);

	}

}
