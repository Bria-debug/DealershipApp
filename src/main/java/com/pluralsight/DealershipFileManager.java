package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {
    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"))) {
            // First line = dealership info
            String dealershipInfo = reader.readLine();
            String[] parts = dealershipInfo.split("\\|");

            String name = parts[0];
            String address = parts[1];
            String phone = parts[2];

           dealership = new Dealership(name, address, phone);

            // Remaining lines = vehicles
            String line;
            while ((line = reader.readLine()) != null) {
                String[] v = line.split("\\|");
                int vin = Integer.parseInt(v[0]);
                int year = Integer.parseInt(v[1]);
                String make = v[2];
                String model = v[3];
                String type = v[4];
                String color = v[5];
                int mileage = Integer.parseInt(v[6]);
                double price = Double.parseDouble(v[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                dealership.addVehicle(vehicle);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dealership;
    }
}

