public class Seat {
    private int seatNumber;
    private SeatClass seatType;

    public void assignSeatNumber(int seatNumber){
        this.seatNumber = seatNumber;
    }

    public void assignSeatType(SeatClass seatType){
       this.seatType = seatType;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatClass getSeatType() {
        return seatType;
    }
}
