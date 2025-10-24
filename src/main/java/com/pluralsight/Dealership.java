package com.pluralsight;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    //Constructor
    public Dealership(String phone, String address, String name) {
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.inventory = new ArrayList<>();
    }
    //Getters

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }
    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }
    public ArrayList<Vehicle> getVehiclesByPrice( double min, double max){
        return null;
    }
    public ArrayList<Vehicle> getVehiclesByMakeModel (String make, String model) {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear){
        return null;
    }
    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        return null;
    }
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max){
        return null;
    }
    public ArrayList<Vehicle> getVehiclesByType (String type){
        return null;
    }
    public void removeVehicle(Vehicle vehicle){

    }
}
