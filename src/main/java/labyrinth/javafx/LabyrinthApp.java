package labyrinth.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * Labyrinth application.
 */
@Slf4j
public class LabyrinthApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("Starting application...");
        Parent root = FXMLLoader.load(LabyrinthApp.class.getResource("/fxml/menu.fxml"));
        primaryStage.setTitle("Labyrinth");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
