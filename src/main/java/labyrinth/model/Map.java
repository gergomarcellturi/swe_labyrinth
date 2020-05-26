package labyrinth.model;

import labyrinth.model.utilities.TileInterface;
import lombok.Data;

@Data
public class Map {


    Tile[][] state;

    private Position bluePosition;

    private Position redPosition;

    public Map() {
        this.createMap();
        this.positionPlayers();
    }

    private void createMap() {
        this.state = new Tile[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                state[i][j] = new Tile().randomTile();
            }
        }

        this.setMapBorders();
        this.corrigateMapBorders();

    }

    private void setMapBorders() {

        for (int i = 0; i< 7; i++) {
            for (int j = 0; j<7; j++) {
                if ( i == 0 ) {
                    state[i][j].setLeftBlocked(true);
                }
                if ( i == 6 ) {
                    state[i][j].setRightBlocked(true);
                }

                if ( j == 0 ) {
                    state[i][j].setTopBlocked(true);
                }
                if ( j == 6 ) {
                    state[i][j].setBottomBlocked(true);
                }
            }
        }
    }


    private void corrigateMapBorders() {

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
               if (state[i+1][j].isLeftBlocked()) {
                   state[i][j].setRightBlocked(true);
               }

               if (state[i][j].isRightBlocked()) {
                   state[i+1][j].setLeftBlocked(true);
               }

               if (state[j][i+1].isTopBlocked()) {
                   state[j][i].setBottomBlocked(true);
               }
                if (state[j][i].isBottomBlocked()) {
                    state[j][i+1].setTopBlocked(true);
                }

            }
        }


    }

    private void positionPlayers() {
        state[6][6].setPopulated(TileInterface.populatedBy.BLUE);
        state[0][0].setPopulated(TileInterface.populatedBy.RED);
        this.bluePosition = new Position(6, 6);
        this.redPosition = new Position(0, 0);
    }
}
