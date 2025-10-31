package com.pluralsight;

import java.io.*;

public class DealershipFileManager {

    public static final String FILE_NAME = "src/main/resources/inventory.csv";

    /**
     * Example data, header and vehicle row
     * Super Dupers Car Dealership|777 elm dr.|817-777-5555
     * 10112|1993|Ford|Explorer|SUV|Red|525123|995.00
     *
     * @return new Dealership
     */
    public static Dealership getDealership() {
        Dealership dealership = null;
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(FILE_NAME));
            String input;

            while((input = bufReader.readLine()) != null) {
                String[] tokens = input.split("\\|"); //Input from CSV, the data, split by |

                if (tokens.length > 3) { //Vehicle
                    dealership.addVehicle(new Vehicle(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[6]), tokens[2], tokens[3], tokens[4], tokens[5], Double.parseDouble(tokens[7])));
                } else { //Dealership Name and Number
                    dealership = new Dealership(tokens[0], tokens[1], tokens[2]);
                }
            }

            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace(); //Error chain
        }
        return dealership;
    }

    public static void saveDealership(Dealership dealership) {
        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(FILE_NAME, false));
            bufWriter.write(String.format("%s|%s|s", dealership.getName(), dealership.getAddress(), dealership.getPhone()));
            bufWriter.newLine();

            for (Vehicle v: dealership.getAllVehicles()) {
                bufWriter.write(String.format("%d|%d|%s|%s|%s|%s|%d|%.2f", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice()));
                bufWriter.newLine();
            }

            bufWriter.close(); //Saves file
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}