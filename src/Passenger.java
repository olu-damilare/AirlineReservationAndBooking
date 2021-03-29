public class Passenger {

    private final String name;
    private final String phoneNumber;
    private String emailAddress;
    private SeatClass passengerSeatType;
    private int seatNumber;
    private boolean hasPaid;
    private PaymentType paymentType;


    @Override
    public String toString() {
        return
                "Full Name = '" + name + '\n' +
                "Phone Number = " + phoneNumber + '\n' +
                "Email Address = " + emailAddress + '\n' +
                "Passenger Seat Class = " + passengerSeatType + '\n' +
                "Seat Number = " + seatNumber + '\n' +
                "Payment Type = " + paymentType;
    }

    public Passenger(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
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


    public void assignSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void payment(boolean paymentStatus) {
        if(FlightBooking.hasBooked(seatNumber))
            hasPaid = paymentStatus;
    }
    public boolean hasPaid(){
        return hasPaid;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    public PaymentType getPaymentType(){
        return paymentType;
    }

}
