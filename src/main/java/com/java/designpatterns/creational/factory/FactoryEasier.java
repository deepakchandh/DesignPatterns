package com.java.designpatterns.creational.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

abstract class Plan{
    protected double rate;

    abstract void getRate();

//    public abstract void display();

    public void calculateBill(int units){
        System.out.println(units*rate);
    }
}

class DomesticPlan extends Plan{

    @Override
    void getRate() {
        this.rate = 3.50;
    }
}

class CommercialPlan extends Plan{

    @Override
    void getRate() {
        this.rate = 7.50;
    }
}

class InstitutionPlan extends Plan{

    @Override
    void getRate() {
        this.rate = 5.50;
    }
}

class PlanFactory{

    public static Plan getPlan(String planType){
        if(planType == null){
            return null;
        }
        if(planType.equalsIgnoreCase("DOMESTICPLAN")) {
            return new DomesticPlan();
        }
        else if(planType.equalsIgnoreCase("COMMERCIALPLAN")){
            return new CommercialPlan();
        }
        else if(planType.equalsIgnoreCase("INSTITUTIONALPLAN")) {
            return new InstitutionPlan();
        }
        return null;
    }

}

public class FactoryEasier {
    public static void main(String[] args) throws IOException {


        System.out.print("Enter the name of plan for which the bill will be generated: ");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String planName=br.readLine();
        System.out.print("Enter the number of units for bill will be calculated: ");
        int units=Integer.parseInt(br.readLine());

        Plan p = PlanFactory.getPlan(planName);
        //call getRate() method and calculateBill()method of DomesticPaln.

        System.out.print("Bill amount for "+planName+" of  "+units+" units is: ");
        p.getRate();
        p.calculateBill(units);
    }
}

