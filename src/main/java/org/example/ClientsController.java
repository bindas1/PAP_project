package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClientsController {
    @FXML BorderPane clientsTable;
    @FXML AnchorPane addClientWindow;
    @FXML AnchorPane clientsListWindow;
    @FXML AnchorPane deleteClientWindow;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;

    @FXML TextField emailDelete;
    @FXML TextField email;
    @FXML TextField first_name;
    @FXML TextField last_name;
    @FXML TextField zip_code;
    @FXML TextField city;
    @FXML TextField street_address;

    public void initialize(){
        DynamicTable table = new DynamicTable();
        clientsTable.setCenter(table.buildData("Clients"));
    }

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
        deleteClientWindow.setVisible(false);
        clientsListWindow.setVisible(true);
    }
    @FXML protected void deleteClientWindow(ActionEvent event) throws IOException{
        deleteClientWindow.setVisible(true);
        clientsListWindow.setVisible(false);
    }
    @FXML protected void deleteClient(ActionEvent event) throws IOException, SQLException {
        String filledDeletedEmail = emailDelete.getText();
        Window owner = deleteButton.getScene().getWindow();

        if (filledDeletedEmail.trim().isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter client's email");
            return;
        }
        Database database = new Database();
        database.deleteRecord("Clients", filledDeletedEmail);
        Helpers.showAlert(Alert.AlertType.CONFIRMATION, owner, "Request confirmation",
                "You deleted "+filledDeletedEmail);
    }

    @FXML protected void addClient(ActionEvent event) throws IOException, SQLException {
        String filledEmail = email.getText();
        String filledFirstName = first_name.getText();
        String filledLastName = last_name.getText();
        String filledZipCode = zip_code.getText();
        String filledCity = city.getText();
        String filledStreetAddress = street_address.getText();
        Window owner = saveButton.getScene().getWindow();

        if (filledEmail.trim().isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter client's email");
            return;
        }
        if (filledFirstName.trim().isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "No first name specified");
            return;
        }
        if (filledLastName.trim().isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "No last name specified");
            return;
        }
        if (filledZipCode.trim().isEmpty()) {
            Helpers.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your zip code");
            return;
        }

        Client new_client = new Client(filledEmail, filledFirstName, filledLastName, filledZipCode, filledCity, filledStreetAddress);
        Database database = new Database();
        database.insertRecord("Clients", new_client.getArguments());

        // confirmation of insert
        Helpers.showAlert(Alert.AlertType.CONFIRMATION, owner, "Request confirmation",
                "Row successfully inserted into Clients");
        System.out.println("Row inserted into Clients with following data: ");
        System.out.println(new_client.getArguments());
    }

    @FXML protected void Import(ActionEvent event) throws IOException, SQLException {
        File selectedFile = Helpers.chooseDocumentFile();

        if (selectedFile != null) {
            BufferedReader lineReader = new BufferedReader(new FileReader(selectedFile));
            String lineText = null;

            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null && !lineText.isEmpty()) {
                String[] data = lineText.split(",");
                String email = data[0];
                String firstName = data[1];
                String lastName = data[2];
                String zipCode = data[3];
                String city = data[4];
                String streetAddress = data[5];

                Client new_client = new Client(email, firstName, lastName, zipCode, city, streetAddress);
                Database database = new Database();
                database.insertRecord("Clients", new_client.getArguments());
                System.out.println("Row inserted into Clients with following data: ");
                System.out.println(new_client.getArguments());

            }
        }
    }

    @FXML protected void Export(ActionEvent event) throws IOException {
        File selectedDirectory = Helpers.chooseFileDestination();
        if (selectedDirectory != null){
            Database database = new Database();
            List<String> dataArray = database.ExportRecords("Clients");
            Helpers.printToCSV(dataArray, selectedDirectory);
        }
        System.out.println(selectedDirectory);
    }

}