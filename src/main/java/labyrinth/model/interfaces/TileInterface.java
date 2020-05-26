package labyrinth.model.interfaces;

import labyrinth.model.Tile;

/**
 * Interface for the {@code Tile} class.
 */
public interface TileInterface {

    /**
     * Determines which player populates the {@code Tile} object.
     */
    enum populatedBy {
        /**
         * Blue populated by.
         */
        BLUE,
        /**
         * Red populated by.
         */
        RED,
        /**
         * None populated by.
         */
        NONE,
    }

    /**
     * Generates a random {@code Tile} object.
     *
     * @return random {@code Tile} object.
     */
    Tile randomTile();

    /**
     * Generates random borders for a {@code Tile} object.
     *
     * @param tile       {@code Tile} object of which the borders will be generated.
     * @param difficulty The scale of which borders will not be generated.
     */
    void randomBorder(Tile tile, int difficulty);
}
