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

        long randTime = 2000 + (long) (Math.random() * (3000)); // Random seconds between 2 and 5.
        long randTimeOfFlying = 4000 + (long) (Math.random() * (5000)); // Random seconds between 4 and 7.


        // Departing
        departAirPort.queue.add(flightNum); // Add flight to queue
        int freeRunwayToDepart = departAirPort.depart(flightNum); // Trying to get number of free runway to depart from.
        try { // Got number of freeway, and his turn. DEPARTING.
            sleep(200);
            System.out.println("flight number " + (flightNum + 1) + " is DEPARTING from runway number " + (freeRunwayToDepart + 1) + " in " + departAirPort.getName());
            sleep(randTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        departAirPort.makeFreeRunway(flightNum, freeRunwayToDepart);
        System.out.println("runway number " + (freeRunwayToDepart + 1) + " in " + departAirPort.getName() + " is clear");

        // Flying
        try {
            sleep(randTimeOfFlying);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Landing
        landAirPort.queue.add(flightNum); // Add flight to queue
        int freeRunwayToLand = landAirPort.land(flightNum);
        try { // Got number of freeway, and his turn. LANDING.
            sleep(200);
            System.out.println("flight number " + (flightNum + 1) + " is LANDING to runway number " + (freeRunwayToLand + 1) + " in " + departAirPort.getName());
            sleep(randTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        landAirPort.makeFreeRunway(flightNum, freeRunwayToLand);
        System.out.println("runway number " + (freeRunwayToDepart + 1) + " in " + departAirPort.getName() + " is clear");
    }
}
