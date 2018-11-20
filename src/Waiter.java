import java.util.ArrayList;

public class Waiter {

    private final int PHILOSOPHER_COUNT;
    private final int FORK_COUNT;
    private final int KNIFE_COUNT;
    private Semaphore[] forks;
    private Semaphore[] knives;
    private ArrayList queue = new ArrayList();
    private boolean[] forksStatus = {false, false, false, false, false};
    private boolean[] knivesStatus = {false, false, false, false, false};



    public Waiter(int philosopherCount, int forkCount, int knifeCount) {
        this.PHILOSOPHER_COUNT = philosopherCount;
        this.FORK_COUNT = forkCount;
        this.KNIFE_COUNT = knifeCount;
        this.forks = new Semaphore[this.FORK_COUNT];
        this.knives = new Semaphore[this.KNIFE_COUNT];
        this.produceCutlery();
    }
    public void produceCutlery() {
        this.produceForks();
        this.produceSpoons();
    }
    public void produceForks() {
        for (int i = 0; i < this.FORK_COUNT; i++) {
            this.forks[i] = new Semaphore(1);
        }
    }
    public void produceSpoons() {
        for (int i = 0; i < this.KNIFE_COUNT; i++) {
            this.knives[i] = new Semaphore(1);
        }
    }

    public int pickUpFork(int pIndex) {
        // pick random fork
        int index = (int) (Math.random() * this.FORK_COUNT);
        if (!this.isQueueFull()) {
            this.forks[index].P();
            this.addToQueue(pIndex);
            DiningPhilosophers.transactionData.add(new TransactionData("PickingFork",pIndex, index));
            forksStatus[pIndex] = true;
        }
        return index;
    }

    public int pickUpKnife(int pIndex) {
        // pick random knife
        int index = (int) (Math.random() * this.KNIFE_COUNT);
        if (!this.isQueueFull()) {
            this.knives[index].P();
            this.addToQueue(pIndex);
            DiningPhilosophers.transactionData.add(new TransactionData("PickingKnife",pIndex, index));
            knivesStatus[pIndex] = true;
        }
        return index;
    }

    public void putDownFork(int index, int pIndex) {
        if(forksStatus[pIndex])
        {
            this.forks[index].V();
            this.removeFromQueue(pIndex);
            DiningPhilosophers.transactionData.add(new TransactionData("PuttingFork",pIndex, index));
            forksStatus[pIndex] = false;
        }

    }

    public void putDownKnife(int index, int pIndex) {
        if(knivesStatus[pIndex])
        {
            this.knives[index].V();
            this.removeFromQueue(pIndex);
            DiningPhilosophers.transactionData.add(new TransactionData("PuttingKnife",pIndex, index));
            knivesStatus[pIndex] = false;
        }

    }

    public boolean isQueueFull() {
        if(this.queue.size() >= this.PHILOSOPHER_COUNT - 1) {
//            System.out.println("QUEUE: QUEUE FULL");
            return true;
        } else {
            return false;
        }
    }

    public boolean isInQueue(int pIndex) {
        if (this.queue.contains(pIndex)) {
            return true;
        } else {
            return false;
        }
    }

    public void addToQueue(int pIndex) {
        if (!this.isInQueue(pIndex)) {
            this.queue.add(pIndex);
        }
    }

    public void removeFromQueue(int pIndex) {
        if (this.queue.contains(pIndex)) {
            this.queue.remove(this.queue.indexOf(pIndex));
        }
    }




}
