package org.example;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainPage {
    @FXML AnchorPane statistics1;
    @FXML AnchorPane statistics2;
    @FXML AnchorPane statistics3;

    @FXML private void signOut() throws IOException { App.setRoot("primary"); }

    @FXML private void goToOrders() throws IOException { App.setRoot("orders"); }

    @FXML private void goToClients() throws IOException { App.setRoot("clients"); }

    @FXML private void goToProducts() throws IOException { App.setRoot("products"); }

    @FXML protected void setStatistics1(ActionEvent event) throws IOException{
        statistics1.setVisible(true);
        statistics2.setVisible(false);
        statistics3.setVisible(false);
    }
    @FXML protected void setStatistics2(ActionEvent event) throws IOException{
        statistics1.setVisible(false);
        statistics2.setVisible(true);
        statistics3.setVisible(false);
    }
    @FXML protected void setStatistics3(ActionEvent event) throws IOException{
        statistics1.setVisible(false);
        statistics2.setVisible(false);
        statistics3.setVisible(true);
    }
}