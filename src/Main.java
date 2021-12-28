import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();

        AirPort airPort1 = new AirPort("TLV", 3);
        AirPort airPort2 = new AirPort("LHR", 3);
        boolean direction;

        Flight[] flights = new Flight[10];

        for (int i = 0; i < flights.length; i++) {
            direction = rand.nextBoolean();
            if (direction) {
                flights[i] = new Flight(i + 1, airPort1, airPort2);
            } else {
                flights[i] = new Flight(i + 1, airPort2, airPort1);
            }
        }

        for (Flight flight : flights) {
            System.out.println("flight number: " + flight.getFlightNum() + " from: " +
                    flight.getDepartAirPort().getName() + " to " + flight.getLandAirPort().getName());
        }
    }
}
