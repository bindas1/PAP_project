package org.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;

public class ClientsController {
    @FXML AnchorPane addClientWindow;
    @FXML AnchorPane clientsListWindow;
    @FXML Pagination clientsPagination;

    @FXML private void signOut() throws IOException { App.setRoot("primary"); }
    @FXML private void goToOrders() throws IOException { App.setRoot("orders"); }
    @FXML private void goToMainPage() throws IOException { App.setRoot("MainPage"); }
    @FXML private void goToProducts() throws IOException { App.setRoot("products"); }

    @FXML protected void addClientWindow(ActionEvent event) throws IOException{
        addClientWindow.setVisible(true);
        clientsListWindow.setVisible(false);
    }
    @FXML protected void closeWindow(ActionEvent event) throws IOException{
        addClientWindow.setVisible(false);
        clientsListWindow.setVisible(true);
    }

    @FXML protected void addClient(ActionEvent event) throws IOException{
        System.out.println("klient dodany");
    }

}