package com.java.designpatterns.others;

enum ShippingType {
    STANDARD {
        @Override
        public double getCost(double weight) {
            return weight * 5.0;
        }
    },
    EXPRESS {
        @Override
        public double getCost(double weight) {
            return weight * 10.0;
        }
    },
    SAME_DAY {
        @Override
        public double getCost(double weight) {
            return weight * 20.0;
        }
    },
    INTERNATIONAL {
        @Override
        public double getCost(double weight) {
            return weight * 50.0;
        }
    },
    OVERNIGHT {
        @Override
        public double getCost(double weight) {
            return weight * 30.0;
        }
    };

    public abstract double getCost(double weight);
}

class ShippingCostCalculator {

    public double calculateShippingCost(ShippingType shippingType, double weight) {
        return shippingType.getCost(weight);
    }
}

public class IfElseReduce {


    public static void main(String[] args) {
        var calculator = new ShippingCostCalculator();
        var cost = calculator.calculateShippingCost(ShippingType.EXPRESS, 2.5);
        System.out.println("Shipping cost: " + cost);
    }
}
