import java.util.ArrayList;
import java.util.List;

public class FlightReservation {
    private static List<Passenger> reservedPassengers = new ArrayList<>();

    public void reserveFlight(Passenger passenger, SeatClass seatType) {
        reservedPassengers.add(passenger);
        passenger.setPassengerSeatClass(seatType);

    }

    public int getReservationID(Passenger passenger){
        int passengerReservationID = 0;
        for (int i = 0; i < reservedPassengers.size(); i++) {
            if(reservedPassengers.get(i).equals(passenger)) {
                passengerReservationID = i + 1;
                break;
            }
        }
        return passengerReservationID;
    }

    public static boolean hasReserved(Passenger passenger) {
        boolean hasReserved = false;
        for (Passenger value: reservedPassengers) {
            if (value.getEmailAddress().equals(passenger.getEmailAddress())) {
                hasReserved = true;
                break;
            }
        }
        return hasReserved;
    }

    public SeatClass getPassengerReservedSeatType(Passenger passenger) {
        for (Passenger reservedPassenger : reservedPassengers) {
            if (reservedPassenger.getEmailAddress().equals(passenger.getEmailAddress()))
                return reservedPassenger.getPassengerSeatType();
        }
        return null;
    }

    public void cancelReservation(Passenger passenger) {
        for (int i = 0; i < reservedPassengers.size(); i++) {
            if(reservedPassengers.get(i).getEmailAddress().equals(passenger.getEmailAddress()))
                reservedPassengers.remove(i);
        }
    }

    public static List<Passenger> getReservedPassenger(){
        return reservedPassengers;
    }

    public static void emptyReservedPassengerList(){
                reservedPassengers.clear();
    }


}
