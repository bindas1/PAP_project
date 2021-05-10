package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class ProductsController {
    @FXML AnchorPane addProductWindow;
    @FXML AnchorPane productsListWindow;
    @FXML Pagination productsPagination;

    @FXML TextField product_name;
    @FXML TextField price;
    @FXML TextField ean;

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
    @FXML protected void addProduct(ActionEvent event) throws IOException, SQLException {
        String filledProductName = product_name.getText();

        if (!filledProductName.trim().isEmpty() && !price.getText().isEmpty() && price.getText().matches("[-+]?[0-9]*\\.?[0-9]+")) {
            float filledPrice = Float.parseFloat(price.getText());
            int filledEan;

            if (ean.getText().isEmpty()) {
                 filledEan = 0;
            }
            else {
                filledEan = Integer.parseInt(ean.getText());
            }

            Product new_product = new Product(filledProductName, filledPrice, filledEan);
            Database database = new Database();
            database.insertRecord("Products", new_product.getArguments());
            System.out.println("Row inserted into Products with following data: ");
            System.out.println(new_product.getArguments());
        }
    }
}