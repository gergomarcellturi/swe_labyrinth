package labyrinth.model;

import labyrinth.model.utilities.TileInterface;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
public class Tile implements TileInterface {

    private boolean topBlocked;
    private boolean rightBlocked;
    private boolean bottomBlocked;
    private boolean leftBlocked;
    private populatedBy populated;

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
        this.randomBorder(newTile);
        return newTile;
    }

    @Override
    public void randomBorder(Tile newTile) {
        switch (ThreadLocalRandom.current().nextInt(0, 12)) {
            case 0: newTile.setTopBlocked(true); break;
            case 1: newTile.setRightBlocked(true); break;
            case 2: newTile.setBottomBlocked(true); break;
            case 3: newTile.setLeftBlocked(true); break;
            default: break;
        }
    }

}
