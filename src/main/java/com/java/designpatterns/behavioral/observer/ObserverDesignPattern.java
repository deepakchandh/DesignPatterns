package com.java.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

interface Observer{
    void notify(String post);
}

class SocialMediaPost implements Observer{

    @Override
    public void notify(String post) {
        System.out.println("SocialMediaNotifier: New post published :"+post);
    }
}

class SubscribedUserNotifier implements Observer{
    @Override
    public void notify(String post) {
        System.out.println("SubscribedUserNotifier: New post published :"+post);
    }
}

interface Subject{
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class Mediums implements Subject{

    private String post;
    private List<Observer> listOfObserver = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        listOfObserver.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listOfObserver.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : listOfObserver) {
            observer.notify(post);
        }
    }

    public void newPost(String post){
        this.post = post;
        notifyObservers();
    }
}

public class ObserverDesignPattern {

    public static void main(String[] args) {
        SocialMediaPost socialMediaPost = new SocialMediaPost();
        SubscribedUserNotifier subscribedUserNotifier = new SubscribedUserNotifier();

        Mediums mediumBlogPost = new Mediums();
        mediumBlogPost.addObserver(socialMediaPost);
        mediumBlogPost.addObserver(subscribedUserNotifier);
        mediumBlogPost.newPost("Analysis of Design Patterns");

    }

}
