package labyrinth.model;

import labyrinth.model.interfaces.TileInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {

    @Test
    void testCreateMapBorders() {

        for (int i = 0; i<7; i++) {
            for (int j = 0; j<7; j++) {
                if (i == 0) {
                    assertEquals(true, new Map().getState()[i][j].isLeftBlocked());
                }

                if (i == 6) {
                    assertEquals(true, new Map().getState()[i][j].isRightBlocked());
                }

                if (j == 0) {
                    assertEquals(true, new Map().getState()[i][j].isTopBlocked());
                }

                if (j == 6) {
                    assertEquals(true, new Map().getState()[i][j].isBottomBlocked());
                }
            }
        }

    }

    @Test
    void testPositionPlayers() {
        assertEquals(TileInterface.populatedBy.BLUE, new Map().getState()[6][6].getPopulated());
        assertEquals(TileInterface.populatedBy.RED, new Map().getState()[0][0].getPopulated());

        for (int i = 0; i<7; i++) {
            for (int j = 0; j < 7; j++) {
                if ( (i != 0 && j != 0) && (i != 6 && j != 6)) {
                    assertEquals(TileInterface.populatedBy.NONE, new Map().getState()[i][j].getPopulated());
                }
            }
        }

    }
}
