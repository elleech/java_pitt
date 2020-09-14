package lab13;

public abstract class Account {
	private int accountNumber;
	private double balance;

	public Account(int accountNumber, double initBalance){
		this.accountNumber = accountNumber;
		this.balance = initBalance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double newBalance) {
		this.balance = newBalance;
	}
}
