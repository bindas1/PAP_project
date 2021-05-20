package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class ProductsController {
    @FXML BorderPane productsTable;
    @FXML AnchorPane addProductWindow;
    @FXML AnchorPane productsListWindow;
    @FXML AnchorPane deleteProductsWindow;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;

    @FXML TextField idDelete;
    @FXML TextField product_name;
    @FXML TextField price;
    @FXML TextField ean;

    public void initialize(){
        DynamicTable table = new DynamicTable();
        productsTable.setCenter(table.buildData("Products"));
    }

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
        deleteProductsWindow.setVisible(false);
        productsListWindow.setVisible(true);
    }
    @FXML protected void deleteProductWindow(ActionEvent event) throws IOException{
        deleteProductsWindow.setVisible(true);
        productsListWindow.setVisible(false);
    }
    @FXML protected void deleteProduct(ActionEvent event) throws IOException, SQLException {
        String filledDeletedId = idDelete.getText();
        Window owner = deleteButton.getScene().getWindow();

        if (filledDeletedId.trim().isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter product id");
            return;
        }
        Database database = new Database();
        database.deleteRecord("Products", filledDeletedId);
        Helpers.showAlert(Alert.AlertType.CONFIRMATION, owner, "Request confirmation",
                "You deleted "+filledDeletedId);
    }
    @FXML protected void addProduct(ActionEvent event) throws IOException, SQLException {
        String filledProductName = product_name.getText();
        Window owner = saveButton.getScene().getWindow();

        // validate data
        if (filledProductName.trim().isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter product name");
            return;
        }
        if (price.getText().isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter product's price");
            return;
        }
        if (!price.getText().matches("[-+]?[0-9]*\\.?[0-9]+")) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Price isn't a valid number");
            return;
        }

        float filledPrice = Float.parseFloat(price.getText());
        long filledEan;

        if (ean.getText().isEmpty()) {
             filledEan = 0;
        }
        else {
            filledEan = Long.parseLong(ean.getText());
        }

        Product new_product = new Product(filledProductName, filledPrice, filledEan);
        Database database = new Database();
        database.insertRecord("Products", new_product.getArguments());

        // confirmation of insert
        Helpers.showAlert(Alert.AlertType.CONFIRMATION, owner, "Request confirmation",
                "Row successfully inserted into Products");
        System.out.println("Row inserted into Products with following data: ");
        System.out.println(new_product.getArguments());
    }

    @FXML protected void Import(ActionEvent event) throws IOException, SQLException {
        File selectedFile = Helpers.chooseDocumentFile();

        if (selectedFile != null) {
            BufferedReader lineReader = new BufferedReader(new FileReader(selectedFile));
            String lineText = null;

            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String productName = data[0];
                float price = Float.parseFloat(data[1]);
                long ean;

                try {
                    if (data[2].isEmpty()) {
                        ean = 0;
                    }
                    else {
                        ean = Long.parseLong(data[2]);
                    }
                }
                catch(ArrayIndexOutOfBoundsException exception) {
                    ean = 0;
                }

                Product new_product = new Product(productName, price, ean);
                Database database = new Database();
                database.insertRecord("Products", new_product.getArguments());
                System.out.println("Row inserted into Products with following data: ");
                System.out.println(new_product.getArguments());
            }
        }
    }

    @FXML protected void Export(ActionEvent event) throws IOException {
        System.out.println("Export");
    }
}