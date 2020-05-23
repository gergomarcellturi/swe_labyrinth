package labyrinth.javafx.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    public void goToLoadScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/load.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void goToGameScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void quitApplication(ActionEvent event) {
        System.out.println("Exiting...");
        Platform.exit();
    }
}
