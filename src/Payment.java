public class Payment {

    public void makePayment(Passenger passenger, double amount, SeatClass seatClass, PaymentType paymentType){
        boolean paid = false;
        if(seatClass ==  SeatClass.FIRSTCLASS) {
            if (amount >= Airline.getPriceOfFirstClass())
                paid = true;
        }
        if(seatClass ==  SeatClass.BUSINESS) {
            if (amount >= Airline.getPriceOfBusinessClass())
                paid = true;
        }
        if(seatClass ==  SeatClass.ECONOMY) {
            if (amount >= Airline.getPriceOfEconomyClass())
                paid = true;
        }
        passenger.payment(paid);
        passenger.setPaymentType(paymentType);
    }
}
