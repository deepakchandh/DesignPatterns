package com.java.designpatterns.structural.proxy;


// Proxy Pattern Example

interface Service {
    void request();
}

// The real object that performs the actual operation
class RealService implements Service {
    public void request() {
        System.out.println("Accessing sensitive resource...");
    }
}

// The proxy that controls access to the real service
class SecureProxy implements Service {
    private final RealService realService = new RealService();
    private final String userRole;

    public SecureProxy(String userRole) {
        this.userRole = userRole;
    }

    public void request() {
        if ("ADMIN".equalsIgnoreCase(userRole)) {
            realService.request();
        } else {
            System.out.println("Access denied. Admins only.");
        }
    }
}
public class ProxyExample2 {

    public static void main(String[] args) {
        Service adminService = new SecureProxy("ADMIN");
        adminService.request();  // ✅ Accessing sensitive resource...

        Service userService = new SecureProxy("USER");
        userService.request();   // ❌ Access denied. Admins only.
    }
}
