package labyrinth.model;

import labyrinth.model.utilities.TileUtilities;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

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
                state[i][j] = new Tile().RandomTile();
//                state[i][j] = new Tile();
            }
        }

        this.setMapBorders();
        this.corrigateMapBorders();

    }

    private void setMapBorders() {

        System.out.println("Setting map borders...");

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

        System.out.println("corrigating map borders");


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

        System.out.println("positioning players...");

//        int blue_x = ThreadLocalRandom.current().nextInt(1, 6);
//        blue_x += blue_x % 2 == 0 ? 1 : 0;
//        int blue_y = ThreadLocalRandom.current().nextInt(1,6);
//        blue_y += blue_y % 2 == 0 ? 1 : 0;
//
//        int red_x = ThreadLocalRandom.current().nextInt(1, 6);
//        red_x += red_x % 2 == 0 ? 1 : 0;
//        int red_y = ThreadLocalRandom.current().nextInt(1,6);
//        red_y += red_y % 2 == 0 ? 1 : 0;
//
//        if ( blue_x != red_x || blue_y != red_y) {
//            state[blue_x][blue_y].setPopulated(TileUtilities.populatedBy.BLUE);
//            state[red_x][red_y].setPopulated(TileUtilities.populatedBy.RED);
//            this.bluePosition = new Position(blue_x, blue_y);
//            this.redPosition = new Position(red_x, red_y);
//            System.out.println("Blue x: " + (blue_x+1));
//            System.out.println("Blue y: " + (blue_y+1));
//            System.out.println("Red x: " + (red_x+1));
//            System.out.println("Red y: " + (red_y+1));
//        } else {
//            this.positionPlayers();
//        }

        state[6][6].setPopulated(TileUtilities.populatedBy.BLUE);
        state[0][0].setPopulated(TileUtilities.populatedBy.RED);
        this.bluePosition = new Position(6, 6);
        this.redPosition = new Position(0, 0);
    }
}
