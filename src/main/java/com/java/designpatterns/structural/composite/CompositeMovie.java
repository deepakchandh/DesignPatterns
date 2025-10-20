package com.java.designpatterns.structural.composite;

import java.util.*;

interface CinemaComponent {
    void showDetails();
}

class ShowTime implements CinemaComponent {
    private String movieName;
    private String time;

    public ShowTime(String movieName, String time) {
        this.movieName = movieName;
        this.time = time;
    }

    @Override
    public void showDetails() {
        System.out.println("   🎞️ " + movieName + " at " + time);
    }
}

class Hall implements CinemaComponent {
    private String name;
    private List<CinemaComponent> showTimes = new ArrayList<>();

    public Hall(String name) {
        this.name = name;
    }

    public void addShow(CinemaComponent show) {
        showTimes.add(show);
    }

    public void removeShow(CinemaComponent show) {
        showTimes.remove(show);
    }

    @Override
    public void showDetails() {
        System.out.println("🛋️ Hall: " + name);
        for (CinemaComponent show : showTimes) {
            show.showDetails();
        }
    }
}

class Cinema implements CinemaComponent {
    private String name;
    private List<CinemaComponent> halls = new ArrayList<>();

    public Cinema(String name) {
        this.name = name;
    }

    public void addHall(CinemaComponent hall) {
        halls.add(hall);
    }

    @Override
    public void showDetails() {
        System.out.println("🎬 Cinema: " + name);
        for (CinemaComponent hall : halls) {
            hall.showDetails();
        }
    }
}

public class CompositeMovie {
    public static void main(String[] args) {
        ShowTime morning = new ShowTime("Inception", "10:00 AM");
        ShowTime evening = new ShowTime("Interstellar", "6:00 PM");

        Hall hall1 = new Hall("IMAX Hall");
        hall1.addShow(morning);
        hall1.addShow(evening);

        Cinema cinema = new Cinema("PVR Downtown");
        cinema.addHall(hall1);

        cinema.showDetails();
    }
}
