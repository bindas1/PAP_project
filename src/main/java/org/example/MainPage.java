package org.example;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainPage {
    @FXML BorderPane statistics1;
    @FXML AnchorPane statistics2;
    @FXML AnchorPane statistics3;

    public void initialize() throws SQLException {
        Database database = new Database();
        ResultSet rs = database.selectJoinRecords();

        Hashtable<java.sql.Date, Float> orders_values = new Hashtable<java.sql.Date, Float>();

        while(rs.next()) {
            java.sql.Date date = rs.getDate("order_date");
            Float value = rs.getFloat("Value_of_order");
            if(orders_values.containsKey(date)){
                orders_values.put(date, orders_values.get(date) + value);
            }
            else {
                orders_values.put(date, value);
            }
        }

        // use TreeMap so that our data is sorted
        TreeMap<java.sql.Date, Float> tmap = new TreeMap<java.sql.Date, Float>(orders_values);
        System.out.println(tmap);

        XYChart.Series statistics_series = new XYChart.Series();
        statistics_series.setName("Total value of orders in particular day");

        for (Map.Entry<java.sql.Date, Float> entry : tmap.entrySet()) {
            java.sql.Date date = entry.getKey();
            Float value = entry.getValue();
            XYChart.Data<String, Float> d = new XYChart.Data<>(date.toString(), value);
            statistics_series.getData().add(d);
        }

        // create bar chart using selected data
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis,yAxis);
        bc.setTitle("Profit of the shop in 2021");
        xAxis.setLabel("Date");
        yAxis.setLabel("Value of profit");

        bc.getData().addAll(statistics_series);
        statistics1.setCenter(bc);

    }

    @FXML private void signOut() throws IOException { App.setRoot("primary"); }

    @FXML private void goToOrders() throws IOException { App.setRoot("orders"); }

    @FXML private void goToClients() throws IOException { App.setRoot("clients"); }

    @FXML private void goToProducts() throws IOException { App.setRoot("products"); }

    @FXML protected void setStatistics1(ActionEvent event) throws IOException{
        statistics1.setVisible(true);
        statistics2.setVisible(false);
        statistics3.setVisible(false);
    }
    @FXML protected void setStatistics2(ActionEvent event) throws IOException{
        statistics1.setVisible(false);
        statistics2.setVisible(true);
        statistics3.setVisible(false);
    }
    @FXML protected void setStatistics3(ActionEvent event) throws IOException{
        statistics1.setVisible(false);
        statistics2.setVisible(false);
        statistics3.setVisible(true);
    }
}