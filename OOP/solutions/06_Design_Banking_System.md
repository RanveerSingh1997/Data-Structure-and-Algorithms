# 🏛️ Solution & Architecture: Core Banking System

**Problem**: Design a thread-safe core banking system with accounts, atomic transactions, and deadlock-free transfers.  
**Difficulty**: Medium–Hard | **Target Companies**: Goldman Sachs, Morgan Stanley, Stripe, Google Pay  
**Practice Template**: [`OOP/src/DesignBank.java`](../src/DesignBank.java)

---

## 1. Requirements & Clarifications

### Functional Requirements:
1. **Account Hierarchy**:
   - `SavingsAccount`: Minimum balance required (e.g. $200).
   - `CheckingAccount`: Overdraft allowance up to pre-approved limit (e.g. -$300).
2. **Core Financial Operations**: `deposit(amount)`, `withdraw(amount)`, `transfer(from, to, amount)`.
3. **Audit Trail**: Every money movement creates an immutable `Transaction` record with timestamp and post-transaction balance.

### Concurrency & Non-Functional Requirements:
- **Atomicity & Isolation**: Multiple threads depositing or withdrawing on the same account must not produce dirty reads or lost updates.
- **Deadlock Avoidance**: Concurrent cross-transfers ($A \to B$ and $B \to A$) must never deadlock.

---

## 2. Deadlock Prevention via Deterministic Lock Ordering

### The Bug (Deadlock):
If Thread 1 transfers $A \to B$ (locks $A$, waits for $B$) while Thread 2 transfers $B \to A$ (locks $B$, waits for $A$), a **circular wait** occurs. Both threads block forever!

### The Solution:
Sort the locks by a globally unique, immutable attribute (e.g. `accountNumber` string comparison) before acquiring:

```java
public boolean transfer(String fromAccNum, String toAccNum, double amount) {
    Account from = accounts.get(fromAccNum);
    Account to = accounts.get(toAccNum);

    // Deterministic Lock Ordering: Always lock in alphabetical order!
    Account firstLock = from.getAccountNumber().compareTo(to.getAccountNumber()) < 0 ? from : to;
    Account secondLock = from.getAccountNumber().compareTo(to.getAccountNumber()) < 0 ? to : from;

    firstLock.getLock().lock();
    try {
        secondLock.getLock().lock();
        try {
            if (!from.canWithdraw(amount)) return false;

            from.balance -= amount;
            to.balance += amount;

            from.transactionHistory.add(new Transaction(TransactionType.TRANSFER_OUT, amount, from.balance));
            to.transactionHistory.add(new Transaction(TransactionType.TRANSFER_IN, amount, to.balance));
            return true;
        } finally {
            secondLock.getLock().unlock();
        }
    } finally {
        firstLock.getLock().unlock();
    }
}
```

---

## 3. Template Method for Withdrawal Rules

Base class `Account` encapsulates the locking and audit logging, delegating policy to `canWithdraw()`:

```java
public abstract class Account {
    public boolean withdraw(double amount) {
        lock.lock();
        try {
            if (!canWithdraw(amount)) return false;
            balance -= amount;
            transactionHistory.add(new Transaction(TransactionType.WITHDRAWAL, amount, balance));
            return true;
        } finally {
            lock.unlock();
        }
    }

    protected abstract boolean canWithdraw(double amount);
}

// Subclasses specify their rule:
public class SavingsAccount extends Account {
    @Override
    protected boolean canWithdraw(double amount) {
        return (balance - amount) >= minimumBalance;
    }
}

public class CheckingAccount extends Account {
    @Override
    protected boolean canWithdraw(double amount) {
        return (balance - amount) >= -overdraftLimit;
    }
}
```

---

## 4. Key Interview Traps

1. **Floating Point Arithmetic**: In real production systems, use `BigDecimal` or store currency in cents (`long`) to avoid binary floating-point rounding errors ($0.1 + 0.2 \ne 0.3$).
2. **Missing `finally` Blocks**: Always release `lock.unlock()` inside `finally` to prevent permanent lock leaks if an exception is thrown.
