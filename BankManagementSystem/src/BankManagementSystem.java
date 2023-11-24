import java.util.ArrayList;

class Customer {
    int customer_id;
    String name;
    String address;
    ArrayList<Account> accounts;

    public Customer(int customer_id, String name, String address) {
        this.customer_id = customer_id;
        this.name = name;
        this.address = address;
        this.accounts = new ArrayList<>();
    }
}

class Account {
    int account_number;
    double balance;
    ArrayList<String> transactions;

    public Account(int account_number, double balance) {
        this.account_number = account_number;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposit: +$" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrawal: -$" + amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void displayTransactions() {
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

class SavingsAccount extends Account {
    static double interestRate = 0.02;

    public SavingsAccount(int account_number, double balance) {
        super(account_number, balance);
    }

    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
        transactions.add("Interest added: +$" + interest);
    }
}

class CurrentAccount extends Account {
    static double overdraftLimit = 1000;

    public CurrentAccount(int account_number, double balance) {
        super(account_number, balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance + overdraftLimit) {
            balance -= amount;
            transactions.add("Withdrawal: -$" + amount);
        } else {
            System.out.println("Exceeds overdraft limit");
        }
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Customer customer1 = new Customer(1, "John Doe", "123 Main St");
        SavingsAccount savingsAccount = new SavingsAccount(101, 0);
        CurrentAccount currentAccount = new CurrentAccount(102, 0);

        customer1.accounts.add(savingsAccount);
        customer1.accounts.add(currentAccount);
        bank.addCustomer(customer1);

        Customer customer2 = new Customer(2, "Jane Smith", "456 Oak St");
        CurrentAccount currentAccount2 = new CurrentAccount(103, 0);

        customer2.accounts.add(currentAccount2);
        bank.addCustomer(customer2);

        savingsAccount.deposit(1000);
        savingsAccount.addInterest();
        savingsAccount.displayTransactions();

        currentAccount.deposit(500);
        currentAccount.withdraw(200);
        currentAccount.displayTransactions();

        currentAccount2.deposit(800);
        currentAccount2.withdraw(1200);

        bank.displayCustomerInfo(1);
        bank.displayCustomerInfo(2);
    }
}

class Bank {
    ArrayList<Customer> customers;

    public Bank() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void displayCustomerInfo(int customer_id) {
        for (Customer customer : customers) {
            if (customer.customer_id == customer_id) {
                System.out.println("Customer ID: " + customer.customer_id);
                System.out.println("Name: " + customer.name);
                System.out.println("Address: " + customer.address);
                System.out.println("Accounts:");

                for (Account account : customer.accounts) {
                    System.out.println("  Account Number: " + account.account_number);
                    System.out.println("  Balance: $" + account.balance);
                    System.out.println("  Transactions:");
                    account.displayTransactions();
                    System.out.println();
                }
            }
        }
    }
}
