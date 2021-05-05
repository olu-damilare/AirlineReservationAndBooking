
import java.util.ArrayList;

public class FlightDetails {
    private int numberOfPassengers;
    private String hostDetails;
    private String pilotDetails;
    private Passenger passengerInfo;
    private int flightNumber;
    private ArrayList<Passenger> passengers = new ArrayList<>();

    public void assignHost(Admin host){
       hostDetails = host.toString();
    }
    public void assignPilot(Admin pilot){
        pilotDetails = pilot.toString();
    }
    public void assignFlightNumber(int flightNumber){
        this.flightNumber = flightNumber;
    }

    @Override
    public String toString() {
        StringBuilder flightDetails = new StringBuilder("FlightDetails:\n" +
                "Number Of Passengers = " + numberOfPassengers + "\n\n" +
                "Host Details = " + hostDetails + "\n\n" +
                "Pilot Details = " + pilotDetails + "\n\n" +
                "Flight Number = " + flightNumber + "\n\n" +
                "Passengers Information:\n\n");
        for (Passenger passenger : passengers) {
            flightDetails.append(passenger);
        }
        return flightDetails.toString();
    }

    public void recordNumberOfPassengers(int totalNumberOfPassengers){
        numberOfPassengers = totalNumberOfPassengers;
    }

    public void recordPassengerInformation(Passenger passenger) {
        passengerInfo = passenger;
    }

    public void recordPassengerInfo(Passenger passenger) {
        passengers.add(passenger);
    }
}
