import java.util.concurrent.Semaphore;

// Shared resource
class BankAccount {
    String id;
    int balance;
    Semaphore mutex = new Semaphore(1);

    BankAccount(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }
}

// Transfer logic
class Transaction {

    static void moveMoney(BankAccount source, BankAccount destination, int amount) {
        try {
            System.out.println(Thread.currentThread().getName()
                    + " attempting to lock " + source.id);
            source.mutex.acquire();
            System.out.println(Thread.currentThread().getName()
                    + " locked " + source.id);

            // Intentional delay
            Thread.sleep(150);

            System.out.println(Thread.currentThread().getName()
                    + " attempting to lock " + destination.id);
            destination.mutex.acquire();
            System.out.println(Thread.currentThread().getName()
                    + " locked " + destination.id);

            // Critical section
            source.balance -= amount;
            destination.balance += amount;

            System.out.println(Thread.currentThread().getName()
                    + " transaction finished");

            destination.mutex.release();
            source.mutex.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class DeadlockTest {

    public static void main(String[] args) throws InterruptedException {

        BankAccount accA = new BankAccount("ACC-A", 1500);
        BankAccount accB = new BankAccount("ACC-B", 1500);

        Thread worker1 = new Thread(() ->
                Transaction.moveMoney(accA, accB, 300),
                "Worker-1"
        );

        Thread worker2 = new Thread(() ->
                Transaction.moveMoney(accB, accA, 400),
                "Worker-2"
        );

        worker1.start();
        Thread.sleep(40); // small stagger
        worker2.start();
    }
}
