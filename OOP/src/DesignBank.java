import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ============================================================================
 * Practice Template: Core Banking & Transaction System (LLD)
 * Difficulty: Medium–Hard | Target Companies: Goldman Sachs, Stripe, Google Pay
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Design a thread-safe core banking system that manages customer accounts,
 * processes deposits, withdrawals, and inter-account transfers, records immutable
 * audit logs, and prevents concurrency issues (race conditions & deadlocks).
 *
 * 📥 REQUIREMENTS:
 * 1. Account Types:
 *    - Savings Account: Enforces a minimum balance (e.g. $200).
 *    - Checking Account: Allows overdraft up to a pre-approved limit (e.g. -$300).
 * 2. Atomic Operations: `deposit()`, `withdraw()`, `transfer()` must be thread-safe.
 * 3. Deadlock Prevention: Transferring between Account A and Account B concurrently
 *    (Thread 1: A -> B, Thread 2: B -> A) must NEVER cause a deadlock.
 * 4. Audit Trail: Every transaction generates an immutable `Transaction` record.
 *
 * 💡 INTERVIEW HINTS:
 * - To prevent deadlocks on two-account transfers: always acquire locks in a globally
 *   consistent, deterministic order (e.g., compare `accountNumber` strings).
 * - Guard balance mutation with `ReentrantLock` and always unlock in a `finally` block.
 */
public class DesignBank {

    // =========================================================================
    // 1. ENUMS & TRANSACTION AUDIT LOG
    // =========================================================================

    public enum AccountType {
        SAVINGS, CHECKING
    }

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT
    }

    public static class Transaction {
        private static final AtomicInteger TXN_COUNTER = new AtomicInteger(5000);

        private final String transactionId;
        private final TransactionType type;
        private final double amount;
        private final double balanceAfter;
        private final LocalDateTime timestamp;

        public Transaction(TransactionType type, double amount, double balanceAfter) {
            this.transactionId = "TXN-" + TXN_COUNTER.incrementAndGet();
            this.type = type;
            this.amount = amount;
            this.balanceAfter = balanceAfter;
            this.timestamp = LocalDateTime.now();
        }

        public String getTransactionId() { return transactionId; }
        public TransactionType getType() { return type; }
        public double getAmount() { return amount; }
        public double getBalanceAfter() { return balanceAfter; }
        public LocalDateTime getTimestamp() { return timestamp; }

        @Override
        public String toString() {
            return String.format("[%s] %-12s Amount: $%.2f | Balance: $%.2f",
                    transactionId, type, amount, balanceAfter);
        }
    }

    // =========================================================================
    // 2. ACCOUNT HIERARCHY
    // =========================================================================

    public static abstract class Account {
        private final String accountNumber;
        private final String customerId;
        private final AccountType accountType;
        protected double balance;
        protected final List<Transaction> transactionHistory = Collections.synchronizedList(new ArrayList<>());
        private final ReentrantLock lock = new ReentrantLock();

        public Account(String accountNumber, String customerId, AccountType accountType, double initialDeposit) {
            this.accountNumber = accountNumber;
            this.customerId = customerId;
            this.accountType = accountType;
            this.balance = initialDeposit;
            if (initialDeposit > 0) {
                transactionHistory.add(new Transaction(TransactionType.DEPOSIT, initialDeposit, balance));
            }
        }

        public String getAccountNumber() { return accountNumber; }
        public String getCustomerId() { return customerId; }
        public AccountType getAccountType() { return accountType; }
        public double getBalance() { return balance; }
        public ReentrantLock getLock() { return lock; }

        public List<Transaction> getTransactionHistory() {
            return Collections.unmodifiableList(transactionHistory);
        }

        /**
         * Deposits money into this account atomically.
         */
        public void deposit(double amount) {
            // TODO: Implement atomic deposit:
            // 1. Validate amount > 0
            // 2. lock.lock()
            // 3. try: balance += amount; record Transaction; finally: lock.unlock()
        }

        /**
         * Withdraws money if permitted by account policy.
         */
        public boolean withdraw(double amount) {
            // TODO: Implement atomic withdraw:
            // 1. Validate amount > 0
            // 2. lock.lock()
            // 3. try: if canWithdraw(amount) -> balance -= amount; record Transaction; return true
            //    else return false
            //    finally: lock.unlock()
            return false;
        }

        protected abstract boolean canWithdraw(double amount);
    }

    public static class SavingsAccount extends Account {
        private final double minimumBalance;

        public SavingsAccount(String accountNumber, String customerId, double initialDeposit, double minimumBalance) {
            super(accountNumber, customerId, AccountType.SAVINGS, initialDeposit);
            this.minimumBalance = minimumBalance;
        }

        @Override
        protected boolean canWithdraw(double amount) {
            // TODO: Return true if (balance - amount) >= minimumBalance
            return false;
        }

        public double getMinimumBalance() { return minimumBalance; }
    }

    public static class CheckingAccount extends Account {
        private final double overdraftLimit;

        public CheckingAccount(String accountNumber, String customerId, double initialDeposit, double overdraftLimit) {
            super(accountNumber, customerId, AccountType.CHECKING, initialDeposit);
            this.overdraftLimit = overdraftLimit;
        }

        @Override
        protected boolean canWithdraw(double amount) {
            // TODO: Return true if (balance - amount) >= -overdraftLimit
            return false;
        }

        public double getOverdraftLimit() { return overdraftLimit; }
    }

    // =========================================================================
    // 3. BANK CONTROLLER & DEADLOCK-FREE TRANSFERS
    // =========================================================================

    private final String bankName;
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    public DesignBank(String bankName) {
        this.bankName = bankName;
    }

    public void openAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    /**
     * Atomically transfers money between two accounts without deadlock risk.
     */
    public boolean transfer(String fromAccNum, String toAccNum, double amount) {
        // TODO: Implement deadlock-free atomic transfer:
        // 1. Retrieve Account from and Account to
        // 2. Order locks deterministically (e.g. compare accountNumber strings)
        // 3. Acquire firstLock, then secondLock in try-finally
        // 4. Verify from.canWithdraw(amount)
        // 5. Transfer: from.balance -= amount; to.balance += amount
        // 6. Record TRANSFER_OUT and TRANSFER_IN transactions
        return false;
    }

    // =========================================================================
    // 4. VERIFICATION TEST HARNESS (Run to test your code!)
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== Testing: Banking & Concurrency System ===");

        DesignBank bank = new DesignBank("Apex Global Bank");

        SavingsAccount savings = new SavingsAccount("ACC-101", "CUST-1", 1000.0, 200.0);
        CheckingAccount checking = new CheckingAccount("ACC-102", "CUST-2", 500.0, 300.0);

        bank.openAccount(savings);
        bank.openAccount(checking);

        // Test 1: Savings deposit
        savings.deposit(300.0);
        if (savings.getBalance() == 1300.0) {
            System.out.println("  [PASS] Test 1: Savings deposit successful ($1300.0).");
        } else {
            System.out.println("  [TODO] Test 1: deposit() not implemented yet.");
        }

        // Test 2: Minimum balance enforcement
        boolean w1 = savings.withdraw(1200.0); // Leaves $100 < $200 min
        if (!w1 && savings.getBalance() == 1300.0) {
            System.out.println("  [PASS] Test 2: Minimum balance protection prevented excessive withdrawal.");
        } else {
            System.out.println("  [TODO] Test 2: canWithdraw() in SavingsAccount not implemented yet.");
        }

        // Test 3: Checking overdraft
        boolean w2 = checking.withdraw(700.0); // Allowed up to -$300 limit
        if (w2 && checking.getBalance() == -200.0) {
            System.out.println("  [PASS] Test 3: Checking overdraft executed (-$200.0).");
        } else {
            System.out.println("  [TODO] Test 3: canWithdraw() in CheckingAccount not implemented yet.");
        }

        // Test 4: Inter-account transfer
        boolean t1 = bank.transfer("ACC-101", "ACC-102", 400.0);
        if (t1) {
            System.out.println("  [PASS] Test 4: Deadlock-free inter-account transfer succeeded.");
        } else {
            System.out.println("  [TODO] Test 4: transfer() method not implemented yet.");
        }
    }
}
