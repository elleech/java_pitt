package lab13;

public interface IAccount {
	public void withdraw(double amount);
	public void deposit(double amount);
	public int getAccountNumber();
	public double getBalance();
}
