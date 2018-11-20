import java.util.ArrayList;

public class DiningPhilosophers {
    private final int PHILOSOPHER_COUNT;
    private final int FORK_COUNT;
    private final int KNIFE_COUNT;
    public static ArrayList<TransactionData> transactionData = new ArrayList<>();

    Waiter waiter;

    public DiningPhilosophers(int philosopherCount, int forkCount, int knifeCount) {
        // assign constants;
        this.PHILOSOPHER_COUNT = philosopherCount;
        this.FORK_COUNT = forkCount;
        this.KNIFE_COUNT = knifeCount;
        // create objects
        this.waiter = new Waiter(this.PHILOSOPHER_COUNT, this.FORK_COUNT, this.KNIFE_COUNT);
    }

    public void execute() {
        for (int i = 0; i < this.PHILOSOPHER_COUNT; i++) {
            Thread philosopher = new Philosopher(i, this.waiter);
            philosopher.start();
        }
    }

    public int getPHILOSOPHER_COUNT() {
        return PHILOSOPHER_COUNT;
    }

    public int getFORK_COUNT() {
        return FORK_COUNT;
    }

    public int getKNIFE_COUNT() {
        return KNIFE_COUNT;
    }
}
