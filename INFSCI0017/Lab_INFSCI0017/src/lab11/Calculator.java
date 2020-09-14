package lab11;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Calculator {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ArrayList<String> input = new ArrayList<>();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 420, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textPane.setBounds(15, 16, 365, 35);
		textPane.setText("0");
		frame.getContentPane().add(textPane);
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("0");
				textPane.setText(print(input));
			}
		});
		btn0.setBounds(15, 271, 80, 35);
		frame.getContentPane().add(btn0);
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("1");
				textPane.setText(print(input));
			}
		});
		btn1.setBounds(15, 220, 80, 35);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("2");
				textPane.setText(print(input));
			}
		});
		btn2.setBounds(110, 220, 80, 35);
		frame.getContentPane().add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("3");
				textPane.setText(print(input));
			}
		});
		btn3.setBounds(205, 220, 80, 35);
		frame.getContentPane().add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("4");
				textPane.setText(print(input));
			}
		});
		btn4.setBounds(15, 169, 80, 35);
		frame.getContentPane().add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("5");
				textPane.setText(print(input));
			}
		});
		btn5.setBounds(110, 169, 80, 35);
		frame.getContentPane().add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("6");
				textPane.setText(print(input));
			}
		});
		btn6.setBounds(205, 169, 80, 35);
		frame.getContentPane().add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("7");
				textPane.setText(print(input));
			}
		});
		btn7.setBounds(15, 118, 80, 35);
		frame.getContentPane().add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("8");
				textPane.setText(print(input));
			}
		});
		btn8.setBounds(110, 118, 80, 35);
		frame.getContentPane().add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("9");
				textPane.setText(print(input));
			}
		});
		btn9.setBounds(205, 118, 80, 35);
		frame.getContentPane().add(btn9);
		
		JButton btnDot = new JButton(".");
		btnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add(".");
				textPane.setText(print(input));
			}
		});
		btnDot.setBounds(110, 271, 80, 35);
		frame.getContentPane().add(btnDot);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("+");
				textPane.setText(print(input));
			}
		});
		btnPlus.setBounds(300, 271, 80, 35);
		frame.getContentPane().add(btnPlus);
		
		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("-");
				textPane.setText(print(input));
			}
		});
		btnMinus.setBounds(300, 220, 80, 35);
		frame.getContentPane().add(btnMinus);
		
		JButton btnMultiply = new JButton("x");
		btnMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("*");
				textPane.setText(print(input));
			}
		});
		btnMultiply.setBounds(300, 169, 80, 35);
		frame.getContentPane().add(btnMultiply);
		
		JButton btnDivide = new JButton("\u00F7");
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("/");
				textPane.setText(print(input));
			}
		});
		btnDivide.setBounds(300, 118, 80, 35);
		frame.getContentPane().add(btnDivide);
		
		JButton buttonParenL = new JButton("(");
		buttonParenL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("(");
				textPane.setText(print(input));
			}
		});
		buttonParenL.setBounds(15, 67, 80, 35);
		frame.getContentPane().add(buttonParenL);
		
		JButton buttonParenR = new JButton(")");
		buttonParenR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add(")");
				textPane.setText(print(input));
			}
		});
		buttonParenR.setBounds(110, 67, 80, 35);
		frame.getContentPane().add(buttonParenR);
		
		JButton buttonPercent = new JButton("%");
		buttonPercent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.add("%");
				textPane.setText(print(input));
			}
		});
		buttonPercent.setBounds(205, 67, 80, 35);
		frame.getContentPane().add(buttonPercent);
		
		JButton btnEquals = new JButton("=");
		btnEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (input.size() != 0) {
					input.add("=");
					textPane.setText(doCal(doParen((input))));
					input.clear();
				} else {
					textPane.setText("0");
				}
			}
		});
		btnEquals.setBounds(205, 271, 80, 35);
		frame.getContentPane().add(btnEquals);
		
		JButton btnAC = new JButton("AC");
		btnAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText("0");
				input.clear();
			}
		});
		btnAC.setBounds(300, 67, 80, 35);
		frame.getContentPane().add(btnAC);

	}
	
	/**
	 * Method for showing String text
	 * @param input an ArrayList<String>
	 * @return result String
	 */
	private String print(ArrayList<String> input) {
		String result = "";
		for (String str : input) {
			result += str;
		}
		return result;
	}
	
	/*
	public static BigDecimal doCal(String calIn) {
		String operator = "";
		int index = 0;
		
		calIn = calIn.replaceAll("--", "+");
		
		operator = "(";
		index = calIn.lastIndexOf(operator);
		if (index >= 0) {
			int rightIndex = calIn.indexOf(")", index);

			String left = calIn.substring(0, index);
			String right = "";
			if (rightIndex + 1 < calIn.length()) {
				right = calIn.substring(rightIndex + 1);
			}

			BigDecimal middle = doCal(calIn.substring(index + 1, rightIndex));
			return doCal(left + middle + right);
		}

		operator = "+";
		index = calIn.lastIndexOf(operator);
		if (index > 0) {
			BigDecimal left = doCal(calIn.substring(0, index));
			BigDecimal right = doCal(calIn.substring(index + 1));
			return left.add(right);
		}

		operator = "-";
		index = calIn.lastIndexOf(operator);
		if (index == 0) {
			BigDecimal result = doCal(calIn.substring(index + 1));
			if (result.compareTo(new BigDecimal("0")) == -1) {
				return result.abs();
			} else {
				return result.negate();
			}
		} else if (index > 0) {
			BigDecimal left = doCal(calIn.substring(0, index));
			BigDecimal right = doCal(calIn.substring(index + 1));
			return left.subtract(right);
		}

		operator = "*";
		index = calIn.lastIndexOf(operator);
		if (index > 0) {
			BigDecimal left = doCal(calIn.substring(0, index));
			BigDecimal right = doCal(calIn.substring(index + 1));
			return left.multiply(right);
		}

		operator = "/";
		index = calIn.lastIndexOf(operator);
		if (index > 0) {
			BigDecimal left = doCal(calIn.substring(0, index));
			BigDecimal right = doCal(calIn.substring(index + 1));
			return left.divide(right);
		}
		
		operator = "%";
		index = calIn.lastIndexOf(operator);
		if (index > 0) {
			BigDecimal left = doCal(calIn.substring(0, index));
			return left.divide(new BigDecimal(100));
		}

		return new BigDecimal(calIn);
	}
	
	private String execute(ArrayList<String> input) {
		String calIn = print(input);
		BigDecimal result = doCal(calIn);
		return result.toPlainString();
	}*/
	
	private ArrayList<String> doSort(ArrayList<String> input) {
		ArrayList<String> output = new ArrayList<>();
		String temp = "";
		for (int i = 0; i < input.size(); i++) {
			if (isNum(input.get(i)) || input.get(i).equals(".")) {
				temp += input.get(i);
			} else if (input.get(i).equals("=")) {
				output.add(temp);
			} else {
				output.add(temp);
				output.add(input.get(i));
				temp = "";
			}
		}
		output.removeIf(n -> n.equals(""));
		return output;
	}
	
	private ArrayList<String> doParen(ArrayList<String> input) {
		ArrayList<String> newIn = doSort(input);
		ArrayList<String> output = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<>();
		boolean isParen = false;
		int i = 0;
		while (i < newIn.size()) {
			if (newIn.get(i).equals("(") && !isParen) {
				int j = i + 1;
				for (int k = j; k < newIn.size(); k++) {
					if (!newIn.get(k).equals(")")) {
						temp.add(newIn.get(k));
						isParen = true;
					} else {
						isParen = false;
						j = k;
						break;
					}
				}
				output.add(doCal(temp));
				temp.clear();
				i = j;
			} else {
				output.add(newIn.get(i));
			}
			i++;
		}
		
		return output;
	}

	private String doCal(ArrayList<String> input) {
		double temp = 0;
		double result = 0;
		String operator = "";
		boolean doCal = false;
		
		for (int i = 0; i < input.size(); i++) {
			if (isNum(input.get(i)) && !doCal) {
				result = Double.parseDouble(input.get(i));
			} else if (isNum(input.get(i)) && doCal) {
				temp = Double.parseDouble(input.get(i));
				switch (operator) {
					case "+":
						result = result + temp;
						break;
					case "-":
						result = result - temp;
						break;
					case "*":
						result = result * temp;
						break;
					case "/":
						result = result / temp;
						break;
					case "%":
						result = result / 100.0;
						break;
				}
			} else {
				operator = input.get(i);
				doCal = true;
			}
		}
		
		return Double.toString(result);
	}
	
	/**
	 * Method for checking whether the String is a double or not
	 * @param str a String
	 * @return true if it's a double, false if it's not.
	 */
	private boolean isNum(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
