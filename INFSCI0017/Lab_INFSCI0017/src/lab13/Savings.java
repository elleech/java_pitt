package lab13;

public class Savings extends Account implements IAccount {
	private double newBalance = super.getBalance();

	public Savings(int accountNumber, double initBalance) {
		super(accountNumber, initBalance);
	}

	@Override
	public void withdraw(double amount) {
		newBalance -= 5;
		if (newBalance - amount >= 0) {
			newBalance -= amount;
		} else {
			System.out.println("Overdraft is not allowed.");
		}
	}

	@Override
	public void deposit(double amount) {
		newBalance += amount;
		if (newBalance >= 1000) {
			newBalance += 5;
		}
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
