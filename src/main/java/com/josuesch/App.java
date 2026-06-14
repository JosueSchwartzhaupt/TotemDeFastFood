package com.josuesch;

import com.josuesch.controller.TelaGeralController;
import com.josuesch.model.Sistema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static final Sistema sistema = new Sistema();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("telaGeral"), 1020, 500);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        loader.setController(
                new TelaGeralController(sistema)
        );

        return loader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}