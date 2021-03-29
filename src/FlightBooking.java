import java.util.ArrayList;
import java.util.List;

public class FlightBooking {
    private  boolean[] hasBooked = new boolean[50];
    private static List<Passenger> bookedPassengers = new ArrayList<>();

    public void bookFlight(int reservationID) {
        if(reservationID > 0){
            Passenger reservedPassenger = FlightReservation.getReservedPassenger().get(reservationID - 1);
                if(FlightReservation.hasReserved(reservedPassenger)) {
                    if (!userExists(reservedPassenger)) {
                        bookedPassengers.add(reservedPassenger);
                        int seatNumber = allocateSeatBasedOnSeatType(reservedPassenger);
                        reservedPassenger.assignSeatNumber(seatNumber);
                    }
                }

        }else
            System.out.println("passenger has not reserved a seat");

    }

    private int allocateSeatBasedOnSeatType(Passenger reservedPassenger) {
        int seatNumber = 0;
        if(getPassengerBookedSeatType(reservedPassenger) == SeatClass.FIRSTCLASS)
            for (int i = 0; i < hasBooked.length - 40; i++) {
                if (!hasBooked[i]){
                    seatNumber = i + 1;
                    hasBooked[i] = true;
                    break;
                }
            }
        else if(getPassengerBookedSeatType(reservedPassenger) == SeatClass.BUSINESS)
            for (int i = 10; i < hasBooked.length - 25; i++) {
                if (!hasBooked[i]){
                    seatNumber = i + 1;
                    hasBooked[i] = true;
                    break;
                }
            }
        else
            for (int i = 25; i < hasBooked.length; i++) {
                if (!hasBooked[i]){
                    seatNumber = i + 1;
                    hasBooked[i] = true;
                    break;
                }
            }
            return seatNumber;
    }

    public boolean hasBooked(int seatNumber){
        return hasBooked[seatNumber - 1];
    }

    public int getTotalNumberOfFirstClassSeatsBooked() {
        int bookedSeats = 0;
        for (int i = 0; i < hasBooked.length - 40; i++) {
            if(hasBooked[i])
                bookedSeats++;
        }
        return bookedSeats;
    }

    public static SeatClass getPassengerBookedSeatType(Passenger passenger) {
        for (int i = 0; i < bookedPassengers.size(); i++) {
            if(bookedPassengers.get(i).equals(passenger))
                return  bookedPassengers.get(i).getPassengerSeatType();
        }
        return null;
    }


    public int getTotalNumberOfBusinessClassSeatsBooked() {
        int bookedSeats = 0;
        for (int i = 10; i < hasBooked.length - 25; i++) {
            if(hasBooked[i])
                bookedSeats++;
        }
        return bookedSeats;
    }

    public int getTotalNumberOfEconomyClassSeatsBooked() {
        int bookedSeats = 0;
        for (int i = 25; i < hasBooked.length; i++) {
            if(hasBooked[i])
                bookedSeats++;
        }
        return bookedSeats;
    }

    private static boolean userExists(Passenger passenger){
        boolean userExists = false;
        for (Passenger bookedPassenger : bookedPassengers) {
            if (bookedPassenger.equals(passenger)) {
                userExists = true;
                break;
            }
        }
        return userExists;
    }

    public void bookFlight(Passenger passenger, SeatClass seatClass) {
        bookedPassengers.add(passenger);
        passenger.setPassengerSeatClass(seatClass);
        int seatNumber = allocateSeatBasedOnSeatType(passenger);
        passenger.assignSeatNumber(seatNumber);

    }
}
