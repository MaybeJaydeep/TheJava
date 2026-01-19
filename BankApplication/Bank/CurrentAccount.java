package BankApplication.Bank;

class CurrentAccount extends SavingsAccount {
    public CurrentAccount(String accountNumber, String accountHolderName, double balance, String bankName, String ifscCode, String branch, double interestRate) {
        super(accountNumber, accountHolderName, balance, bankName, ifscCode, branch, interestRate);
    }
    
    @Override
    public String getAccountType(){
        return "Current Account";
    }

    @Override
    public void withdraw(double amount){
        if(amount <0 ){
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if(amount > balance){
            System.out.println("Insufficient balance.");
            return;
        }
        balance -= amount;
    }
}
