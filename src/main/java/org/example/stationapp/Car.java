package org.example.stationapp;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Car {
    private final FuelStation fuelStation;
    private final Pane carPane;
    private final Random random = new Random();

    public Car(FuelStation fuelStation, Pane carPane) {
        this.fuelStation = fuelStation;
        this.carPane = carPane;
    }

    public void animateArrival() throws InterruptedException {
        Platform.runLater(() -> {
            Rectangle carShape = new Rectangle(50, 30, Color.BLUE);
            carPane.getChildren().add(carShape);

            TranslateTransition transition = new TranslateTransition(Duration.seconds(3), carShape);
            transition.setFromX(-100);
            transition.setToX(200);

            transition.setOnFinished(event -> {
                try {
                    refuel();
                    animateDeparture(carShape);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            transition.play();
        });
    }

    private void animateDeparture(Rectangle carShape) {
        TranslateTransition departure = new TranslateTransition(Duration.seconds(3), carShape);
        departure.setFromX(200);
        departure.setToX(600);
        departure.setOnFinished(event -> carPane.getChildren().remove(carShape));
        departure.play();
    }

    public void refuel() throws InterruptedException {
        double consumption = random.nextDouble() * 0.3;
        fuelStation.consumeFuel(consumption);
        Thread.sleep(1000 + random.nextInt(1000));
    }
}

