package com.java.designpatterns.creational.singleton;

public class SingletonPattern {
    private static SingletonPattern singletonPattern;

    // private constructor to force use of
    // getInstance() to create Singleton object
    private SingletonPattern(){}

    public static SingletonPattern getInstance(){
        if(singletonPattern == null)
            singletonPattern = new SingletonPattern();
        return singletonPattern;
    }

}
