package labyrinth.javafx.controller;

import labyrinth.model.GameSave;
import labyrinth.model.Map;
import labyrinth.model.Position;
import labyrinth.model.interfaces.TileInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    @Test
    void testCheckMoveable() {
        GameController gameController = new GameController();
        gameController.setMap( new GameSave(new Map()));
        gameController.getMap().getState()[2][2].setPopulated(TileInterface.populatedBy.RED);
        gameController.getMap().getState()[5][5].setPopulated(TileInterface.populatedBy.BLUE);
        gameController.getMap().setRedPosition(new Position(2,2));
        gameController.getMap().setBluePosition(new Position(5,5));

        gameController.getMap().getState()[2][2].setTopBlocked(true);
        gameController.getMap().getState()[2][2].setRightBlocked(true);
        gameController.getMap().getState()[2][2].setBottomBlocked(false);
        gameController.getMap().getState()[2][2].setLeftBlocked(false);

        gameController.getMap().getState()[5][5].setTopBlocked(true);
        gameController.getMap().getState()[5][5].setRightBlocked(false);
        gameController.getMap().getState()[5][5].setBottomBlocked(true);
        gameController.getMap().getState()[5][5].setLeftBlocked(false);

        assertFalse(gameController.checkMovable(0,1,gameController.getMap().getBluePosition()));
        assertTrue(gameController.checkMovable(1,0,gameController.getMap().getBluePosition()));

        assertFalse(gameController.checkMovable(0,-1,gameController.getMap().getRedPosition()));
        assertTrue(gameController.checkMovable(0,1,gameController.getMap().getRedPosition()));
    }

    @Test
    void testMove() {
        GameController gameController = new GameController();
        gameController.setMap( new GameSave(new Map()));
        gameController.getMap().getState()[2][2].setPopulated(TileInterface.populatedBy.RED);
        gameController.getMap().getState()[5][5].setPopulated(TileInterface.populatedBy.BLUE);
        gameController.getMap().setRedPosition(new Position(2,2));
        gameController.getMap().setBluePosition(new Position(5,5));

        gameController.getMap().getState()[2][2].setTopBlocked(true);
        gameController.getMap().getState()[2][2].setRightBlocked(true);
        gameController.getMap().getState()[2][2].setBottomBlocked(false);
        gameController.getMap().getState()[2][2].setLeftBlocked(false);

        gameController.getMap().getState()[5][5].setTopBlocked(true);
        gameController.getMap().getState()[5][5].setRightBlocked(false);
        gameController.getMap().getState()[5][5].setBottomBlocked(true);
        gameController.getMap().getState()[5][5].setLeftBlocked(false);

        gameController.move(0,1);

    }
}
