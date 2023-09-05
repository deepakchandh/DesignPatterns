package com.java.designpatterns.structural.facade;

//https://www.tutorialspoint.com/design_pattern/facade_pattern.htm

// Hides the complexcities and provides interface to the client using which client can access the system.

// This is more like a factory pattern..
// In Factory, we dynamically create objects for the requested type,
// whereas in Facade, we would have created all objects predefined, define a method with desired operations and then call them when required..

interface Shape {
    void draw();
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}

class ShapeMaker { // this is the Facade //Helper Class
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}

public class FacadeEasy {

    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
