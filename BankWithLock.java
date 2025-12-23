// BankWithLock.java
public class BankWithLock {
    public static int balance = 0;
    private static final Object lock = new Object(); // explicit lock object

    public void deposit() {
        synchronized (lock) {
            balance += 100;
        }
    }

    public void withdraw() {
        synchronized (lock) {
            balance -= 100;
        }
    }

    public int getValue() {
        synchronized (lock) {
            return balance;
        }
    }

    public void run() {
        deposit();
        System.out.println("Value for Thread after deposit " + Thread.currentThread().getName() + " " + getValue());
        withdraw();
        System.out.println("Value for Thread after withdraw " + Thread.currentThread().getName() + " " + getValue());
    }
}