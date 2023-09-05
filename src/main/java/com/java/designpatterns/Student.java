package com.java.designpatterns;

public class Student
{
    public static void main(String[] args)
    {
        Student s1 = new Student();
        s1 = null;
        System.gc();
        System.out.println("Garbage collector is called");
    }

    @Override
    protected void finalize()
    {
        System.out.println("Finalize method is called.");
    }
}
