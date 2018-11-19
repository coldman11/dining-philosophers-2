public class Main {

    public static void main(String[] args) {
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers(
                5,
                5,
                1
        );
        diningPhilosophers.execute();
    }
}
