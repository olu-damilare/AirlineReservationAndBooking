public class Passenger {


    private final String name;
    private int passengerId;
    private final String phoneNumber;
    private String emailAddress;
    private SeatClass passengerSeatType;
    private static int passengerIDCounter;
    private int seatNumber;


    @Override
    public String toString() {
        return "Passenger{" + '\n' +
                "name = '" + name + '\n' +
                "Passenger Id = " + passengerId + '\n' +
                "Phone Number = '" + phoneNumber + '\n' +
                "Email Address = '" + emailAddress + '\n' +
                "Passenger Seat Class=" + passengerSeatType + '\n' +
                ", seatNumber = " + seatNumber + '\n' +
                '}';
    }

    public Passenger(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
       passengerId = ++passengerIDCounter;
    }

    public String getEmailAddress() {
        return emailAddress;
    }


    public void setPassengerSeatClass(SeatClass seatType){
        passengerSeatType = seatType;
    }

    public SeatClass getPassengerSeatType(){
        return passengerSeatType;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void assignSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}
