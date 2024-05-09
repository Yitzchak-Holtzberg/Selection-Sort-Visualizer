package com.example.selection;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class AnimatedSelectionSort extends Application {
    public int[] arrayToSort = randomArray();
    public static int currentIndex = 0;
    public boolean isSorted = false;

    @Override
    public void start(Stage stage) {
        // Create the Reset and Next Step buttons
        Button resetButton = new Button("Reset");
        Button nextStepButton = new Button("Next Step");

        // Create a horizontal box to hold the buttons and center align them
        HBox hbox = new HBox(resetButton, nextStepButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);

        // Create the axes for the bar chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Create the bar chart and the series to hold the data
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        XYChart.Series<String, Number> populations = new XYChart.Series<>();

        // Set the action for the Reset button to reset the chart
        resetButton.setOnAction(e -> resetChart(barChart, populations));

        // Set the action for the Next Step button to perform the next step in the sorting algorithm
        nextStepButton.setOnAction(e -> nextStep(barChart, populations));


        // Hide the tick labels and tick marks on the axes
        xAxis.setTickLabelsVisible(false);
        xAxis.setTickMarkVisible(false);
        yAxis.setTickLabelsVisible(false);
        yAxis.setTickMarkVisible(false);
        xAxis.setOpacity(0);
        yAxis.setOpacity(0);
        barChart.setHorizontalGridLinesVisible(false);
        barChart.setVerticalGridLinesVisible(false);

        // Set the title of the bar chart and hide the legend
        barChart.setTitle("Selection Sort");
        barChart.setLegendVisible(false);

        // Create a vertical box to hold the bar chart and the buttons
        VBox vbox = new VBox(barChart, hbox);

        // Create the scene and set it on the stage
        Scene view = new Scene(vbox, 800, 400);
        stage.setScene(view);
        stage.show();

        // Perform the initial setup of the chart
        resetChart(barChart, populations);
    }

    /**
     * Resets the chart to its initial state.
     * <p>
     * This method generates a new random array to sort, clears the previous data from the chart,
     * creates a new chart from the current data, and adds the new data to the chart if it's not already present.
     *
     * @param barChart    The BarChart object that displays the sorting process.
     * @param populations The Series object that holds the data for the BarChart.
     */
    private void resetChart(BarChart<String, Number> barChart, XYChart.Series<String, Number> populations) {
        // Generate a new random array to sort
        isSorted = false;
        arrayToSort = randomArray();
        currentIndex = 0;


        // Clear the previous data from the chart
        populations.getData().clear();

        // Create a new chart from the current data
        createChartFromCurrentData(arrayToSort, populations);

        // Add the new data to the chart if it's not already present
        if (!barChart.getData().contains(populations)) {
            barChart.getData().add(populations);
        }
    }

    /**
     * Creates a chart from the current data in the array to sort.
     * <p>
     * This method iterates over the array in reverse order, creating a new Data object for each element.
     * The index of the element is used as the X value (converted to a string), and the value of the element is used as the Y value.
     * A new Label is created for each Data object, displaying the value of the element.
     * Each Data object is then added to the provided Series object.
     *
     * @param arrayToSort The array of integers to be sorted.
     * @param populations The Series object that holds the data for the chart.
     */
    private static void createChartFromCurrentData(int[] arrayToSort, XYChart.Series<String, Number> populations) {
        // Clear the previous data from the series
        populations.getData().clear();
        for (int i = 0; i < 20; i++) {
            int k = arrayToSort[i];
            XYChart.Data<String, Number> data = new XYChart.Data<>(Integer.toString(i), k);
            StackPane node = new StackPane(new Label(Integer.toString(k)));
            data.setNode(node);

            // Apply color and border modifications
            if (i == currentIndex) { // Assume 'currentIndex' is the index of the bar you want to color differently
                node.setStyle("-fx-background-color: #10ca92; -fx-border-color: black; -fx-border-bottom-color: transparent; -fx-border-width: 2px;");
            } else {
                node.setStyle("-fx-background-color: gray; -fx-border-color: black; -fx-border-bottom-color: transparent; -fx-border-width: 2px;");
            }
            populations.getData().add(data);
        }

        // Add a black border around the entire chart
        Node chart = populations.getNode();
        if (chart != null) {
            ((Node) chart).setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        }
    }


    /**
     * Generates an array of integers from 1 to 20 in a random order.
     * <p>
     * The method first creates an array of 20 integers in ascending order.
     * Then it shuffles the array using the Fisher-Yates shuffle algorithm.
     *
     * @return An array of integers from 1 to 20 in a random order.
     */
    public static int[] randomArray() {
        // Initialize an array of 20 integers in ascending order
        int[] numbers = new int[20];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }

        // Shuffle the array using the Fisher-Yates shuffle algorithm
        for (int i = numbers.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        // Return the shuffled array
        return numbers;
    }

    /**
     * Performs the next step in the selection sort algorithm and updates the chart accordingly.
     *
     * @param barChart    The BarChart object that displays the sorting process.
     * @param populations The Series object that holds the data for the BarChart.
     */
    private void nextStep(BarChart<String, Number> barChart, XYChart.Series<String, Number> populations) {
        // If the array is already sorted, no further action is needed
        if (isSorted) {
            // Show the alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sorting Complete! \n press reset to continue.", ButtonType.OK);
            alert.setOnCloseRequest(event -> {
                System.out.println("Alert closed");
            });
            alert.showAndWait();
            return;
        }

        // Find the index of the smallest element in the unsorted part of the array
        int minIndex = currentIndex;
        for (int j = currentIndex + 1; j < arrayToSort.length; j++) {
            if (arrayToSort[j] < arrayToSort[minIndex]) {
                minIndex = j;
            }
        }

        // Swap the found minimum element with the first element of the unsorted part
        int temp = arrayToSort[currentIndex];
        arrayToSort[currentIndex] = arrayToSort[minIndex];
        arrayToSort[minIndex] = temp;

        // Update the chart to reflect the current state of the array
        createChartFromCurrentData(arrayToSort, populations);

        // Move to the next index
        currentIndex++;

        // If we've reached the end of the array, it means the array is completely sorted
        if (currentIndex == arrayToSort.length - 1) {
            isSorted = true;
        }
    }
}
