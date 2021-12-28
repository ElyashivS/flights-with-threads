import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class AirPort {

    private final String name;
    boolean[] freeRunWays;
    Queue<Integer> queue = new LinkedList<>();

    public AirPort(String name, int numOfRunway) {
        this.name = name;
        freeRunWays = new boolean[numOfRunway];
        Arrays.fill(freeRunWays, true); // Make all values true;
    }

    public synchronized int depart(int flightNum) {
        return waitForFreeRunway(flightNum);
    }

    public synchronized int land(int flightNum) {
        return waitForFreeRunway(flightNum);
    }

    private synchronized int waitForFreeRunway(int flightNum) {
        int temp = getFreeRunway();
        while (temp == -1) {
            try {
                wait();
                temp = getFreeRunway();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(flightNum);
        return temp;
    }

    public synchronized void makeFreeRunway(int flightNum, int numOfFreeRunway) {
        // What do I do with the flightNum?
        freeRunWays[numOfFreeRunway] = true;
    }

    public String getName() {
        return name;
    }

    private synchronized int getFreeRunway() {
        for (int i = 0; i < freeRunWays.length; i++) {
            if (freeRunWays[i]) {
                return i;
            }
        }
        return -1;
    }
}
