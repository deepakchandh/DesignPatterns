package com.java.designpatterns.structural.facade;

/*
You’re building a hotel booking system.
Booking a room involves:

Checking availability

Reserving the room

Charging the customer

Sending confirmation email

You want the client code to just call bookRoom() without managing all these steps individually.

Question:
👉 Which design pattern would best solve this?
 */

class AvailabilityService {
    public boolean check() {
        System.out.println("Checking availability...");
        return true;
    }
}

class ReservationService {
    public void reserve() {
        System.out.println("Reserving room...");
    }
}

class PaymentService {
    public void charge() {
        System.out.println("Charging customer...");
    }
}

class EmailService {
    public void sendConfirmation() {
        System.out.println("Sending confirmation email...");
    }
}

class BookingFacade {
    private AvailabilityService availability = new AvailabilityService();
    private ReservationService reservation = new ReservationService();
    private PaymentService payment = new PaymentService();
    private EmailService email = new EmailService();

    public void bookRoom() {
        if (availability.check()) {
            reservation.reserve();
            payment.charge();
            email.sendConfirmation();
        } else {
            System.out.println("No rooms available.");
        }
    }
}

public class FacadeGPT {

    public static void main(String[] args) {
        BookingFacade booking = new BookingFacade();
        booking.bookRoom();
    }
}
