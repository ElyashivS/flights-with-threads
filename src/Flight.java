public class Flight extends Thread{
    private final int flightNum;
    private final AirPort departAirPort;
    private final AirPort landAirPort;

    public Flight(int flightNum, AirPort departAirPort, AirPort landAirPort) {
        this.flightNum = flightNum;
        this.departAirPort = departAirPort;
        this.landAirPort = landAirPort;
    }

    public int getFlightNum() {
        return flightNum;
    }

    public AirPort getDepartAirPort() {
        return departAirPort;
    }

    public AirPort getLandAirPort() {
        return landAirPort;
    }

    @Override
    public void run() {
        super.run();

    }
}
