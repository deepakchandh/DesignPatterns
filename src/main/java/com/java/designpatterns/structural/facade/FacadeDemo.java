package com.java.designpatterns.structural.facade;


/*
Definition: Provides a unified, simplified interface to a set of interfaces in a subsystem.

Intent: Hide complexity from clients, make usage easier.

Real-world analogy: A travel agent (facade) books flights, hotels, and cabs for you, so you don’t have to interact with each service individually.
 */

class FlightBooking {
    public void bookFlight(String from, String to) {
        System.out.println("Flight booked from " + from + " to " + to);
    }
}

class HotelBooking {
    public void bookHotel(String location) {
        System.out.println("Hotel booked at " + location);
    }
}

class CabBooking {
    public void bookCab(String location) {
        System.out.println("Cab booked at " + location);
    }
}

class TravelFacade {
    private FlightBooking flightBooking;
    private HotelBooking hotelBooking;
    private CabBooking cabBooking;

    public TravelFacade() {
        this.flightBooking = new FlightBooking();
        this.hotelBooking = new HotelBooking();
        this.cabBooking = new CabBooking();
    }

    public void bookTrip(String from, String to) {
        System.out.println("Starting travel booking...");
        flightBooking.bookFlight(from, to);
        hotelBooking.bookHotel(to);
        cabBooking.bookCab(to);
        System.out.println("Travel booking completed!");
    }
}

public class FacadeDemo {

    public static void main(String[] args) {
        TravelFacade travelFacade = new TravelFacade();

        // Client just calls ONE method, hides all complexity
        travelFacade.bookTrip("Delhi", "Goa");
    }
}
