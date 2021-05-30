package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

import java.util.*;

public class OrdersController {
    @FXML BorderPane ordersTable;
    @FXML AnchorPane addOrderWindow;
    @FXML AnchorPane deleteOrderWindow;
    @FXML AnchorPane changeOrderStatusWindow;
    @FXML AnchorPane ordersListWindow;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;
    @FXML private Button changeStatusButton;

    @FXML TextField idChange;
    @FXML TextField idDelete;
    @FXML TextField order_id;
    @FXML TextField products_ids;
    @FXML TextField email_client;
    @FXML TextField quantities;
    @FXML DatePicker order_date;
    @FXML ChoiceBox shipping_status;

    public void initialize(){
        DynamicTable table = new DynamicTable();
       ordersTable.setCenter(table.buildData("Orders"));
    }

    @FXML private void signOut() throws IOException { App.setRoot("primary"); }
    @FXML private void goToMainPage() throws IOException { App.setRoot("MainPage"); }
    @FXML private void goToClients() throws Exception { App.setRoot("clients");}
    @FXML private void goToProducts() throws IOException { App.setRoot("products"); }

    @FXML protected void addOrderWindow(ActionEvent event) throws IOException{
        addOrderWindow.setVisible(true);
        ordersListWindow.setVisible(false);
    }
    @FXML protected void closeWindow(ActionEvent event) throws IOException{
        addOrderWindow.setVisible(false);
        deleteOrderWindow.setVisible(false);
        changeOrderStatusWindow.setVisible(false);
        ordersListWindow.setVisible(true);
    }
    @FXML protected void deleteOrderWindow(ActionEvent event) throws IOException{
        deleteOrderWindow.setVisible(true);
        ordersListWindow.setVisible(false);
    }
    @FXML protected void changeOrderStatusWindow(ActionEvent event) throws IOException{
        changeOrderStatusWindow.setVisible(true);
        ordersListWindow.setVisible(false);
    }
    @FXML protected void changeStatus(ActionEvent event) throws IOException, SQLException {
        String filledChangeId = idChange.getText();
        Window owner = changeStatusButton.getScene().getWindow();
        if (filledChangeId.trim().isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter order id");
            return;
        }
        Database database = new Database();
        //database.changeStatus("Orders", filledChangeId);
        Helpers.showAlert(Alert.AlertType.CONFIRMATION, owner, "Request confirmation",
                "/This function will be added soon/ You changed order "+filledChangeId);


    }
    @FXML protected void deleteOrder(ActionEvent event) throws IOException, SQLException {
        String filledDeletedId = idDelete.getText();
        Window owner = deleteButton.getScene().getWindow();

        if (filledDeletedId.trim().isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter order id");
            return;
        }
        Database database = new Database();
        database.deleteRecord("Orders", filledDeletedId);
        Helpers.showAlert(Alert.AlertType.CONFIRMATION, owner, "Request confirmation",
                "You deleted order "+filledDeletedId);
    }
    @FXML protected void addOrder(ActionEvent event) throws IOException, SQLException {
        String filledProducts = products_ids.getText();
        String filledEmail = email_client.getText();
        String filledOrderId = order_id.getText();
        String filledQuantities = quantities.getText();
        Window owner = saveButton.getScene().getWindow();

        if (filledOrderId.isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Order id isn't specified");
            return;
        }
        if (filledProducts.isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "No products' ids specified");
            return;
        }
        if (filledEmail.isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter client's email");
            return;
        }
        if (filledQuantities.isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter quantities of each bought products");
            return;
        }

        int order_id = Integer.parseInt(filledOrderId.trim());

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        if (order_date.valueProperty().get() != null) {
            date = java.sql.Date.valueOf(order_date.valueProperty().get());
        }

        int shipping = 0;
        try {
            Object status = shipping_status.getSelectionModel().getSelectedItem();
            if (status.equals("Shipped (True)")){
                shipping = 1;
            }
        } catch (java.lang.NullPointerException exception) {
            System.out.println("Setting default shipping status to 0 - not shipped");
        }

        List<String> products = new ArrayList<>(Arrays.asList(filledProducts.split("[,]")));
        List<String> quantities_ = new ArrayList<>(Arrays.asList(filledQuantities.split("[,]")));
        for(int i=0; i< products.size(); i++) {
            int product_id = Integer.parseInt(products.get(i).trim());
            int quantity = Integer.parseInt(quantities_.get(i).trim());

            Order new_order = new Order(order_id, product_id, filledEmail, quantity, date, shipping);
            Database database = new Database();
            database.insertRecord("Orders", new_order.getArguments());

            // confirmation of insert
            Helpers.showAlert(Alert.AlertType.CONFIRMATION, owner, "Request confirmation",
                    "Row successfully inserted into Orders");
            System.out.println("Row inserted into Orders with following data: ");
            System.out.println(new_order.getArguments());
        }
    }

    @FXML protected void Import(ActionEvent event) throws IOException, SQLException {
        File selectedFile = Helpers.chooseDocumentFile();

        if (selectedFile != null) {
            BufferedReader lineReader = new BufferedReader(new FileReader(selectedFile));
            String lineText;

            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null && !lineText.isEmpty()) {
                String[] data = lineText.split(",");

                int order_id = Integer.parseInt(data[0]);
                int product_id = Integer.parseInt(data[1]);
                String email = data[2];
                int quantity = Integer.parseInt(data[3]);
                java.sql.Date date = java.sql.Date.valueOf(data[4]);
                int shipping = Integer.parseInt(data[5]);

                Order new_order = new Order(order_id, product_id, email, quantity, date, shipping);
                Database database = new Database();
                database.insertRecord("Orders", new_order.getArguments());
                System.out.println("Row inserted into Orders with following data: ");
                System.out.println(new_order.getArguments());

            }
        }
    }

    @FXML protected void Export(ActionEvent event) throws IOException {
        File selectedDirectory = Helpers.chooseFileDestination();
        if (selectedDirectory != null){
            Database database = new Database();
            List<String> dataArray = database.ExportRecords("Orders");
            Helpers.printToCSV(dataArray, selectedDirectory);
        }
        System.out.println(selectedDirectory);
    }

    @FXML protected void refreshWindows(ActionEvent event) throws IOException{
        addOrderWindow.setVisible(false);
        ordersListWindow.setVisible(true);
    }
}