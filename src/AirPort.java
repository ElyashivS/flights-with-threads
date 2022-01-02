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

        // No available runway, or not his turn. Exit from while when find free runway and his turn.
        while ((temp == -1) || (queue.peek() != null && queue.peek() != flightNum)) {
            try {
                wait(); // notify in line 46
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            temp = getFreeRunway(); // Trying again.
        }
        // Found free runway and his turn.
        freeRunWays[temp] = false;
        return temp;
    }

    public synchronized void makeFreeRunway(int flightNum, int numOfFreeRunway) {
        if (queue.peek() != null) {
            queue.poll();
            freeRunWays[numOfFreeRunway] = true;
            notifyAll();
        }
    }

    public String getName() {
        return name;
    }

    private int getFreeRunway() {
        for (int i = 0; i < freeRunWays.length; i++) {
            System.out.println("i = " + i);
            if (freeRunWays[i]) {
                return i;
            }
        }
        return -1;
    }
}
