import java.util.ArrayList;

public class Airline {
    private final String airlineName;
    private final ArrayList<Aeroplane> fleet = new ArrayList<>();
    private ArrayList<FlightDetails> flightDetails = new ArrayList<>();

    public Airline(String airlineName, Aeroplane aeroplane) {
        this.airlineName = airlineName;
        fleet.add(aeroplane);
    }

    @Override
    public String toString() {
        return "NewAirline{" + "\n" +
                "Airline Name='" + airlineName + '\'' + "\n" +
                "Number of aeroplanes='" + getTotalNumberOfAeroplanes() + '\'' +
                '}';
    }

    public void acquireAeroplane(Aeroplane aeroplane) {
        fleet.add(aeroplane);
    }

    public int getTotalNumberOfAeroplanes() {
        int numberOfAeroplanes = 0;
        for (Aeroplane aeroplane : fleet) {
            if (aeroplane != null)
                numberOfAeroplanes++;
        }
        return numberOfAeroplanes;
    }


    public void assignPilot(String name, String phoneNumber, String emailAddress, String staffID, int flightNumber){
        Admin pilot = new Admin(name, phoneNumber, emailAddress, staffID);
        flightDetails.get(flightNumber - 1).assignFlightNumber(flightNumber);
        flightDetails.get(flightNumber - 1).assignPilot(pilot);
    }

    public void assignHost(Admin host, int flightNumber){
        flightDetails.get(flightNumber - 1).assignFlightNumber(flightNumber);
        flightDetails.get(flightNumber - 1).assignHost(host);
    }

    public String generateFlightDetails(int flightNumber){
        flightDetails.get(flightNumber - 1).assignFlightNumber(flightNumber);
        return flightDetails.get(flightNumber - 1).toString();
    }
    public void boardPassenger(Passenger passenger, int flightNumber, int seatNumber){
        flightDetails.get(flightNumber - 1).assignFlightNumber(flightNumber);
        fleet.get(flightNumber - 1).boardPassenger(passenger, seatNumber);
        flightDetails.get(flightNumber - 1).recordPassengerInformation(passenger);
    }
    public void recordTotalNumberOfPassengers(int flightNumber){
      int totalNumberOfPassengers = fleet.get(flightNumber - 1).getTotalNumberOfPassengers();
      flightDetails.get(flightNumber - 1).recordNumberOfPassengers(totalNumberOfPassengers);
    }

}
