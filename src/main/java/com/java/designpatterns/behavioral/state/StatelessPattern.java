package com.java.designpatterns.behavioral.state;

public class StatelessPattern {
    private String state= "";

    public void setState(String state){
        this.state=state;
    }

    public void doAction(){
        if(state.equalsIgnoreCase("ON")){
            System.out.println("TV is turned ON");
        }else if(state.equalsIgnoreCase("OFF")){
            System.out.println("TV is turned OFF");
        }
    }

    public static void main(String args[]){
        StatelessPattern statelessPattern = new StatelessPattern();
        statelessPattern.setState("ON");
        statelessPattern.doAction();

        statelessPattern.setState("OFF");
        statelessPattern.doAction();


    }

}
