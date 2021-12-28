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

        int freeRunwayToDepart = departAirPort.depart(flightNum);
        try {
            sleep(2000); // Make it random between 2 to 5.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        departAirPort.makeFreeRunway(flightNum, freeRunwayToDepart);
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int freeRunwayToLand = landAirPort.land(flightNum);
        try {
            sleep(2000); // Make it random between 2 to 5.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        landAirPort.makeFreeRunway(flightNum, freeRunwayToLand);
    }
}
