package com.java.designpatterns.behavioral.strategy;

interface PaymentStrategy {
    void pay(int amount);
}

// Concrete strategies
class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}
class UpiPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy strategy;
    public ShoppingCart(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    public void checkout(int amount) {
        strategy.pay(amount);
    }
}

public class StrategyDemo {
    public static void main(String[] args) {
        ShoppingCart cart1 = new ShoppingCart(new CreditCardPayment());
        cart1.checkout(100);

        ShoppingCart cart2 = new ShoppingCart(new UpiPayment());
        cart2.checkout(200);
    }
}
