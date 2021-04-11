package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class ProductsController {

    @FXML private void signOut() throws IOException { App.setRoot("primary"); }

    @FXML private void goToOrders() throws IOException { App.setRoot("orders"); }

    @FXML private void goToClients() throws IOException { App.setRoot("clients"); }

    @FXML private void goToMainPage() throws IOException { App.setRoot("MainPage"); }
}