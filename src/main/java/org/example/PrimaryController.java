//package org.example;
//
//import java.io.IOException;
//import javafx.fxml.FXML;
//
//public class PrimaryController {
//
//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }
//}

package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.io.IOException;

public class PrimaryController {
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    @FXML private Text actiontarget;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException{
        String filledUsername = usernameField.getText();
        String filledPassword = passwordField.getText();
        String correctUsername = "admin";
        String correctPassword = "admin";

        if (filledUsername.equals(correctUsername) && filledPassword.equals(correctPassword)){
            App.setRoot("MainPage");
        }else {
            actiontarget.setText("Wrong credentials");
        }
    }

}