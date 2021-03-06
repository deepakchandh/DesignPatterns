package com.java.designpatterns.creational.singleton;

public class EagerInitializedSingleton {

    // created instance of singleton in static initializer
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    //private constructor to avoid client applications to use constructor
    private EagerInitializedSingleton(){
        System.out.println("Singleton being initialized");
    }

    public static EagerInitializedSingleton getInstance(){
        return instance;
    }
// But in most of the scenarios, Singleton classes are created for resources such as File System, Database connections, etc.
    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        EagerInitializedSingleton singleton = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton singletonTwo = EagerInitializedSingleton.getInstance();
    }

}
