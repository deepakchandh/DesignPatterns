package com.java.designpatterns.behavioral.command;
//https://www.tutorialspoint.com/design_pattern/command_pattern.htm

// This method encapsulates the request by binding together a set of actions on a specific receiver.
// It does so by exposing just one method execute() that causes some actions to be invoked on the receiver.

import java.util.ArrayList;
import java.util.List;

interface Order{
    void execute();
}

class Stock {

    private String name ;
    private int quantity ;

    public Stock(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void buy(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] sold");
    }
}


class BuyStock implements Order{
    private Stock stock;

    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy();
    }
}

class SellStock implements Order{

    private Stock stock;

    public SellStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell();

    }
}

class Broker{
    private List<Order> orders = new ArrayList<>();

    public void takeOrder(Order order){
        orders.add(order);
    }

    public void placeOrder(){
        for(Order order: orders){
            order.execute();
        }
        orders.clear();
    }

}

public class CommandPatternEasy {

    public static void main(String[] args) {
        Stock abcStock = new Stock("itc", 50);
// Req is wrapped under an object as cmd and passed to the invoker object.
        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);
        broker.placeOrder();

    }
}
