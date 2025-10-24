package com.pluralsight;

public class UserInterface {
    private Dealership dealership;

    public UserInterface(Dealership dealership) {
        this.dealership = dealership;
    }

    public void display() {
        System.out.println("Welcome to " + dealership.getName() + "!");
        System.out.println("Address: " + dealership.getAddress());
        System.out.println("Phone: " + dealership.getPhone());
        System.out.println("==================================");
        System.out.println("Inventory:");

        for (Vehicle v : dealership.getAllVehicles()) {
            System.out.println(v.getYear() + " " + v.getMake() + " " + v.getModel() + " - $" + v.getPrice());
        }
    }
}

