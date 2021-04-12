package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ProductsController {
    @FXML AnchorPane addProductWindow;
    @FXML AnchorPane productsListWindow;
    @FXML Pagination productsPagination;

    @FXML private void signOut() throws IOException { App.setRoot("primary"); }
    @FXML private void goToOrders() throws IOException { App.setRoot("orders"); }
    @FXML private void goToClients() throws IOException { App.setRoot("clients"); }
    @FXML private void goToMainPage() throws IOException { App.setRoot("MainPage"); }

    @FXML protected void addProductWindow(ActionEvent event) throws IOException{
        addProductWindow.setVisible(true);
        productsListWindow.setVisible(false);
    }
    @FXML protected void closeWindow(ActionEvent event) throws IOException{
        addProductWindow.setVisible(false);
        productsListWindow.setVisible(true);
    }
    @FXML protected void addProduct(ActionEvent event) throws IOException{
        System.out.println("produkt dodany");
    }
}