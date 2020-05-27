package labyrinth.model;

import lombok.Data;

/**
 * Object that contains the position of the players.
 */
@Data
public class Position {
    private int x;
    private int y;

    /**
     * Instantiates a new Position.
     */
    public Position() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Instantiates a new Position.
     *
     * @param x the x value.
     * @param y the y value.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the position.
     * @param x the x value.
     * @param y the y value.
     */
    public void setPositions(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
