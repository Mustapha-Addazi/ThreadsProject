package org.example.stationapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GasStationApp extends Application {
    private ProgressBar fuelBar;
    private Text statusText;
    private FuelStation fuelStation;
    private StackPane carPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        fuelBar = new ProgressBar(1.0);
        fuelBar.setStyle("-fx-accent: green;");
        statusText = new Text("Station prête");

        carPane = new StackPane();
        VBox layout = new VBox(10, statusText, fuelBar, carPane);
        Scene scene = new Scene(layout, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Station de Gaz");
        primaryStage.show();

        fuelStation = new FuelStation(fuelBar, statusText, carPane);
        fuelStation.startStation();
    }

    @Override
    public void stop() {
        fuelStation.stopStation();
    }
}
