package org.example;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helpers {
    public static File chooseDocumentFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Document Files", "*.csv"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        return selectedFile;
    }

    public static File chooseFileDestination(){
        FileChooser directoryChooser = new FileChooser();
        directoryChooser.setTitle("Choose file destination");
        directoryChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Document Files", "*.csv")
        );
        File selectedDirectory = directoryChooser.showSaveDialog(null);
        return selectedDirectory;
    }

    public static void printToCSV(List<String> dataArray, File directory) throws IOException {
        BufferedWriter save = new BufferedWriter(new FileWriter(directory));
        for (int i = 0; i < dataArray.size(); i++){
            save.write(dataArray.get(i));
            save.newLine();
        }
        save.close();
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
