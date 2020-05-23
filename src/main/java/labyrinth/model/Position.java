package labyrinth.model;

import lombok.Data;

@Data
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPositions(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
