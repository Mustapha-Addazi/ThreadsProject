package org.example.stationapp;

public class Truck {
    private final FuelStation fuelStation;

    public Truck(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
    }

    public void refuel() throws InterruptedException {
        Thread.sleep(3000);
        fuelStation.refillFuel();
    }
}
