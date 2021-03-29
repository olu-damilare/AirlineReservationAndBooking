import java.util.Arrays;

public class Aeroplane {
    private String name;
    private Seat[] seats = new Seat[50];
    private Passenger[] passengers = new Passenger[50];

    public Aeroplane(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getNumberOfSeatsInPlane() {
        assignSeatNumbers();
        int numberOfSeats = 0;
        for (int i = 0; i < seats.length; i++) {
            if(seats[i] != null)
                numberOfSeats++;
        }
        return numberOfSeats;
    }

    private void assignSeatNumbers(){
        installSeats();
        for (int i = 0; i < seats.length; i++) {
            seats[i].assignSeatNumber(i+1);
        }
    }
    private void installSeats(){
      Seat seat = new Seat();
        Arrays.fill(seats, seat);
    }

    public void boardPassenger(Passenger passenger, int seatNumber) {
        passengers[seatNumber - 1] = passenger;
    }

    public int getTotalNumberOfPassengers(){
        int totalNumberOfPassengers = 0;
        for (Passenger passenger : passengers) {
            if (passenger != null)
                totalNumberOfPassengers++;
        }
        return totalNumberOfPassengers;
    }
}
