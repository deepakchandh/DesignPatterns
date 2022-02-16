package com.java.designpatterns.behavioral.mediator;
// Mediator enables decoupling of objects by introducing a layer in between so that the interaction between objects happen via the layer.
// If the objects interact with each other directly, the system components are tightly-coupled with each other that makes higher maintainability cost and not hard to extend.
// Mediator pattern focuses on providing a mediator between objects for communication and help in implementing lose-coupling between objects.

import java.util.ArrayList;
import java.util.List;

interface ChatMediator{
    public void SendMessage(String msg, User user);
    void addUser(User user);
}

abstract class User{

    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator med, String name){
        this.mediator=med;
        this.name=name;
    }

    public abstract void send(String msg);

    public abstract void receive(String msg);
}

class ChatMediatorImpl implements ChatMediator{

    private List<User> users;

    public ChatMediatorImpl(){
        this.users=new ArrayList<>();
    }

    @Override
    public void SendMessage(String msg, User user) {
        for(User userr: this.users){
            //message should not be received by the user sending it
            if(userr != user){
                userr.receive(msg);
            }
        }
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

}

class UserImpl extends User{

    // since in the base class we have created a constructor, we need to add here too
    public UserImpl(ChatMediator med, String name) {
        super(med, name);
    }

    @Override
    public void send(String msg) {
        System.out.println(this.name+": Sending Message="+msg);
        mediator.SendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.name+": Received Message:"+msg);
    }
}

public class MediatorDesignPattern {

    public static void main(String[] args) {
        ChatMediator chatMediator = new ChatMediatorImpl();
        User user1 = new UserImpl(chatMediator, "Pankaj");
        User user2 = new UserImpl(chatMediator, "Vinay");
        User user3 = new UserImpl(chatMediator, "Akash");
        chatMediator.addUser(user1);
        chatMediator.addUser(user2);
        chatMediator.addUser(user3);
        user1.send("Hi All");
    }

}
