package labyrinth.javafx.controller;

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
import labyrinth.model.Map;
import labyrinth.model.Position;
import labyrinth.model.utilities.TileUtilities;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class GameController {

    private Map map = new Map();

    @FXML
    private GridPane gridElement;

    @FXML
    public void initialize() {
        this.visualizeMap();
    }

    public void KeyPressed(KeyEvent key) {

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
            this.resetAction();
        }

    }

    public void goToMenuScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void saveAction() {
    }

    public void resetAction() {
        this.map = new Map();
        visualizeMap();
    }

    private void visualizeMap() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                this.setBorders(gridElement.getChildren().get(j * 7 + i), i, j);
                if (map.getState()[i][j].getPopulated() != TileUtilities.populatedBy.NONE) {
                    this.drawPlayer(map.getState()[i][j].getPopulated(), gridElement.getChildren().get(j * 7 + i), i,j);
                }
            }
        }
    }

    private void setBorders(Node gridChild, int i, int j) {

        gridChild.setStyle("-fx-border-color: black; -fx-border-width:"
                .concat(this.map.getState()[i][j].isTopBlocked() ? " 2px" : " 0px")
                .concat(this.map.getState()[i][j].isRightBlocked() ? " 2px" : " 0px")
                .concat(this.map.getState()[i][j].isBottomBlocked() ? " 2px" : " 0px")
                .concat(this.map.getState()[i][j].isLeftBlocked() ? " 2px;" : " 0px;"));



    }

    private void drawPlayer(TileUtilities.populatedBy populatedBy, Node gridChild, int x, int y) {

        if (populatedBy == TileUtilities.populatedBy.BLUE) {
            Image image = new Image("/images/blue.png");

            gridChild.setStyle(gridChild.getStyle().concat(" -fx-background-image: url(")
                    .concat(image.getUrl()+ "); -fx-background-size: contain;")
            .concat("-fx-background-repeat:no-repeat;"));

        } else if (populatedBy == TileUtilities.populatedBy.RED) {
            Image image = new Image("/images/red.png");

            gridChild.setStyle(gridChild.getStyle().concat(" -fx-background-image: url(")
                    .concat(image.getUrl()+ "); -fx-background-size:contain;")
            .concat("-fx-background-repeat:no-repeat;"));
        }

    }

    private void move(int posX, int posY) {

        if (checkMovable(posX, posY, map.getRedPosition()) && checkMovable(-posX, -posY, map.getBluePosition())) {
                map.getState()[map.getRedPosition().getX()][map.getRedPosition().getY()].setPopulated(TileUtilities.populatedBy.NONE);
                map.getState()[map.getRedPosition().getX()+posX][map.getRedPosition().getY()+posY].setPopulated(TileUtilities.populatedBy.RED);
                map.getRedPosition().setPositions(map.getRedPosition().getX()+posX,map.getRedPosition().getY()+posY);

                map.getState()[map.getBluePosition().getX()][map.getBluePosition().getY()].setPopulated(TileUtilities.populatedBy.NONE);
                map.getState()[map.getBluePosition().getX()-posX][map.getBluePosition().getY()-posY].setPopulated(TileUtilities.populatedBy.BLUE);
                map.getBluePosition().setPositions(map.getBluePosition().getX()-posX,map.getBluePosition().getY()-posY);
        }
    }

    private boolean checkMovable(int posX, int posY, Position position) {

        switch (posX) {
            case 1: if ( map.getState()[position.getX()][position.getY()].isRightBlocked()) {
                return false;
            } else {
                break;
            }
            case -1: if ( map.getState()[position.getX()][position.getY()].isLeftBlocked()) {
                return false;
            } else {
                break;
            }
            default: break;
        }

        switch (posY) {
            case 1: if ( map.getState()[position.getX()][position.getY()].isBottomBlocked()) {
                return false;
            } else {
                break;
            }
            case -1: if ( map.getState()[position.getX()][position.getY()].isTopBlocked()) {
                return false;
            } else {
                break;
            }
            default: break;
        }

        return true;
    }

    private boolean checkVictory() {
        return map.getRedPosition().getX() == map.getBluePosition().getX() && map.getRedPosition().getY() == map.getBluePosition().getY();
    }



}
