package labyrinth.model.utilities;

import labyrinth.model.Tile;

import java.util.concurrent.ThreadLocalRandom;

public abstract class TileUtilities {

    public enum populatedBy {
        BLUE,
        RED,
        NONE,
    }

    public Tile RandomTile() {
        Tile newTile = new Tile();
        this.RandomBorder(newTile);
        return newTile;
    }

    private void RandomBorder(Tile newTile) {
        switch (ThreadLocalRandom.current().nextInt(0, 12)) {
            case 0: newTile.setTopBlocked(true); break;
            case 1: newTile.setRightBlocked(true); break;
            case 2: newTile.setBottomBlocked(true); break;
            case 3: newTile.setLeftBlocked(true); break;
            default: break;
        }
    }
}
