package com.java.designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

// should be used when a group of objects should behave as a single object
//Base Component
interface Shape{
    public void draw(String fillColor);
}

//Leaf Objects
class Triangle implements Shape{
    @Override
    public void draw(String fillColor) {
        System.out.println("Drawing Triangle with color "+fillColor);
    }
}

class Circle implements Shape{
    @Override
    public void draw(String fillColor) {
        System.out.println("Drawing Circle with color "+fillColor);
    }
}

//Composite pattern on leaf objects- key part
// it contains a group of leaf objects and provide methods for add, delete, deleteAll from group
class Drawing implements Shape{

    private List<Shape> shapes = new ArrayList<>();
    @Override
    public void draw(String fillColor) {
        for(Shape sh : shapes)
        {
            sh.draw(fillColor);
        }
    }

    public void add(Shape s){
        this.shapes.add(s);
    }

    public void remove(Shape s){
        this.shapes.remove(s);
    }

    //removing all the shapes
    public void clear(){
        System.out.println("Clearing all the shapes from drawing");
        this.shapes.clear();
    }


}

public class CompositePatternTest {

    public static void main(String[] args) {
        Shape tri = new Triangle();
        Shape tri1 = new Triangle();
        Shape cir = new Circle();

        Drawing drawing = new Drawing();
        drawing.add(tri1);
        drawing.add(tri1);
        drawing.add(cir);

        drawing.draw("Red");

        drawing.clear();

        drawing.add(tri);
        drawing.add(cir);
        drawing.draw("Green");
    }

}
