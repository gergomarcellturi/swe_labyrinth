package labyrinth.model;

import labyrinth.model.interfaces.TileInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    @Test
    void testTile() {

        assertFalse(new Tile().isLeftBlocked());
        assertFalse(new Tile().isRightBlocked());
        assertFalse(new Tile().isTopBlocked());
        assertFalse(new Tile().isBottomBlocked());
        assertEquals(TileInterface.populatedBy.NONE, new Tile().getPopulated());

    }

    @Test
    void randomBorder() {

        Tile tile = new Tile();

        tile.randomBorder(tile, 1);
        assertTrue(tile.isTopBlocked());

        tile.randomBorder(tile, 2);
        assertTrue(tile.isTopBlocked() || tile.isRightBlocked());

        tile.randomBorder(tile, 3);
        assertTrue(tile.isTopBlocked() || tile.isRightBlocked() ||
                tile.isBottomBlocked() || tile.isLeftBlocked());
    }

}
