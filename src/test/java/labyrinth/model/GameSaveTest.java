package labyrinth.model;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GameSaveTest {

    @Test
    void testGameSave() {
        assertNull(new GameSave().saveDate);
        assertNull(new GameSave().saveDate);
        assertNull(new GameSave().mapState);
    }

    @Test
    void testGameSaveParams() {
        Map map = new Map();
        Date date;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        GameSave gamesave = new GameSave(map);
        date = gamesave.getSaveDate();

        assertEquals(formatter.format(date), gamesave.getSaveName());
        assertEquals(map, gamesave.getMapState());

    }
}
