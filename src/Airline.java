import java.util.ArrayList;

public class Airline {
    private final String airlineName;
    private final ArrayList<Aeroplane> fleet = new ArrayList<>();
    private final ArrayList<FlightDetails> flightDetails = new ArrayList<>();
    private static double firstClassPrice;
    private static double businessClassPrice;
    private static double economyClassPrice;

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


    public void assignPilot(Admin pilot, int flightNumber){
        flightDetails.get(flightNumber - 1).assignFlightNumber(flightNumber);
        flightDetails.get(flightNumber - 1).assignPilot(pilot);
    }

    public void assignHost(Admin host, int flightNumber){
        flightDetails.get(flightNumber - 1).assignFlightNumber(flightNumber);
        flightDetails.get(flightNumber - 1).assignHost(host);
    }
    public int generateFlightNumber(){
        FlightDetails flightDetail = new FlightDetails();
        flightDetails.add(flightDetail);
        return flightDetails.size();
    }
    public String generateFlightDetails(int flightNumber){
        flightDetails.get(flightNumber - 1).assignFlightNumber(flightNumber);
        return flightDetails.get(flightNumber - 1).toString();
    }
    public void boardPassenger(Passenger passenger, int flightNumber, int seatNumber){
        flightDetails.get(flightNumber - 1).assignFlightNumber(flightNumber);
        flightDetails.get(flightNumber - 1).recordPassengerInfo(passenger);
        fleet.get(flightNumber - 1).boardPassenger(passenger, seatNumber);
        flightDetails.get(flightNumber - 1).recordPassengerInformation(passenger);
    }
    public void recordTotalNumberOfPassengers(int flightNumber){
      int totalNumberOfPassengers = fleet.get(flightNumber - 1).getTotalNumberOfPassengers();
      flightDetails.get(flightNumber - 1).recordNumberOfPassengers(totalNumberOfPassengers);
    }

    public void setPriceOfFirstClass(double price) {
        firstClassPrice = price;
    }

    public static double getPriceOfFirstClass() {
        return firstClassPrice;
    }

    public static double getPriceOfBusinessClass() {
        return businessClassPrice;
    }

    public void setPriceOfBusinessClass(double price) {
        businessClassPrice = price;
    }

    public void setPriceOfEconomyClass(double price) {
        economyClassPrice = price;
    }

    public static double getPriceOfEconomyClass() {
        return economyClassPrice;
    }
}
