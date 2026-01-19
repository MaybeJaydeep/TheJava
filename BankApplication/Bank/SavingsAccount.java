package BankApplication.Bank;

public class SavingsAccount extends AbstractBankAccount {
    private static final double MIN_BALANCE = 1000;
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance, String bankName, String ifscCode, String branch, double interestRate) {
        super(accountNumber, accountHolderName, balance, bankName, ifscCode, branch);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < MIN_BALANCE) {
            System.out.println("Minimum balance required: ₹1000");
            return;
        }
        balance -= amount;
    }

    @Override
    public String getAccountType(){
        return "Savings Account";
    } 
}