package labyrinth.javafx.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Handles the navigation in the main menu.
 */
@Slf4j
public class MenuController {

    /**
     * Switches to the loadScene.
     * @param event The {@code ActionEvent} on which this function is called.
     * @throws IOException if it cannot find the fxml file.
     */
    public void goToLoadScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/load.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Switches to the gameScene.
     * @param event The {@code ActionEvent} on which this function is called.
     * @throws IOException if it cannot find the fxml file.
     */
    public void goToGameScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Quits the application.
     */
    public void quitApplication() {
        log.info("Exiting application...");
        Platform.exit();
    }
}
