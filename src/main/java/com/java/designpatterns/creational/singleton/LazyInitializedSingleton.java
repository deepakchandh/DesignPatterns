package com.java.designpatterns.creational.singleton;

public class LazyInitializedSingleton {

    private static LazyInitializedSingleton instance;
    private String value;

    private LazyInitializedSingleton(String value){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static LazyInitializedSingleton getInstance(String value){
        if(instance == null)
             instance = new LazyInitializedSingleton(value);
        return instance;
    }

    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        LazyInitializedSingleton singleton = LazyInitializedSingleton.getInstance("FOO");
        LazyInitializedSingleton singletonTwo = LazyInitializedSingleton.getInstance("BAR");
        System.out.println(singleton.value);
        System.out.println(singletonTwo.value);
    }
}
