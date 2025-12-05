package com.java.designpatterns.structural.decorator.gptBooking;

interface Room {
    double getCost();
    String getDescription();
}


class BasicRoom implements Room {
    @Override
    public double getCost() {
        return 3000;
    }

    @Override
    public String getDescription() {
        return "Basic Room";
    }
}

abstract class RoomDecorator implements Room {
    protected Room room;

    public RoomDecorator(Room room) {
        this.room = room;
    }

    public double getCost() {
        return room.getCost();
    }

    public String getDescription() {
        return room.getDescription();
    }
}

class BreakfastDecorator extends RoomDecorator {

    public BreakfastDecorator(Room room) {
        super(room);
    }

    @Override
    public double getCost() {
        return room.getCost() + 500;
    }

    @Override
    public String getDescription() {
        return room.getDescription() + ", Breakfast Included";
    }
}

class ExtraBedDecorator extends RoomDecorator {

    public ExtraBedDecorator(Room room) {
        super(room);
    }

    @Override
    public double getCost() {
        return room.getCost() + 1000;
    }

    @Override
    public String getDescription() {
        return room.getDescription() + ", Extra Bed";
    }
}

public class BookingDecoratorExample {

    public static void main(String[] args) {
        Room room = new BasicRoom();

        // Add breakfast
        room = new BreakfastDecorator(room);

        // Add extra bed
        room = new ExtraBedDecorator(room);

        System.out.println(room.getDescription());
        System.out.println("Total cost: " + room.getCost());
    }
}
