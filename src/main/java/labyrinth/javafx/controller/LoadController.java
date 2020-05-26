package labyrinth.javafx.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import labyrinth.model.GameSave;
import labyrinth.model.JAXB;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

/**
 * Handles the {@code GameSave} loadings from files.
 */
@Slf4j
public class LoadController {

    private ObservableList<GameSave> saveGames = FXCollections.observableArrayList();
    private GameSave selected;

    @FXML
    private ListView<GameSave> saveGameList;

    @FXML
    private Button loadButton;

    /**
     * Initializes the LoadController.
     * @throws FileNotFoundException if it doesn't find the files.
     * @throws JAXBException if it is not able to convert the files into {@code Gamesave} objects.
     */
    @FXML
    public void initialize() throws FileNotFoundException, JAXBException {
        this.checkLoadDisabled();
        this.loadDataIntoList();
    }

    /**
     * Switches to the menu.
     * @param event {@code ActionEvent} on which this function is called
     * @throws IOException if it is not able to find the fxml file.
     */
    @FXML
    public void goToMenuScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Changed the currently selected loadable record on the listview.
     */
    @FXML
    public void changeSelected() {
        this.selected = saveGameList.getSelectionModel().getSelectedItem();
        this.checkLoadDisabled();
    }

    /**
     * Loads the selected record from the listview.
     * @param event {@code ActionEvent} on which this function is called
     * @throws IOException if it is not able to find the fxml file.
     */
    @FXML
    public void loadSelected(ActionEvent event) throws IOException {

        log.info("Loading selected savegame...");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));

        Parent root = fxmlLoader.load();
        GameController controller = fxmlLoader.getController();
        controller.setMap(selected);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Checks if there is a selected loadable item from the listview. If there is, the load button will be activated.
     */
    private void checkLoadDisabled() {
        this.loadButton.setDisable(this.selected == null);
    }

    /**
     * Loads the files from the given folder.
     * @param folder the {@code File} folder from which the {@code GameSave} will be imported.
     * @throws FileNotFoundException if it is not able to find the folder.
     * @throws JAXBException if it is not able to convert the xml files to {@code GameSave} objects.
     */
    private void loadSavegameFiles(final File folder) throws FileNotFoundException, JAXBException {
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                loadSavegameFiles(fileEntry);
            } else {
                saveGames.add(JAXB.fromXML(GameSave.class, new FileInputStream(fileEntry)));
            }
        }
    }

    /**
     * Loads the data into the listview.
     * @throws FileNotFoundException if it can't file the files from which the data will be loaded.
     * @throws JAXBException if it is not able to convert the xml files to {@code GameSave} objects.
     */
    private void loadDataIntoList() throws FileNotFoundException, JAXBException {

        log.info("Loading save files...");
        saveGames.removeAll();
        final File folder = new File("saves");

        if(!folder.exists()) {
            if (folder.mkdir()) {
                log.info("Created Saves folder");
            } else {
                log.error("Could not create Save Folder");
                Platform.exit();
            }
        }

        this.loadSavegameFiles(folder);
        this.saveGameList.getItems().addAll(saveGames);

        this.saveGameList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(GameSave item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getSaveName());
                }
            }
        });
    }
}
