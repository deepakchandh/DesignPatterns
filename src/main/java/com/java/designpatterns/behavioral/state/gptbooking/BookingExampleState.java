package com.java.designpatterns.behavioral.state.gptbooking;
// https://chatgpt.com/c/68f75142-c180-8323-9246-c7b674a046f8

// State Pattern implementation for a Booking.com–style hotel booking lifecycle.
interface BookingState {

    void next(Booking booking);
    void prev(Booking booking);

    void cancel(Booking booking);
    void modify(Booking booking);

    void printStatus();
}

class CreatedState implements BookingState {

    @Override
    public void next(Booking booking) {
        booking.setState(new ConfirmedState());
    }

    @Override
    public void prev(Booking booking) {
        System.out.println("Booking is already in the initial state.");
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Booking cancelled with FULL refund.");
        booking.setState(new CancelledState());
    }

    @Override
    public void modify(Booking booking) {
        System.out.println("Booking modified successfully in CREATED state.");
    }

    @Override
    public void printStatus() {
        System.out.println("Booking created but not yet confirmed.");
    }
}


class ConfirmedState implements BookingState {

    @Override
    public void next(Booking booking) {
        booking.setState(new CheckedInState());
    }

    @Override
    public void prev(Booking booking) {
        booking.setState(new CreatedState());
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Booking cancelled with PARTIAL refund (cancellation policy applies).");
        booking.setState(new CancelledState());
    }

    @Override
    public void modify(Booking booking) {
        System.out.println("Booking modified successfully in CONFIRMED state.");
    }

    @Override
    public void printStatus() {
        System.out.println("Booking confirmed. Hotel has guaranteed your reservation.");
    }
}

class CheckedInState implements BookingState {

    @Override
    public void next(Booking booking) {
        System.out.println("Booking already completed.");
    }

    @Override
    public void prev(Booking booking) {
        booking.setState(new ConfirmedState());
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Cannot cancel. Guest already checked in.");
    }

    @Override
    public void modify(Booking booking) {
        System.out.println("Cannot modify booking after check-in.");
    }

    @Override
    public void printStatus() {
        System.out.println("Guest has checked in.");
    }
}
// include checkedout state if required.

class CancelledState implements BookingState {

    @Override
    public void next(Booking booking) {
        System.out.println("Booking is cancelled. No further transitions allowed.");
    }

    @Override
    public void prev(Booking booking) {
        System.out.println("Cannot go back. Booking is cancelled.");
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Already cancelled.");
    }

    @Override
    public void modify(Booking booking) {
        System.out.println("Cannot modify a cancelled booking.");
    }

    @Override
    public void printStatus() {
        System.out.println("Booking has been cancelled.");
    }
}
class Booking {

    private BookingState state;

    private String bookingId;
    private String userId;
    private String hotelId;

    public Booking(String bookingId, String userId, String hotelId) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.hotelId = hotelId;
        this.state = new CreatedState();
    }

    public void setState(BookingState state) {
        this.state = state;
    }

    public BookingState getState() {
        return state;
    }

    public void nextState() {
        state.next(this);
    }

    public void previousState() {
        state.prev(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public void modify() {
        state.modify(this);
    }

    public void printStatus() {
        state.printStatus();
    }
}


public class BookingExampleState {

    public static void main(String[] args) {
        Booking booking = new Booking("B123", "U56", "H99");

        booking.printStatus();    // Created
        booking.modify();         // Allowed
        booking.nextState();

        booking.printStatus();    // Confirmed
        booking.cancel();         // Partial refund
        booking.printStatus();    // Cancelled

        booking.modify();         // Not allowed
        booking.nextState();
    }
}
