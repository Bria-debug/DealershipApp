package com.pluralsight;

public class SaleContract extends Contract {

    private final double salesTaxAmount;
    private final double recordingFee = 100;
    private final double processingFee;
    private final boolean finance;

    public SaleContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        this.finance = finance;

        this.salesTaxAmount = vehicle.getPrice() * 0.05;
        this.processingFee = (vehicle.getPrice() < 10000) ? 295 : 495;
    }

    @Override
    public double getTotalPrice() {
        return getVehicle().getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0;

        double price = getTotalPrice();
        double rate;
        int months;

        if (getVehicle().getPrice() >= 10000) {
            rate = 0.0425 / 12;
            months = 48;
        } else {
            rate = 0.0525 / 12;
            months = 24;
        }

        return (price * rate) / (1 - Math.pow(1 + rate, -months));
    }

    public double getSalesTaxAmount() { return salesTaxAmount; }
    public double getRecordingFee() { return recordingFee; }
    public double getProcessingFee() { return processingFee; }
    public boolean isFinance() { return finance; }
}
