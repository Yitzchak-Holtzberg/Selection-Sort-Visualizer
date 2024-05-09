package com.example.selection;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.util.Arrays;

public class HelloApplication extends Application {
    public int[] arrayToSort = randomArray();

    @Override
    public void start(Stage stage) {
        Button resetButton = new Button("Reset");
        Button nextStepButton = new Button("Next Step");

        HBox hbox = new HBox(resetButton, nextStepButton);
        hbox.setAlignment(Pos.CENTER);


        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> populations = new XYChart.Series<>();

        resetButton.setOnAction(e -> resetChart(barChart, populations));

        //nextStepButton.setOnAction(e -> nextStep());  // Assuming nextStep is properly defined elsewhere

        xAxis.setTickLabelsVisible(false);
        xAxis.setTickMarkVisible(false);
        yAxis.setTickLabelsVisible(false);
        yAxis.setTickMarkVisible(false);

        barChart.setTitle("Selection Sort");
        barChart.setLegendVisible(false);

        VBox vbox = new VBox(barChart, hbox);
        Scene view = new Scene(vbox, 800, 400);

        stage.setScene(view);
        stage.show();

        resetChart(barChart, populations);  // Initial chart setup
    }

    private void resetChart(BarChart<String, Number> barChart, XYChart.Series<String, Number> populations) {
        arrayToSort = randomArray();
        populations.getData().clear();  // Clear previous data
        createChartFromCurrentData(arrayToSort, populations);
        if (!barChart.getData().contains(populations)) {
            barChart.getData().add(populations);
        }
    }

    private static void createChartFromCurrentData(int[] arrayToSort, XYChart.Series<String, Number> populations) {
        for (int i = 19; i > -1; i--) {
            int k = arrayToSort[i];
            XYChart.Data<String, Number> data = new XYChart.Data<>(Integer.toString(i), k);
            data.setNode(new StackPane(new Label(Integer.toString(k))));
            populations.getData().add(data);
        }
    }

    public static int[] randomArray() {
        int[] numbers = new int[20];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        for (int i = numbers.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        return numbers;
    }
}
