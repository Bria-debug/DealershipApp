package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    // The dealership we are interacting with
    private Dealership dealership;

    // Scanner is used to read user keyboard input
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {}

    // Loads dealership data from file when the program starts
    private void init() {
        dealership = DealershipFileManager.getDealership();
    }
public void processSellOrLeaseRequest(){
    System.out.println("Enter the VIN of vehicle:");
    int vin = scanner.nextInt();
    scanner.nextLine();
    Vehicle vehicle = dealership.getVehicleByVin(vin);

    if(vehicle == null){
        System.out.println("No vehicle found with that VIN");
        return;
    }
    System.out.println("Customer Name:");
    String name = scanner.nextLine();

    System.out.println("Customer Email:");
    String email = scanner.nextLine();

    System.out.println("Is this a Sale or Lease? (S/L):");
    String choice = scanner.nextLine().trim().toUpperCase();
    }
    Contract contract;
    if(choice.equals("L"))

    {
        if (vehicle.getYear() < 2022) {
            System.out.println("This vehicle is too old to lease.");
            return;
        }
        contract = new LeaseContract("today", name, email, vehicle);
    }else {
        contract = new SaleContract("today", name, email, vehicle);

    }

    // Shows the menu and keeps the program running until user quits
    public void display() {
        init();

        do {
            homeMenuScreen();

            System.out.print("Type in a number: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // clears newline so next input reads correctly

            // Switch statement chooses which feature to run based on user choice
            switch (option) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 10 -> processSellOrLeaseRequest();
                case 99 -> System.exit(0); // ends the program
                default -> System.out.println("Invalid option, please try again.");
            }
        } while (true);
    }

    // Prints the menu options to the user
    private void homeMenuScreen() {
        System.out.println("""
                1 - Find vehicles within a price range
                2 - Find vehicles by make / model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicles by type (car, truck, SUV, van)
                7 - List ALL vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                10- Sell/ Lease a vehicle
                99 - Quit
                """);
    }

    // Loops through and prints each vehicle in a list
    private void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
        System.out.println();
    }

    // Each of the methods below handles one menu option...

    public void processGetByPriceRequest() {
        System.out.print("Please enter the max price: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the min price: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Please enter the make: ");
        String make = scanner.nextLine();

        System.out.print("Please enter the model: ");
        String model = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest() {
        System.out.print("Please enter the max year: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the min year: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    public void processGetByColorRequest() {
        System.out.print("Please enter the color: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
        System.out.print("Please enter the max mileage: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the min mileage: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Please enter the type: ");
        String type = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByType(type));
    }

    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {
        System.out.print("Please enter the vin: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the odometer: ");
        int odometer = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the make: ");
        String make = scanner.nextLine();

        System.out.print("Please enter the model: ");
        String model = scanner.nextLine();

        System.out.print("Please enter the type: ");
        String type = scanner.nextLine();

        System.out.print("Please enter the color: ");
        String color = scanner.nextLine();

        System.out.print("Please enter the price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        // Create vehicle object and add it to dealership
        dealership.addVehicle(new Vehicle(vin, year, odometer, make, model, type, color, price));
        DealershipFileManager.saveDealership(dealership); // Save back to file
    }

    public void processRemoveVehicleRequest() {
        System.out.print("Please enter the vin of the car you want to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        dealership.removeVehicle(dealership.getVehicleByVin(vin));
        DealershipFileManager.saveDealership(dealership);
    }
}