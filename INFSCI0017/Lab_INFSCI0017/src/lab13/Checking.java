package lab13;

public class Checking extends Account implements IAccount {
	private double newBalance = super.getBalance();

	public Checking(int accountNumber, double initBalance) {
		super(accountNumber, initBalance);
	}

	@Override
	public void withdraw(double amount) {
		newBalance -= amount;
	}

	@Override
	public void deposit(double amount) {
		newBalance += amount;
	}

	@Override
	public int getAccountNumber() {
		return super.getAccountNumber();
	}

	@Override
	public double getBalance() {
		return newBalance;
	}
}
