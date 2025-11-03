package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    private static final String FILE_NAME = "contracts.csv";

    public static void saveContract(Contract contract) {

        Vehicle v = contract.getVehicle();
        String type = contract.getClass().getSimpleName(); // Returns "SaleContract" or "LeaseContract"

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {

            if (type.equals("SaleContract")) {
                SaleContract sc = (SaleContract) contract; // We *already* know it's a SaleContract

                writer.write(
                        "SALE|" +
                                contract.getDate() + "|" +
                                contract.getCustomerName() + "|" +
                                contract.getCustomerEmail() + "|" +
                                v.getVin() + "|" +
                                v.getYear() + "|" +
                                v.getMake() + "|" +
                                v.getModel() + "|" +
                                v.getType() + "|" +
                                v.getColor() + "|" +
                                v.getOdometer() + "|" +
                                v.getPrice() + "|" +
                                sc.getSalesTaxAmount() + "|" +
                                sc.getRecordingFee() + "|" +
                                sc.getProcessingFee() + "|" +
                                contract.getTotalPrice() + "|" +
                                (sc.isFinance() ? "YES" : "NO") + "|" +
                                contract.getMonthlyPayment() + "\n"
                );
            }
            else { // Must be LeaseContract
                LeaseContract lc = (LeaseContract) contract;

                writer.write(
                        "LEASE|" +
                                contract.getDate() + "|" +
                                contract.getCustomerName() + "|" +
                                contract.getCustomerEmail() + "|" +
                                v.getVin() + "|" +
                                v.getYear() + "|" +
                                v.getMake() + "|" +
                                v.getModel() + "|" +
                                v.getType() + "|" +
                                v.getColor() + "|" +
                                v.getOdometer() + "|" +
                                v.getPrice() + "|" +
                                lc.getExpectedEndingValue() + "|" +
                                lc.getLeaseFee() + "|" +
                                contract.getTotalPrice() + "|" +
                                contract.getMonthlyPayment() + "\n"
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving contract.");
        }
    }
}

