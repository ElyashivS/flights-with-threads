public class AirPort {
    private String name;
    private int numOfRunways;

    public AirPort(String name, int numOfRunways) {
        this.name = name;
        this.numOfRunways = numOfRunways;
    }

    public int depart(int flightNum) {
        return 0;
    }

    public int land(int flightNum) {
        return 0;
    }

    public void freeRunways(int flightNum) {

    }

    public String getName() {
        return name;
    }

    public int getNumOfRunways() {
        return numOfRunways;
    }
}
