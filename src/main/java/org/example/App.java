package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * PAP21L-Z22, Binda Bartłomiej, Jankowski Paweł
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        //Image icon = new Image("icon.png");                       //Mam jakiś problem z wyszukaniem pliku png :/ "Failed to execute goal org.openjfx:javafx-maven-plugin:0.0.4:run (default-cli) on project PAP21L-Z22: Error"
        //stage.getIcons().add(icon);
        stage.setTitle("Zarządzanie sklepem - PAP21L - Z22");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}