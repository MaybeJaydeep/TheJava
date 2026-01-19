package BankApplication;
import BankApplication.Bank.*;
import BankApplication.utils.AccountDetails;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Bank Application!");

        BankAccount account = new SavingsAccount(
            "1234509876",
            "Jaydeep Badal",
            50000,
            "Bacancy Bank",
            "BAC1234567",
            "Ahm Branch",
            7.5
        );
        account.deposit(20000);
        account.withdraw(15000);

         AccountDetails details = new AccountDetails(
                account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getBalance(),
                account.getAccountType()
        );

        System.out.println(details);
    }
}
