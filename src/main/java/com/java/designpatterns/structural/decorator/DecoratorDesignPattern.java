package com.java.designpatterns.structural.decorator;

// modify the functionality of the object in runtime
interface Car {
    public void assemble();
}

class BasicCar implements Car {
    @Override
    public void assemble() {
        System.out.print("Basic Car.");
    }
}
// it implements component interface and it has HAS-A relationship with component interface

class CarDecorator implements Car{
    protected Car car;
    // in constructor allowing dev to give the Object he wants
    public CarDecorator(Car car){
        this.car = car;
    }
    @Override
    public void assemble() {
        this.car.assemble();
    }
}

class SportsCar extends CarDecorator{

    public SportsCar(Car car) {
        super(car);
    }

    @Override
    public void assemble(){
        super.assemble();
        System.out.print(" Adding features of Sports Car.");
    }
}

class LuxuryCar extends CarDecorator{

    public LuxuryCar(Car car) {
        super(car);
    }

    @Override
    public void assemble(){
        super.assemble();
        System.out.print(" Adding features of Luxury Car.");
    }
}


public class DecoratorDesignPattern {
    public static void main(String[] args) {
        Car sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();
        System.out.println("\n*****");
        // defining with which ever objects I want, during the run-time
        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();
    }
}
