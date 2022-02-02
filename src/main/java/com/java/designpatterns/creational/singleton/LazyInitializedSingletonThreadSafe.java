package com.java.designpatterns.creational.singleton;

// To fix the problem, you have to synchronize threads during first creation of the Singleton object.
public class LazyInitializedSingletonThreadSafe {

    private static volatile LazyInitializedSingletonThreadSafe instance;
    public String value;

    private  LazyInitializedSingletonThreadSafe(String value){
        this.value = value;
    }

    public static LazyInitializedSingletonThreadSafe getInstance(String value){
// Double Check Locking - DCL,
        //  It exists to prevent race condition between multiple threads that may
        // attempt to get singleton instance at the same time, creating separate
        // instances as a result.
        LazyInitializedSingletonThreadSafe result = instance;
        if (result != null){
            return result;
        }
        synchronized (LazyInitializedSingletonThreadSafe.class){
            if(instance == null)
                instance =  new LazyInitializedSingletonThreadSafe(value);
            return instance;
        }
    }

}

class MainApp{
    public static void main(String[] args){
        System.out.println("If you see different values, then 2 singletons were created" + "\n\n" +
                "RESULT:" + "\n");
        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable{
        @Override
        public void run() {
            LazyInitializedSingletonThreadSafe singletonThreadSafe = LazyInitializedSingletonThreadSafe.getInstance("FOO");
            System.out.println(singletonThreadSafe.value);
        }
    }

    static class ThreadBar implements Runnable{
        @Override
        public void run() {
            LazyInitializedSingletonThreadSafe singletonThreadSafe = LazyInitializedSingletonThreadSafe.getInstance("BAR");
            System.out.println(singletonThreadSafe.value);
        }
    }
}
