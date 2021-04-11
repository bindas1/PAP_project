package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainPage {

    @FXML private void signOut() throws IOException { App.setRoot("primary"); }

    @FXML private void goToOrders() throws IOException { App.setRoot("orders"); }

    @FXML private void goToClients() throws IOException { App.setRoot("clients"); }

    @FXML private void goToProducts() throws IOException { App.setRoot("products"); }
}