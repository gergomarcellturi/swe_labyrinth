package labyrinth.javafx.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import labyrinth.model.GameSave;
import labyrinth.model.JAXB;
import labyrinth.model.Map;
import labyrinth.model.Position;
import labyrinth.model.interfaces.TileInterface;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBException;
import java.io.*;

/**
 * Handles the controlling and displaying of the game.
 */
@Slf4j
@Data
public class GameController {

    private Map map;

    @FXML
    private GridPane gridElement;

    /**
     * Initializes the Game.
     */
    @FXML
    public void initialize() {
        log.info("Initializing...");
        Platform.runLater(() -> {
            this.createMap();
            this.visualizeMap();
        });
    }

    /**
     * Resets the Game.
     */
    public void resetAction() {
        log.info("Resetting...");
        this.map = new Map();
        visualizeMap();
    }

    /**
     * Sets the map from a gamesave.
     * @param save the {@code GameSave} object from which the map will be loaded.
     */
    public void setMap(GameSave save) {
        this.map = save.getMapState();
    }

    /**
     * Switches the gui to the menu.
     * @param event the {@code ActionEvent} from which this method is called.
     * @throws IOException if the loadable fxml file does not exist.
     */
    public void goToMenuScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Saves the current gamestate into an xml file.
     * @throws IOException if unable to create the file to which it tries to saves.
     * @throws JAXBException if unable to convert the {@code GameSave} object into xml
     */
    public void saveAction() throws IOException, JAXBException {
        log.info("Saving gamestate...");
        GameSave save = new GameSave(this.map);

        File file = new File(String.format("saves/%s.xml", save.getSaveName()));
        if (file.getParentFile().mkdirs()) {
            log.info("Created Save folder");
        }

        FileOutputStream output = new FileOutputStream(file);
        JAXB.toXML(save, output);
        output.close();
        log.info("Gamestate saved");
    }

    /**
     * Handles keyboard input from the user. After handling the input it updates the drawn map.
     * @param key the {@code KeyEvent} object, that contains which key was pressed.
     */
    public void keyPressed(KeyEvent key) {

        if (key.getCode() == KeyCode.W) {
            move(0, -1);
        }

        if (key.getCode() == KeyCode.S) {
            move(0, 1);
        }

        if (key.getCode() == KeyCode.D) {
            move(1, 0);
        }

        if (key.getCode() == KeyCode.A) {
            move(-1, 0);
        }

        this.visualizeMap();
        if (checkVictory()) {
            log.info("Victory!");
            this.resetAction();
        }

    }

    /**
     * Creates the map if it is a new game.
     */
    private void createMap() {
        if (this.map == null) {
            this.map = new Map();
            log.info("Map created");
        }
    }

    /**
     * Checks if the game ended.
     * @return {@code true} if the game ended in a victory. else returns {@code false}
     */
    private boolean checkVictory() {
        return map.getRedPosition().getX() == map.getBluePosition().getX() && map.getRedPosition().getY() == map.getBluePosition().getY();
    }

    /**
     * Draws the map tile by tile.
     */
    private void visualizeMap() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                this.drawBorders(gridElement.getChildren().get(j * 7 + i), i, j);
                if (map.getState()[i][j].getPopulated() != TileInterface.populatedBy.NONE) {
                    this.drawPlayer(map.getState()[i][j].getPopulated(), gridElement.getChildren().get(j * 7 + i));
                }
            }
        }
    }

    /**
     * Draws the borders of a tile.
     * @param gridChild The tile of which the borders will be drawn.
     * @param i The  row position of the tile on the map.
     * @param j The colomn position of the tile on the map.
     */
    private void drawBorders(Node gridChild, int i, int j) {

        gridChild.setStyle(String.format("-fx-border-color: black; -fx-border-width: %s %s %s %s",
                this.map.getState()[i][j].isTopBlocked() ? " 2px" : " 0px",
                this.map.getState()[i][j].isRightBlocked() ? " 2px" : " 0px",
                this.map.getState()[i][j].isBottomBlocked() ? " 2px" : " 0px",
                this.map.getState()[i][j].isLeftBlocked() ? " 2px;" : " 0px;"));

    }

    /**
     * Draws the given player on the map.
     * @param populatedBy The player who needs to be drawn.
     * @param gridChild The tile onto which the player will be drawn.
     */
    private void drawPlayer(TileInterface.populatedBy populatedBy, Node gridChild) {

        if (populatedBy == TileInterface.populatedBy.BLUE) {
            Image image = new Image("/images/blue.png");

            gridChild.setStyle(gridChild.getStyle().concat(String.format(
                    " -fx-background-image: url(%s); -fx-background-size: contain;",
                    image.getUrl()
            )));
        } else if (populatedBy == TileInterface.populatedBy.RED) {
            Image image = new Image("/images/red.png");

            gridChild.setStyle(gridChild.getStyle().concat(String.format(
                    " -fx-background-image: url(%s); -fx-background-size: contain; -fx-background-repeat:no-repeat;",
                    image.getUrl()
            )));
        }

    }

    /**
     * Moves the Red and Blue players inversely.
     * @param posX movement on the x axis
     * @param posY movement on the y axis.
     */
    public void move(int posX, int posY) {

        if (checkMovable(posX, posY, map.getRedPosition()) && checkMovable(-posX, -posY, map.getBluePosition())) {
                map.getState()[map.getRedPosition().getX()][map.getRedPosition().getY()]
                        .setPopulated(TileInterface.populatedBy.NONE);

                map.getState()[map.getRedPosition().getX()+posX][map.getRedPosition().getY()+posY]
                        .setPopulated(TileInterface.populatedBy.RED);

                map.getRedPosition().setPositions(
                        map.getRedPosition().getX()+posX,
                        map.getRedPosition().getY()+posY
                );

                map.getState()[map.getBluePosition().getX()][map.getBluePosition().getY()]
                        .setPopulated(TileInterface.populatedBy.NONE);

                map.getState()[map.getBluePosition().getX()-posX][map.getBluePosition().getY()-posY]
                        .setPopulated(TileInterface.populatedBy.BLUE);

                map.getBluePosition().setPositions(
                        map.getBluePosition().getX()-posX,
                        map.getBluePosition().getY()-posY
                );

            log.info("Blue position: {}", this.map.getBluePosition());
            log.info("Red position: {}", this.map.getRedPosition());
        }
    }

    /**
     * Checks if the given player can move to a certain direction.
     * @param posX movement on the x axis.
     * @param posY movement on the y acis.
     * @param position {@code Position} of the given player
     * @return {@code true} if it is possible to move the player.
     */
    public boolean checkMovable(int posX, int posY, Position position) {

        switch (posX) {
            case 1: if ( map.getState()[position.getX()][position.getY()].isRightBlocked()) {
                return false;
            }
                break;

            case -1: if ( map.getState()[position.getX()][position.getY()].isLeftBlocked()) {
                return false;
            }
                break;

            default: break;
        }

        switch (posY) {
            case 1: if ( map.getState()[position.getX()][position.getY()].isBottomBlocked()) {
                return false;
            }
                break;
            case -1: if ( map.getState()[position.getX()][position.getY()].isTopBlocked()) {
                return false;
            }
                break;

            default: break;
        }

        return true;
    }



}
