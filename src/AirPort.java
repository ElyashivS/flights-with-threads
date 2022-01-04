import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class AirPort {

    private final String name;
    private boolean[] freeRunWays;
    private Queue<Integer> queue = new LinkedList<>();

    // Constructor
    public AirPort(String name, int numOfRunway) {
        this.name = name;
        freeRunWays = new boolean[numOfRunway];
        Arrays.fill(freeRunWays, true); // Make all values true;
    }

    // Getters
    public Queue<Integer> getQueue() {
        return queue;
    }

    public String getName() {
        return name;
    }

    public synchronized int depart(int flightNum) {
        return waitForFreeRunway(flightNum);
    }

    public synchronized int land(int flightNum) {
        return waitForFreeRunway(flightNum);
    }

    /**
     * This function make the thread wait if there is no free runway, or if it's not his turn according
     * to the queue.
     * @param flightNum the number of flight.
     * @return free runway to depart or land.
     */
    private synchronized int waitForFreeRunway(int flightNum) {
        // No available runway, or not his turn. Exit from loop when find free runway and his turn.
        while ((getFreeRunway() == -1) || (queue.peek() != null && queue.peek() != flightNum)) {
            try {
                wait(); // Notify in the function makeFreeRunway.
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

    /**
     * This function free the runway that has been taken.
     * @param numOfFreeRunway number of the runway to make free.
     */
    // This function should be named "freeRunway" according to the assignment
    public synchronized void makeFreeRunway(int numOfFreeRunway) {
        freeRunWays[numOfFreeRunway] = true;
        notifyAll();
    }

    /**
     * This function get free runway from the airport.
     * @return the number of next free runway.
     */
    private synchronized int getFreeRunway() {
        for (int i = 0; i < freeRunWays.length; i++) {
            if (freeRunWays[i]) {
                return i;
            }
        }
        return -1;
    }
}
