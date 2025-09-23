package com.java.designpatterns.creational.factory;

import java.util.Map;

// https://freedium.cfd/https://medium.com/@rameshfadatare/master-factory-pattern-in-a-spring-boot-project-with-real-world-examples-f75e950da70b
interface PaymentProcessor {
    void pay(String orderId, double amount);
}

// @Component("creditCard")
class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid " + amount + " using Credit Card for Order " + orderId);
    }
}
// @Component("paypal")

class PayPalProcessor implements PaymentProcessor {
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid " + amount + " using PayPal for Order " + orderId);
    }
}

// @Component("upi")
class UpiProcessor implements PaymentProcessor {
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid " + amount + " using UPI for Order " + orderId);
    }
}

class PaymentFactory {
    private final Map<String, PaymentProcessor> paymentMap;


    public PaymentFactory(Map<String, PaymentProcessor> paymentMap) {
        this.paymentMap = paymentMap;
    }

    public PaymentProcessor getProcessor(String method) {
        PaymentProcessor processor = paymentMap.get(method.toLowerCase());
        if (processor == null) {
            throw new IllegalArgumentException("Unknown payment method: " + method);
        }
        return processor;
    }

}

/*
How it works:

Spring injects all PaymentProcessor beans into the map
The bean name (from @Component("paypal")) becomes the key
You fetch the correct implementation based on the method input
 */

public class FactoryRameshFadatare {
}
