package com.java.designpatterns.structural.flyweight;

// Provides ways to decrease the object count
// Particularly used when we need to create large no of similar objects
// FlyWeight objects are immutable

// we use hashmap here, for getting and creating objects

// --> sharing state among a large number of fine-grained objects for efficiency.
import java.util.HashMap;
import java.util.Random;

interface Player{
    public void assignWeapon(String weapon);
    public void mission();
}

class Terrorist implements Player{
    // Intrinsic Attribute
    private final String task;

    // Extrinsic Attribute
    private String weapon;

    Terrorist(){
        task = "Plant a Bomb";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public void mission() {
        System.out.println("Terrorist with weapon "
                + weapon + "|" + " Task is " + task);
    }
}

class CounterTerrorist implements Player{
    // Intrinsic Attribute
    private final String task;

    // Extrinsic Attribute
    private String weapon;

    CounterTerrorist(){
        task = "Diffuse Bomb";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public void mission() {
        System.out.println("CounterTerrorist with weapon "
                + weapon + "|" + " Task is " + task);
    }
}

// Class used to get a player using HashMap (Returns
// an existing player if a player of given type exists.
// Else creates a new player and returns it.

class PlayerFactory{
    private static HashMap<String, Player> hm = new HashMap<String, Player>();

    public static Player getPlayer(String type){
        Player p = null;
        if(hm.containsKey(type))
            return hm.get(type);
        else{
            switch(type)
            {
                case "Terrorist":
                    System.out.println("Terrorist Created");
                    p = new Terrorist();
                    break;
                case "CounterTerrorist":
                    System.out.println("Counter Terrorist Created");
                    p = new CounterTerrorist();
                    break;
                default :
                    System.out.println("Unreachable code!");
            }
            hm.put(type, p);
            return p;
        }
    }
}

public class FlyweightPattern {
    private static String[] playerType =
            {"Terrorist", "CounterTerrorist"};
    private static String[] weapons =
            {"AK-47", "Maverick", "Gut Knife", "Desert Eagle"};

    public static void main(String args[])
    {
        Random r = new Random();
        for (int i=0;i<10;i++){
            Player player = PlayerFactory.getPlayer(playerType[r.nextInt(playerType.length)]);
            player.assignWeapon(weapons[r.nextInt(playerType.length)]);
            player.mission();
        }
    }

}
