package com.java.designpatterns.behavioral.strategy;

// A class behavior or its algorithm can be changed at runtime.

// we create objects which represent various strategies and a context object whose behavior varies as per its strategy object.
// The strategy object changes the executing algorithm of the context object.

interface Strategy{
    public int doOperation(int num1, int num2);
}

class OperationAdd implements Strategy{

    @Override
    public int doOperation(int num1, int num2) {
        return num1+num2;
    }
}

class OperationSub implements Strategy{

    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

class OperationMul implements Strategy{

    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}


class Context{
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
public class StrategyPatternDemo {

    public static void main(String[] args) {

        Context context = new Context(new OperationAdd());

        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSub());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMul());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));

    }
}
