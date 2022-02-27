package com.java.designpatterns.behavioral.state;

interface State{
    public void doAction();
}

class TVStartState implements State {

    @Override
    public void doAction() {
        System.out.println("TV is turned ON");
    }

}

class TVStopState implements State {

    @Override
    public void doAction() {
        System.out.println("TV is turned OFF");
    }

}
class TVContext implements State{
    private State state;

    public void setState(State state) {
        this.state=state;
    }

    public State getState() {
        return this.state;
    }

    @Override
    public void doAction() {
        this.state.doAction();
    }
}

public class statefulPattern {
    public static void main(String[] args) {
        TVContext context = new TVContext();

        context.setState(new TVStartState());
        context.doAction();

        context.setState(new TVStopState());
        context.doAction();
    }
}
