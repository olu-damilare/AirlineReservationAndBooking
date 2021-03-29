public class FlightDetails {
    private int numberOfPassengers;
    private String hostDetails;
    private String pilotDetails;
    private Passenger passengerInfo;
    private int flightNumber;

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
        return "FlightDetails{\n" +
                "Number Of Passengers = " + numberOfPassengers + '\n' +
                "Host Details = " + hostDetails + '\n' +
                "Pilot Details = " + pilotDetails + '\n' +
                "Passenger Info = " + passengerInfo + '\n' +
                "Flight  Number = " + flightNumber + '\n' +
                '}';
    }

    public void recordNumberOfPassengers(int totalNumberOfPassengers){
        numberOfPassengers = totalNumberOfPassengers;
    }

    public void recordPassengerInformation(Passenger passenger) {
        passengerInfo = passenger;
    }
}
