package labyrinth.model;

import labyrinth.model.interfaces.TileInterface;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The type Tile.
 */
@Data
public class Tile implements TileInterface {

    private boolean topBlocked;
    private boolean rightBlocked;
    private boolean bottomBlocked;
    private boolean leftBlocked;
    private populatedBy populated;

    /**
     * Instantiates a new Tile.
     */
    public Tile() {
        this.topBlocked = false;
        this.rightBlocked = false;
        this.bottomBlocked = false;
        this.leftBlocked = false;
        populated = populatedBy.NONE;
    }

    @Override
    public Tile randomTile() {
        Tile newTile = new Tile();
        this.randomBorder(newTile, 12);
        return newTile;
    }

    @Override
    public void randomBorder(Tile tile, int difficulty) {
        switch (ThreadLocalRandom.current().nextInt(0, difficulty)) {
            case 0: tile.setTopBlocked(true); break;
            case 1: tile.setRightBlocked(true); break;
            case 2: tile.setBottomBlocked(true); break;
            case 3: tile.setLeftBlocked(true); break;
            default: break;
        }
    }

}
