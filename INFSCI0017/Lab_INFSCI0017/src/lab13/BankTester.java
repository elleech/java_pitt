package lab13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BankTester {

	public static void main(String[] args) {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("data/accounts.csv");
			br = new BufferedReader(fr);

			String line = null;
			while((line = br.readLine()) != null){
				String[] temp = line.split(",");
				IAccount a; // declare account to the interface, not to a specific type
				int accountNumber = Integer.parseInt(temp[0]);
				double initBalance = Double.parseDouble(temp[1]);
				String accountType = temp[2];
				if(accountType.equals("checking")){
					a = new Checking(accountNumber, initBalance);
				}
				else{
					a = new Savings(accountNumber, initBalance);
				}

				a.deposit(1000);
				a.withdraw(5000);
				System.out.println(accountType+" "+a.getAccountNumber() + " has a balance of $" + a.getBalance());
			}
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			System.out.println("Unable to find file");
		} catch (IOException e) {
			System.out.println("Unable to read file");
		} catch (NumberFormatException e){
			System.out.println("Invalid balance value");
		} finally {
			try {
				if (br != null) br.close();
				if (fr != null) fr.close();
			} catch (IOException e) {
				System.out.println("Error closing file: " + e.getMessage());
			}
		}
	}
}
