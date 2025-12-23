// Main.java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== RACE CONDITION VERSION ===");
        raceConditionDemo();

        // Reset balance for fair comparison
        Bank.balance = 0;

        System.out.println("\n=== THREAD-SAFE VERSION (WITH LOCK) ===");
        threadSafeDemo();
    }

    public static void raceConditionDemo() throws InterruptedException {
        Bank bank = new Bank();
        Thread t1 = new Thread(bank::run, "Thread1");
        Thread t2 = new Thread(bank::run, "Thread2");
        Thread t3 = new Thread(bank::run, "Thread3");

        t1.start(); t2.start(); t3.start();
        t1.join(); t2.join(); t3.join();

        System.out.println("Final balance (should be 0 but may not be): " + Bank.balance);
    }

    public static void threadSafeDemo() throws InterruptedException {
        BankWithLock bank = new BankWithLock();
        Thread t1 = new Thread(bank::run, "Thread1");
        Thread t2 = new Thread(bank::run, "Thread2");
        Thread t3 = new Thread(bank::run, "Thread3");

        t1.start(); t2.start(); t3.start();
        t1.join(); t2.join(); t3.join();

        System.out.println("Final balance (should be 0): " + BankWithLock.balance);
    }
}