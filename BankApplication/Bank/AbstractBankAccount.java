package BankApplication.Bank;

public abstract class AbstractBankAccount implements BankAccount {

    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;
    protected String bankName;
    protected String ifscCode;
    protected String branch;

    public AbstractBankAccount(String accountNumber, String accountHolderName, double balance, String bankName, String ifscCode, String branch) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.branch = branch;
    }
    @Override
    public void deposit(double amount) {
        if (amount < 0){
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
    }

    @Override
    public String getAccountHolderName() {
        return accountHolderName;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }
}
