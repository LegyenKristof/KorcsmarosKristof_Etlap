package com.example.etlap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    protected Stage stage;

    public Stage getStage() {
        return stage;
    }

    public static Controller ujAblak(String fxml, String title, int width, int height) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle(title);
        stage.setScene(scene);
        Controller controller = fxmlLoader.getController();
        controller.stage = stage;
        return fxmlLoader.getController();
    }

    public void alert(String s) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setContentText(s);
        alert.getButtonTypes().add(ButtonType.OK);
        alert.show();
    }

    public boolean felugro(String uzenet) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Megerősítés");
        alert.setContentText(uzenet);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
