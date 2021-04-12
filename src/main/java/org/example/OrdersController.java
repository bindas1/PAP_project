package org.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;

public class OrdersController {
    @FXML AnchorPane addOrderWindow;
    @FXML AnchorPane ordersListWindow;
    @FXML Pagination ordersPagination;

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

    @FXML protected void addOrder(ActionEvent event) throws IOException{
        System.out.println("zamówienie dodane");
    }
}