package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainPage {

    @FXML private void signOut() throws IOException { App.setRoot("primary"); }
}