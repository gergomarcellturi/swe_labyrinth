package labyrinth.model.utilities;

import labyrinth.model.Tile;

public interface TileInterface {

    enum populatedBy {
        BLUE,
        RED,
        NONE,
    }

    Tile randomTile();
    void randomBorder(Tile newTile);
}
