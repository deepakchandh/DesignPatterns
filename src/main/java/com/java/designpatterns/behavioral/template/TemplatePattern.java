package com.java.designpatterns.behavioral.template;

// Template method design pattern is to define an algorithm as a skeleton of operations and leave the details to be implemented by the child classes.
/*
When:
1. Let subclasses implement varying behavior
2. Avoid duplication in the code
 */

abstract class HouseTemplate {

    public final void buildHouse(){
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
    }

    //default implementation
    private void buildWindows() {
        System.out.println("Building Glass Windows");
    }

    //methods to be implemented by subclasses
    public abstract void buildWalls();
    public abstract void buildPillars();

    //default implementation
    private void buildFoundation() {
        System.out.println("Building foundation with cement,iron rods and sand");
    }
}

class WoodenHouse extends HouseTemplate {

    @Override
    public void buildWalls() {
        System.out.println("Building Wooden Walls");
    }

    @Override
    public void buildPillars() {
        System.out.println("Building Pillars with Wood coating");
    }
}

class GlassHouse extends HouseTemplate{

    @Override
    public void buildWalls() {
        System.out.println("Building Glass Walls");
    }

    @Override
    public void buildPillars() {
        System.out.println("Building Pillars with glass coating");
    }
}



public class TemplatePattern {
    public static void main(String[] args) {
        HouseTemplate houseTemplate = new GlassHouse();
        houseTemplate.buildHouse();

    }
}
