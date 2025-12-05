package com.java.designpatterns.behavioral.observer.gptbooking;

// Observer Pattern notifies multiple listeners automatically whenever a state changes.

import java.util.ArrayList;
import java.util.List;

interface BookingObserver {
    void update(Booking booking, String message);
}

class EmailNotificationObserver implements BookingObserver {
    @Override
    public void update(Booking booking, String message) {
        System.out.println("[EMAIL] Sent to user: " + message);
    }
}

class SmsNotificationObserver implements BookingObserver {
    @Override
    public void update(Booking booking, String message) {
        System.out.println("[SMS] Message sent to user: " + message);
    }
}

class Booking {

    private BookingState state;
    private final List<BookingObserver> observers = new ArrayList<>();

    public Booking() {
        this.state = new CreatedState();
    }

    public void addObserver(BookingObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BookingObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (BookingObserver observer : observers) {
            observer.update(this, message);
        }
    }

    public void setState(BookingState state) {
        this.state = state;
        notifyObservers("Booking state changed to: " + state.getClass().getSimpleName());
    }

    public void cancel() {
        state.cancel(this);
        notifyObservers("Booking cancelled.");
    }

    public void modify() {
        state.modify(this);
        notifyObservers("Booking modified.");
    }

    public void nextState() {
        state.next(this);
    }
}

// --------------------------------------

interface BookingState {

    void next(Booking booking);
    void prev(Booking booking);

    void cancel(Booking booking);
    void modify(Booking booking);

    void printStatus();
}


class  CreatedState implements BookingState {

    @Override
    public void next(Booking booking) {

    }

    @Override
    public void prev(Booking booking) {

    }

    @Override
    public void cancel(Booking booking) {

    }

    @Override
    public void modify(Booking booking) {

    }

    @Override
    public void printStatus() {

    }
}

public class BookingObserverExample {

    public static void main(String[] args) {
        Booking booking = new Booking();

        booking.addObserver(new EmailNotificationObserver());
        booking.addObserver(new SmsNotificationObserver());

        booking.modify();       // modify event
        booking.nextState();    // confirmed
        booking.cancel();
    }
}
