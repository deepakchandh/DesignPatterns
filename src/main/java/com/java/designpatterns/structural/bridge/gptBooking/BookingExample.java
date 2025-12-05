package com.java.designpatterns.structural.bridge.gptBooking;
// https://chatgpt.com/c/68f75142-c180-8323-9246-c7b674a046f8

// Bridge Pattern decouples an abstraction from its implementation so both can vary independently.


interface PaymentProcessor {
    void pay(double amount);
}

class CreditCardPayment implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PayAtProperty implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.println("Payment will be collected at property.");
    }
}

class UpiPayment implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI.");
    }
}


abstract class Booking {

    protected PaymentProcessor paymentProcessor;

    public Booking(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public abstract void bookRoom(double amount);
}

class HotelBooking extends Booking{

    public HotelBooking(PaymentProcessor paymentProcessor) {
        super(paymentProcessor);
    }

    @Override
    public void bookRoom(double amount) {
        System.out.println("Hotel booking initiated...");
        paymentProcessor.pay(amount);
    }
}

class ApartmentBooking extends Booking {

    public ApartmentBooking(PaymentProcessor paymentProcessor) {
        super(paymentProcessor);
    }

    @Override
    public void bookRoom(double amount) {
        System.out.println("Apartment booking initiated...");
        paymentProcessor.pay(amount);
    }
}

public class BookingExample {

    public static void main(String[] args) {

        Booking hotelBooking = new HotelBooking(new CreditCardPayment());
        hotelBooking.bookRoom(5000);

        Booking aptBooking = new ApartmentBooking(new UpiPayment());
        aptBooking.bookRoom(3500);

        Booking hotelPayLater = new HotelBooking(new PayAtProperty());
        hotelPayLater.bookRoom(4500);
    }
}
