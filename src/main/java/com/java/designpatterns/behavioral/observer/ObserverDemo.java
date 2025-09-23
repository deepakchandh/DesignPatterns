package com.java.designpatterns.behavioral.observer;

import java.util.*;

interface Observer {
    void update(String message);
}

class User implements Observer {
    private String name;
    public User(String name) { this.name = name; }
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

class Topic {
    private List<Observer> observers = new ArrayList<>();
    public void subscribe(Observer obs) { observers.add(obs); }
    public void notifyAllObservers(String msg) {
        for (Observer obs : observers) obs.update(msg);
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        Topic news = new Topic();
        news.subscribe(new User("Alice"));
        news.subscribe(new User("Bob"));
        news.notifyAllObservers("New video uploaded!");
    }
}
