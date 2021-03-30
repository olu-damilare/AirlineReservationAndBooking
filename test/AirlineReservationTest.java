
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirlineReservationTest {
    Aeroplane aeroplane;
    Airline airline;
    FlightReservation reservation;
    FlightBooking flightBooking;
    Payment payment;
    private BoardingPass boardingPass;

    @BeforeEach
    void setUp() {
        aeroplane = new Aeroplane("Airforce1");
        airline = new Airline("Dana Airline", aeroplane);
        reservation = new FlightReservation();
        flightBooking = new FlightBooking();
        payment = new Payment();
        boardingPass = new BoardingPass();

    }

    @AfterEach
    void tearDown() {
        aeroplane = null;
        airline = null;
        reservation = null;
        flightBooking = null;
        payment = null;
        boardingPass = null;
        FlightReservation.emptyReservedPassengerList();
        FlightBooking.emptyHasBookedArray();
    }

    @Test
    void testThatAirlineCanBeCreated() {
        assertEquals("""
                NewAirline{
                Airline Name='Dana Airline'
                Number of aeroplanes='1'}""", airline.toString());
    }

    @Test
    void testThatAirlineCanHaveAeroplanes() {

        assertEquals(1, airline.getTotalNumberOfAeroplanes());
        Aeroplane secondAeroplane = new Aeroplane("Eagles");
        airline.acquireAeroplane(secondAeroplane);
        assertEquals(2, airline.getTotalNumberOfAeroplanes());
    }

    @Test
    void testThatAeroplaneHasFiftySeats() {
        assertEquals(50, aeroplane.getNumberOfSeatsInPlane());
    }

    @Test
    void testThatAirlineHasFlightDetails() {
        FlightDetails flightDetails = new FlightDetails();
        assertEquals("FlightDetails{\n" +
                "Number Of Passengers = 0\n" +
                "Host Details = null\n" +
                "Pilot Details = null\n" +
                "Passenger Info = null\n" +
                "Flight  Number = 0\n" +
                "}", flightDetails.toString());
    }

    @Test
    void testThatPassengerCanReserveFirstClassSeat() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        reservation.reserveFlight(passenger, SeatClass.FIRSTCLASS);
        assertTrue(FlightReservation.hasReserved(passenger));
        assertEquals(1, reservation.getReservationID(passenger));
        assertEquals(SeatClass.FIRSTCLASS, reservation.getPassengerReservedSeatType(passenger));
    }

    @Test
    void testThatPassengerCanReserveBusinessClassSeat() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        reservation.reserveFlight(passenger, SeatClass.BUSINESS);

        assertEquals(1, reservation.getReservationID(passenger));
        assertTrue(FlightReservation.hasReserved(passenger));
        assertEquals(SeatClass.BUSINESS, reservation.getPassengerReservedSeatType(passenger));
    }

    @Test
    void testThatPassengerCanReserveEconomyClassSeat() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        reservation.reserveFlight(passenger, SeatClass.ECONOMY);

        assertEquals(1, reservation.getReservationID(passenger));
        assertTrue(FlightReservation.hasReserved(passenger));
        assertEquals(SeatClass.ECONOMY, reservation.getPassengerReservedSeatType(passenger));
    }

    @Test
    void testThatMultiplePassengersCanReserveFirstClassSeats() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger1 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        reservation.reserveFlight(passenger, SeatClass.FIRSTCLASS);
        reservation.reserveFlight(passenger1, SeatClass.FIRSTCLASS);

        assertTrue(FlightReservation.hasReserved(passenger));
        assertTrue(FlightReservation.hasReserved(passenger1));
        assertEquals(1, reservation.getReservationID(passenger));
        assertEquals(2, reservation.getReservationID(passenger1));
        assertEquals(SeatClass.FIRSTCLASS, reservation.getPassengerReservedSeatType(passenger));
        assertEquals(SeatClass.FIRSTCLASS, reservation.getPassengerReservedSeatType(passenger1));

    }

    @Test
    void testThatMultiplePassengersCanReserveBusinessClassSeats() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger1 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        reservation.reserveFlight(passenger, SeatClass.BUSINESS);
        reservation.reserveFlight(passenger1, SeatClass.BUSINESS);

        assertTrue(reservation.hasReserved(passenger));
        assertTrue(reservation.hasReserved(passenger1));
        assertEquals(SeatClass.BUSINESS, reservation.getPassengerReservedSeatType(passenger));
        assertEquals(SeatClass.BUSINESS, reservation.getPassengerReservedSeatType(passenger1));

    }

    @Test
    void testThatMultiplePassengersCanReserveEconomyClassSeats() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger1 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        reservation.reserveFlight(passenger, SeatClass.ECONOMY);
        assertTrue(FlightReservation.hasReserved(passenger));
        assertEquals(1, reservation.getReservationID(passenger));
        assertEquals(SeatClass.ECONOMY, reservation.getPassengerReservedSeatType(passenger));

        reservation.reserveFlight(passenger1, SeatClass.ECONOMY);
        assertTrue(FlightReservation.hasReserved(passenger1));
        assertEquals(2, reservation.getReservationID(passenger1));
        assertEquals(SeatClass.ECONOMY, reservation.getPassengerReservedSeatType(passenger1));

    }


    @Test
    void testThatPassengerCanCancelReservation() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        reservation.reserveFlight(passenger, SeatClass.BUSINESS);
        assertTrue(FlightReservation.hasReserved(passenger));
        assertEquals(1, reservation.getReservationID(passenger));
        assertEquals(SeatClass.BUSINESS, reservation.getPassengerReservedSeatType(passenger));

        reservation.cancelReservation(passenger);
        assertFalse(FlightReservation.hasReserved(passenger));
        assertEquals(0, reservation.getReservationID(passenger));
        assertNull(reservation.getPassengerReservedSeatType(passenger));
    }

    @Test
    void testThatPassengerCanBookFirstClassSeatWithReservationID() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        reservation.reserveFlight(passenger, SeatClass.FIRSTCLASS);
        assertTrue(FlightReservation.hasReserved(passenger));
        assertEquals(1, reservation.getReservationID(passenger));

        flightBooking.bookFlight(reservation.getReservationID(passenger));
        assertEquals(1, flightBooking.getTotalNumberOfFirstClassSeatsBooked());
        assertEquals(SeatClass.FIRSTCLASS, FlightBooking.getPassengerBookedSeatType(passenger));

        assertTrue(flightBooking.hasBooked(reservation.getReservationID(passenger)));

    }

    @Test
    void testThatPassengerCanBookBusinessClassSeatWithReservationID() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        reservation.reserveFlight(passenger, SeatClass.BUSINESS);
        assertTrue(FlightReservation.hasReserved(passenger));
        assertEquals(1, reservation.getReservationID(passenger));

        flightBooking.bookFlight(reservation.getReservationID(passenger));
        assertEquals(1, flightBooking.getTotalNumberOfBusinessClassSeatsBooked());
        assertEquals(SeatClass.BUSINESS, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));

    }

    @Test
    void testThatPassengerCanBookEconomyClassSeatWithReservationID() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        reservation.reserveFlight(passenger, SeatClass.ECONOMY);
        assertTrue(FlightReservation.hasReserved(passenger));
        assertEquals(1, reservation.getReservationID(passenger));

        flightBooking.bookFlight(reservation.getReservationID(passenger));

        assertEquals(SeatClass.ECONOMY, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));

        assertEquals(1, flightBooking.getTotalNumberOfEconomyClassSeatsBooked());

    }

    @Test
    void testThatSameReservationIDCannotBeUsedToBookAFlightTwice() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        reservation.reserveFlight(passenger, SeatClass.ECONOMY);
        assertTrue(FlightReservation.hasReserved(passenger));
        assertEquals(1, reservation.getReservationID(passenger));

        flightBooking.bookFlight(reservation.getReservationID(passenger));
        assertEquals(1, flightBooking.getTotalNumberOfEconomyClassSeatsBooked());
        assertEquals(SeatClass.ECONOMY, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));

        flightBooking.bookFlight(reservation.getReservationID(passenger));
        assertEquals(1, flightBooking.getTotalNumberOfEconomyClassSeatsBooked());

    }

    @Test
    void testThatPassengerCanBookFirstClassSeatWithoutHavingToFirstReserve() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");

        flightBooking.bookFlight(passenger, SeatClass.FIRSTCLASS);

        assertEquals(1, flightBooking.getTotalNumberOfFirstClassSeatsBooked());
        assertEquals(SeatClass.FIRSTCLASS, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));
    }

    @Test
    void testThatPassengerCanBookBusinessClassSeatWithoutHavingToFirstReserve() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");

        flightBooking.bookFlight(passenger, SeatClass.BUSINESS);

        assertEquals(1, flightBooking.getTotalNumberOfBusinessClassSeatsBooked());
        assertEquals(SeatClass.BUSINESS, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));
    }

    @Test
    void testThatPassengerCanBookEconomyClassSeatWithoutHavingToFirstReserve() {
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");

        flightBooking.bookFlight(passenger, SeatClass.ECONOMY);

        assertEquals(1, flightBooking.getTotalNumberOfEconomyClassSeatsBooked());
        assertEquals(SeatClass.ECONOMY, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));
    }

    @Test
    void testThatOnlyTenFirstClassSeatsCanBeBooked() {
        Passenger passenger1 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger2 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger3 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger4 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger5 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger6 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger7 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger8 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger9 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger10 = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        Passenger passenger11 = new Passenger("Olu Jola", "0000", "bina@jolo.com");


        flightBooking.bookFlight(passenger1, SeatClass.FIRSTCLASS);
        flightBooking.bookFlight(passenger2, SeatClass.FIRSTCLASS);
        flightBooking.bookFlight(passenger3, SeatClass.FIRSTCLASS);
        flightBooking.bookFlight(passenger4, SeatClass.FIRSTCLASS);
        flightBooking.bookFlight(passenger5, SeatClass.FIRSTCLASS);
        flightBooking.bookFlight(passenger6, SeatClass.FIRSTCLASS);
        flightBooking.bookFlight(passenger7, SeatClass.FIRSTCLASS);
        flightBooking.bookFlight(passenger8, SeatClass.FIRSTCLASS);
        flightBooking.bookFlight(passenger9, SeatClass.FIRSTCLASS);
        flightBooking.bookFlight(passenger10, SeatClass.FIRSTCLASS);
        flightBooking.bookFlight(passenger11, SeatClass.FIRSTCLASS);

        assertEquals(10, flightBooking.getTotalNumberOfFirstClassSeatsBooked());
    }

    @Test
    void testThatAirlineCanSetThePriceOfFirstClassBookingPass(){
        airline.setPriceOfFirstClass(1000);
        assertEquals(1000, airline.getPriceOfFirstClass());
    }
    @Test
    void testThatAirlineCanSetThePriceOfBusinessClassBookingPass(){
        airline.setPriceOfBusinessClass(700);
        assertEquals(700, airline.getPriceOfBusinessClass());
    }
    @Test
    void testThatAirlineCanSetThePriceOfEconomyClassBookingPass(){
        airline.setPriceOfEconomyClass(500);
        assertEquals(500, airline.getPriceOfEconomyClass());
    }

    @Test
    void testThatPassengerCanMakePaymentForFirstClassBookingPass(){
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        flightBooking.bookFlight(passenger, SeatClass.FIRSTCLASS);
        assertEquals(1, flightBooking.getTotalNumberOfFirstClassSeatsBooked());
        assertEquals(SeatClass.FIRSTCLASS, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));
        airline.setPriceOfFirstClass(1000);
        assertEquals(1000, Airline.getPriceOfFirstClass());

        payment.makePayment(passenger, 1000, SeatClass.FIRSTCLASS, PaymentType.MASTERCARD);

        assertTrue(passenger.hasPaid());
        assertEquals(PaymentType.MASTERCARD, passenger.getPaymentType());
    }

    @Test
    void testThatPassengerCanMakePaymentForBusinessClassBookingPass(){
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        flightBooking.bookFlight(passenger, SeatClass.BUSINESS);
        assertEquals(1, flightBooking.getTotalNumberOfBusinessClassSeatsBooked());
        assertEquals(SeatClass.BUSINESS, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));
        airline.setPriceOfBusinessClass(700);
        assertEquals(700, Airline.getPriceOfBusinessClass());

        payment.makePayment(passenger, 700, SeatClass.BUSINESS, PaymentType.CASH);

        assertTrue(passenger.hasPaid());
        assertEquals(PaymentType.CASH, passenger.getPaymentType());
    }
    @Test
    void testThatPassengerCanMakePaymentForEconomyClassBookingPass(){
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        flightBooking.bookFlight(passenger, SeatClass.ECONOMY);
        assertEquals(1, flightBooking.getTotalNumberOfEconomyClassSeatsBooked());
        assertEquals(SeatClass.ECONOMY, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));
        airline.setPriceOfEconomyClass(500);
        assertEquals(500, Airline.getPriceOfEconomyClass());

        payment.makePayment(passenger, 500, SeatClass.ECONOMY, PaymentType.POS);

        assertTrue(passenger.hasPaid());
        assertEquals(PaymentType.POS, passenger.getPaymentType());
    }
    @Test
    void testThatPassengerCannotMakePaymentWithoutBookingFlight(){
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");

        airline.setPriceOfEconomyClass(500);
        assertEquals(500, Airline.getPriceOfEconomyClass());

        payment.makePayment(passenger, 500, SeatClass.ECONOMY, PaymentType.POS);

        assertFalse(passenger.hasPaid());
        assertEquals(PaymentType.POS, passenger.getPaymentType());
    }
    @Test
    void testThatPassengerCannotMakePaymentForFirstClassWithAmountLessThanTheSetPrice(){
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        flightBooking.bookFlight(passenger, SeatClass.FIRSTCLASS);
        assertEquals(1, flightBooking.getTotalNumberOfFirstClassSeatsBooked());
        assertEquals(SeatClass.FIRSTCLASS, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));
        airline.setPriceOfFirstClass(1000);
        assertEquals(1000, Airline.getPriceOfFirstClass());

        payment.makePayment(passenger, 500, SeatClass.FIRSTCLASS, PaymentType.MASTERCARD);

        assertFalse(passenger.hasPaid());
    }
    @Test
    void testThatPassengerCannotMakePaymentForBusinessClassWithAmountLessThanTheSetPrice(){
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        flightBooking.bookFlight(passenger, SeatClass.BUSINESS);
        assertEquals(1, flightBooking.getTotalNumberOfBusinessClassSeatsBooked());
        assertEquals(SeatClass.BUSINESS, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));
        airline.setPriceOfBusinessClass(700);
        assertEquals(700, Airline.getPriceOfBusinessClass());

        payment.makePayment(passenger, 600, SeatClass.BUSINESS, PaymentType.MASTERCARD);

        assertFalse(passenger.hasPaid());
    }
    @Test
    void testThatPassengerCannotMakePaymentForEconomyClassWithAmountLessThanTheSetPrice(){
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        flightBooking.bookFlight(passenger, SeatClass.ECONOMY);
        assertEquals(1, flightBooking.getTotalNumberOfEconomyClassSeatsBooked());
        assertEquals(SeatClass.ECONOMY, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(flightBooking.hasBooked(passenger.getSeatNumber()));
        airline.setPriceOfEconomyClass(500);
        assertEquals(500, Airline.getPriceOfEconomyClass());

        payment.makePayment(passenger, 200, SeatClass.ECONOMY, PaymentType.MASTERCARD);

        assertFalse(passenger.hasPaid());
    }
    @Test
    void testThatBoardingPassCanBeGeneratedWithPassengerDetails(){
        Passenger passenger = new Passenger("Olu Jola", "0000", "bina@jolo.com");
        flightBooking.bookFlight(passenger, SeatClass.ECONOMY);
        assertEquals(1, flightBooking.getTotalNumberOfEconomyClassSeatsBooked());
        assertEquals(SeatClass.ECONOMY, FlightBooking.getPassengerBookedSeatType(passenger));
        assertTrue(FlightBooking.hasBooked(passenger.getSeatNumber()));
        airline.setPriceOfEconomyClass(500);
        assertEquals(500, Airline.getPriceOfEconomyClass());

        payment.makePayment(passenger, 500, SeatClass.ECONOMY, PaymentType.MASTERCARD);
        assertTrue(passenger.hasPaid());
       assertEquals("""
               Full Name = 'Olu Jola
               Phone Number = 0000
               Email Address = bina@jolo.com
               Passenger Seat Class = ECONOMY
               Seat Number = 26
               Payment Type = MASTERCARD""", boardingPass.displayBoardingPassInfo(passenger));
    }

}





