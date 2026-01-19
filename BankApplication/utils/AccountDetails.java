package BankApplication.utils;

public class AccountDetails {

    private final String accountNumber;
    private final String accountHolderName;
    private final double balance;
    private final String accountType;

    public AccountDetails(
            String accountNumber,
            String accountHolderName,
            double balance,
            String accountType
    ) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return """
                -------------------------
                Account Number : %s
                Holder Name    : %s
                Account Type   : %s
                Balance        : %.2f
                -------------------------
                """.formatted(accountNumber, accountHolderName, accountType, balance);
    }

}
