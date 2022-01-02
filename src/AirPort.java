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
        // No available runway, or not his turn. Exit from while when find free runway and his turn.
        while ((getFreeRunway() == -1) || (queue.peek() != null && queue.peek() != flightNum)) {
//            System.out.println((flightNum + 1) + " get route: " + temp);
            try {
                wait(); // notify in line 46
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        int temp = getFreeRunway(); // Trying again.
        // Found free runway and his turn.
        freeRunWays[temp] = false;
        queue.poll();
        return temp;
    }

    public synchronized void makeFreeRunway(int flightNum, int numOfFreeRunway) {
        freeRunWays[numOfFreeRunway] = true;
        notifyAll();
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
