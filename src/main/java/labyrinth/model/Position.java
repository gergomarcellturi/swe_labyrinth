package labyrinth.model;

import lombok.Data;

@Data
public class Position {
    private int x;
    private int y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPositions(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
