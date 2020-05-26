package labyrinth.javafx.controller;

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

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class LoadController {

    private ObservableList<GameSave> saveGames = FXCollections.observableArrayList();
    private GameSave selected;

    @FXML
    private ListView<GameSave> saveGameList;

    @FXML
    private Button loadButton;

    @FXML
    public void initialize() throws FileNotFoundException, JAXBException {
        this.checkLoadDisabled();
        this.loadDataIntoList();
    }

    @FXML
    public void goToMenuScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void changeSelected() {
        this.selected = saveGameList.getSelectionModel().getSelectedItem();
        this.checkLoadDisabled();
    }

    @FXML
    public void loadSelected(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));

        Parent root = (Parent)fxmlLoader.load();
        GameController controller = fxmlLoader.<GameController>getController();
        controller.setMap(selected);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void checkLoadDisabled() {
        if (this.selected == null) {
            this.loadButton.setDisable(true);
        } else {
            this.loadButton.setDisable(false);
        }
    }

    private void loadSavegameFiles(final File folder) throws FileNotFoundException, JAXBException {
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                loadSavegameFiles(fileEntry);
            } else {
                saveGames.add(JAXB.fromXML(GameSave.class, new FileInputStream(fileEntry)));
            }
        }
    }

    private void loadDataIntoList() throws FileNotFoundException, JAXBException {

        saveGames.removeAll();
        final File folder = new File("saves");

        if(!folder.exists())
            folder.mkdir();

        this.loadSavegameFiles(folder);
        this.saveGameList.getItems().addAll(saveGames);

        this.saveGameList.setCellFactory(param -> new ListCell<GameSave>() {
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
