// Bank.java
public class Bank {
    public static int balance = 0;

    public void deposit() {
        // Simulate processing delay (optional, but makes race more visible)
        // try { Thread.sleep(1); } catch (InterruptedException e) { }
        balance += 100;
    }

    public void withdraw() {
        balance -= 100;
    }

    public int getValue() {
        return balance;
    }

    public void run() {
        deposit();
        System.out.println("Value for Thread after deposit " + Thread.currentThread().getName() + " " + getValue());
        withdraw();
        System.out.println("Value for Thread after withdraw " + Thread.currentThread().getName() + " " + getValue());
    }
}