package org.example;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class Helpers {
    public static File chooseDocumentFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Document Files", "*.csv"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        fileChooser.setInitialDirectory(new File("C:\\Users\\Bindas\\OneDrive - Politechnika Warszawska\\Documents\\2.PW\\pap21l-z22"));
        File selectedFile = fileChooser.showOpenDialog(null);
        return selectedFile;
    }

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
