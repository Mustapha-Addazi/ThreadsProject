package org.example.stationapp;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.Random;

public class FuelStation {
    private final ProgressBar fuelBar;
    private final Text statusText;
    private final Pane carPane;
    private double fuelLevel = 1.0;
    private boolean stationRunning = true;

    public FuelStation(ProgressBar fuelBar, Text statusText, Pane carPane) {
        this.fuelBar = fuelBar;
        this.statusText = statusText;
        this.carPane = carPane;
    }

    public void startStation() {
        new Thread(this::operateStation).start();
    }

    public void stopStation() {
        stationRunning = false;
    }

    private void operateStation() {
        while (stationRunning) {
            try {
                Car car = new Car(this, carPane);
                car.animateArrival();

                if (fuelLevel <= 0.2) {
                    Truck truck = new Truck(this);
                    truck.refuel();
                }

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void consumeFuel(double amount) {
        fuelLevel -= amount;
        updateUI();
    }

    public synchronized void refillFuel() {
        fuelLevel = 1.0;
        updateUI();
    }

    private void updateUI() {
        Platform.runLater(() -> {
            fuelBar.setProgress(fuelLevel);
            if (fuelLevel <= 0.2) {
                fuelBar.setStyle("-fx-accent: red;");
                statusText.setText("Niveau bas - Camion en route...");
            } else {
                fuelBar.setStyle("-fx-accent: green;");
                statusText.setText("Station prête");
            }
        });
    }

    public double getFuelLevel() {
        return fuelLevel;
    }
}
