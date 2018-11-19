public class Semaphore {
    private int value;
    public Semaphore() {
        this.value = 0;
    }
    public Semaphore(int v) {
        this.value = v;
    }
    public synchronized void P() {
        while (value <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        value--;
    }
    public synchronized void V() {
        ++value;
        notify();
    }
}
