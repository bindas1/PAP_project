package org.example;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.*;

public class OrdersController {
    @FXML AnchorPane addOrderWindow;
    @FXML AnchorPane ordersListWindow;
    @FXML Pagination ordersPagination;

    @FXML TextField order_id;
    @FXML TextField products_ids;
    @FXML TextField email_client;
    @FXML TextField quantities;
    @FXML DatePicker order_date;
    @FXML ChoiceBox shipping_status;

    @FXML private void signOut() throws IOException { App.setRoot("primary"); }
    @FXML private void goToMainPage() throws IOException { App.setRoot("MainPage"); }
    @FXML private void goToClients() throws IOException { App.setRoot("clients"); }
    @FXML private void goToProducts() throws IOException { App.setRoot("products"); }

    @FXML protected void addOrderWindow(ActionEvent event) throws IOException{
        addOrderWindow.setVisible(true);
        ordersListWindow.setVisible(false);
    }
    @FXML protected void closeWindow(ActionEvent event) throws IOException{
        addOrderWindow.setVisible(false);
        ordersListWindow.setVisible(true);
    }

    @FXML protected void addOrder(ActionEvent event) throws IOException, SQLException {
        String filledProducts = products_ids.getText();
        String filledEmail = email_client.getText();
        String filledOrderId = order_id.getText();
        String filledQuantities = quantities.getText();

        if (!filledProducts.isEmpty() && !filledEmail.isEmpty() && !filledOrderId.isEmpty() && !filledQuantities.isEmpty()) {
            int order_id = Integer.parseInt(filledOrderId.trim());
            java.sql.Date date = java.sql.Date.valueOf(order_date.valueProperty().get());
            Object status = shipping_status.getSelectionModel().getSelectedItem();
            int shipping = 0;

            if (status.equals("Shipped (True)")){
                shipping = 1;
            }

            List<String> products = new ArrayList<>(Arrays.asList(filledProducts.split("[,]")));
            List<String> quantities_ = new ArrayList<>(Arrays.asList(filledQuantities.split("[,]")));
            for(int i=0; i< products.size(); i++) {
                int product_id = Integer.parseInt(products.get(i).trim());
                int quantity = Integer.parseInt(quantities_.get(i).trim());

                Order new_order = new Order(order_id, product_id, filledEmail, quantity, date, shipping);
                Database database = new Database();
                database.insertRecord("Orders", new_order.getArguments());
                System.out.println("Row inserted into Orders with following data: ");
                System.out.println(new_order.getArguments());
            }
        }
    }
}