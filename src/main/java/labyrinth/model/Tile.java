package labyrinth.model;

import labyrinth.model.utilities.TileUtilities;
import lombok.Data;

@Data
public class Tile extends TileUtilities {

    private boolean topBlocked;
    private boolean rightBlocked;
    private boolean bottomBlocked;
    private boolean leftBlocked;
    private populatedBy populated;

    public Tile(boolean t, boolean r, boolean b, boolean l, Tile.populatedBy populated) {
        this.topBlocked = t;
        this.rightBlocked = r;
        this.bottomBlocked = b;
        this.leftBlocked = l;
        this.populated = populated;
    }

    public Tile() {
        this.topBlocked = false;
        this.rightBlocked = false;
        this.bottomBlocked = false;
        this.leftBlocked = false;
        this.populated = populatedBy.NONE;
    }

}
