package com.example.selection;

import javafx.application.Application;
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
    @Override
    public void start(Stage stage) {
        int [] arrayToSort = randomArray();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setTickLabelsVisible(false);
        xAxis.setTickMarkVisible(false);


        NumberAxis yAxis = new NumberAxis();
        yAxis.setTickLabelsVisible(false);
        yAxis.setTickMarkVisible(false);

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        barChart.setTitle("Selection Sort");
        barChart.setLegendVisible(false);

        XYChart.Series<String, Number> populations = new XYChart.Series<>();

        createChartFromCurrentData(arrayToSort, populations);

        barChart.getData().add(populations);
        Scene view = new Scene(barChart, 640, 480);
        stage.setFullScreen(true);
        stage.setScene(view);
        stage.show();
    }

    private static void createChartFromCurrentData(int[] arrayToSort, XYChart.Series<String, Number> populations) {
        for(int i =19; i>-1;i--){
            int k = arrayToSort[i];
            XYChart.Data<String, Number> data = new XYChart.Data<>(Integer.toString(i), k);
            data.setNode(new StackPane(new Label(Integer.toString(k))));
            populations.getData().add(data);
        }
    }

    public static int[] randomArray() {
        // Generate the numbers 1 through 20 in a random order
        int[] numbers = new int[20]; // Array to store numbers 1-20
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1; // Fill the array with numbers 1 to 20
        }

        // Shuffle the array using Fisher-Yates shuffle algorithm
        for (int i = numbers.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1)); // Random index from 0 to i
            // Swap numbers[i] with the element at random index
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        System.out.println(Arrays.toString(numbers));
        return numbers;
    }
}