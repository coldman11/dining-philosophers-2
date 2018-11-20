public class Philosopher extends Thread {
    private int index;
    private Waiter waiter;
    public Philosopher(int index, Waiter waiter) {
        this.index = index;
        this.waiter = waiter;
    }
    @Override
    public void run() {
        while(true) {
            // philosopher thinks
//            System.out.println("Philosopher " + this.index + " is thinking...");
            DiningPhilosophers.transactionData.add(new TransactionData("Thinking", this.index, 0));
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {

            }

            // philosopher picks up fork
            int forkIndex = this.waiter.pickUpFork(this.index);
//            System.out.println("Philosopher " + this.index +  " is picking up fork " + forkIndex);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {

            }

            // philosopher picks up knife
            int knifeIndex = this.waiter.pickUpKnife(this.index);
//            System.out.println("Philosopher " + this.index +  " is picking up knife " + knifeIndex);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {

            }

            // philosopher eats
//            System.out.println("Philosopher " + this.index + " is eating...");
            DiningPhilosophers.transactionData.add(new TransactionData("Eating", this.index, 0));
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {

            }

            // philosopher puts down fork
//            System.out.println("Philosopher " + this.index +  " is putting down fork " + forkIndex);
            this.waiter.putDownFork(forkIndex, this.index);

            // philosopher puts down knife
//            System.out.println("Philosopher " + this.index +  " is putting down knife " + knifeIndex);
            this.waiter.putDownKnife(knifeIndex, this.index);

        }
    }
}
