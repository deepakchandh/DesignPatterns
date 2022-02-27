package com.java.designpatterns.structural.proxy;

import java.util.ArrayList;
import java.util.List;

interface Internet{
    public void connectTo(String serverhost) throws Exception;
}

class RealInternet implements Internet{

    @Override
    public void connectTo(String serverhost) throws Exception {
        System.out.println("Connecting to "+ serverhost);
    }
}

class ProxyInternet implements Internet{

    private Internet realInternet = new RealInternet();
    private static List<String> bannedSites;
    static {
        bannedSites = new ArrayList<>();
        bannedSites.add("abc.com");
        bannedSites.add("def.com");
        bannedSites.add("ijk.com");
        bannedSites.add("lnm.com");
    }
    @Override
    public void connectTo(String serverhost) throws Exception {
        if(bannedSites.contains(serverhost.toLowerCase())){
            throw new Exception("Access Denied");
        }
        realInternet.connectTo(serverhost);
    }
}

public class ProxyDemo {
    public static void main (String[] args){
        Internet internet = new ProxyInternet();
        try
        {
            internet.connectTo("geeksforgeeks.org");
            internet.connectTo("abc.com");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
