package com.java.designpatterns.behavioral.chainofresp;

import java.util.Scanner;

class Currency{
    private int amount;

    public Currency(int amount){
        this.amount = amount;
    }

    public int getAmount(){
        return this.amount;
    }
}

interface DispenseChain {
    void setNextChain(DispenseChain nextChain);
    void dispense(Currency cur);
}

class Dollar50Dispenser implements DispenseChain{

    private DispenseChain chain;
    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount() >= 50){
            int num = cur.getAmount()/50;
            int remainder = cur.getAmount() % 50;
            System.out.println("Dispensing "+num+" 50$ note");
            if(remainder !=0)
                this.chain.dispense(new Currency(remainder));
        }else{
            this.chain.dispense(cur);
        }
    }
}

class Dollar20Dispenser implements DispenseChain{

    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain=nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount() >= 20){
            int num = cur.getAmount()/20;
            int remainder = cur.getAmount() % 20;
            System.out.println("Dispensing "+num+" 20$ note");
            if(remainder !=0)
                this.chain.dispense(new Currency(remainder));
        }else{
            this.chain.dispense(cur);
        }
    }

}

class Dollar10Dispenser implements DispenseChain {

    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain=nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount() >= 10){
            int num = cur.getAmount()/10;
            int remainder = cur.getAmount() % 10;
            System.out.println("Dispensing "+num+" 10$ note");
            if(remainder !=0) this.chain.dispense(new Currency(remainder));
        }else{
            this.chain.dispense(cur);
        }
    }

}

public class ChainOfRespPattern {

    private DispenseChain c1;

    public ChainOfRespPattern() {
        this.c1 = new Dollar50Dispenser();
        DispenseChain c2 = new Dollar20Dispenser();
        DispenseChain c3 = new Dollar10Dispenser();

        // set the chain of responsibility
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }
    public static void main(String[] args) {
        ChainOfRespPattern atmDispenser = new ChainOfRespPattern();

        while (true) {
            int amount = 0;
            System.out.println("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                System.out.println("Amount should be in multiple of 10s.");
                return;
            }
            // process the request
            atmDispenser.c1.dispense(new Currency(amount));
        }
    }

}
