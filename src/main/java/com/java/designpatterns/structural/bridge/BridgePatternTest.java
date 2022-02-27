package com.java.designpatterns.structural.bridge;

// The implementation of bridge design pattern follows the notion to prefer Composition over inheritance.
// it is used to deCouple interfaces from impl and hiding impl details from client prgm
interface Color{
    public void applyColor();
}

abstract class Shape{
    protected Color color;
    public Shape(Color color){
        this.color = color;
    }
    abstract public void applyColor();
}

class Triangle extends Shape{

    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void applyColor() {
        System.out.print("Triangle filled with color ");
        color.applyColor();
    }
}

class Pentagon extends Shape{

    public Pentagon(Color color) {
        super(color);
    }

    @Override
    public void applyColor() {
        System.out.print("Pentagon filled with color ");
        color.applyColor();
    }
}

class RedColor implements Color{

    @Override
    public void applyColor() {
        System.out.println("Red.");
    }
}

class GreenColor implements Color{

    @Override
    public void applyColor() {
        System.out.println("Green.");
    }
}

public class BridgePatternTest {
    public static void main(String[] args) {
        Shape tri = new Triangle(new RedColor());
        tri.applyColor();

        Shape pent = new Pentagon(new GreenColor());
        pent.applyColor();
    }
}
