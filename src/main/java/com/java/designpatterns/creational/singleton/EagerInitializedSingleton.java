package com.java.designpatterns.creational.singleton;

public class EagerInitializedSingleton {

    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    private EagerInitializedSingleton(){
        System.out.println("Singleton being initialized");
    }

    public static EagerInitializedSingleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        EagerInitializedSingleton singleton = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton singletonTwo = EagerInitializedSingleton.getInstance();
    }

}
