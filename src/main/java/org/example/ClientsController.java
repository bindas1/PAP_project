package org.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ClientsController {
    @FXML AnchorPane addClientWindow;
    @FXML AnchorPane clientsListWindow;
    @FXML Pagination clientsPagination;

    @FXML TextField email;
    @FXML TextField first_name;
    @FXML TextField last_name;
    @FXML TextField zip_code;
    @FXML TextField city;
    @FXML TextField street_address;

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

    @FXML protected void addClient(ActionEvent event) throws IOException, SQLException {
        String filledEmail = email.getText();
        String filledFirstName = first_name.getText();
        String filledLastName = last_name.getText();
        String filledZipCode = zip_code.getText();
        String filledCity = city.getText();
        String filledStreetAddress = street_address.getText();

//        if (filledEmail == null || filledEmail.trim().isEmpty()){
//            email.setStyle("-fx-text-inner-color: red;");
//            email.setText("Email must be filled-in");
//            email.setStyle("-fx-text-inner-color: black;");
//        } else {
//            System.out.println(arguments);
//        }

        if (!filledEmail.trim().isEmpty() && !filledFirstName.trim().isEmpty() && !filledLastName.trim().isEmpty() && !filledZipCode.trim().isEmpty()) {
            Client new_client = new Client(filledEmail, filledFirstName, filledLastName, filledZipCode, filledCity, filledStreetAddress);
            Database database = new Database();
            database.insertRecord("Clients", new_client.getArguments());
            System.out.println("Row inserted into Clients with following data: ");
            System.out.println(new_client.getArguments());
        }
    }

}