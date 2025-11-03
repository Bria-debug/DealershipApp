package com.pluralsight;

public class LeaseContract extends Contract {

    private final double expectedEndingValue;
    private final double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);

        this.expectedEndingValue = vehicle.getPrice() * 0.50;
        this.leaseFee = vehicle.getPrice() * 0.07;
    }

    @Override
    public double getTotalPrice() {
        return (getVehicle().getPrice() - expectedEndingValue) + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double total = getTotalPrice();
        double rate = 0.04 / 12; // 4% annual
        int months = 36;

        return (total * rate) / (1 - Math.pow(1 + rate, -months));
    }

    public double getExpectedEndingValue() { return expectedEndingValue; }
    public double getLeaseFee() { return leaseFee; }
}

