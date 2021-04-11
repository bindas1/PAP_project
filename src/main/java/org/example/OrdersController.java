package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class OrdersController {

    @FXML private void signOut() throws IOException { App.setRoot("primary"); }

    @FXML private void goToMainPage() throws IOException { App.setRoot("MainPage"); }

    @FXML private void goToClients() throws IOException { App.setRoot("clients"); }

    @FXML private void goToProducts() throws IOException { App.setRoot("products"); }
}