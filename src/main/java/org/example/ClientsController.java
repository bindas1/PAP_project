package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class ClientsController {

    @FXML private void signOut() throws IOException { App.setRoot("primary"); }

    @FXML private void goToOrders() throws IOException { App.setRoot("orders"); }

    @FXML private void goToMainPage() throws IOException { App.setRoot("MainPage"); }

    @FXML private void goToProducts() throws IOException { App.setRoot("products"); }
}