package labyrinth.model;

import lombok.Data;

import java.util.Date;

@Data
public class GameSave {
    String name;
    Date saveDate;
    Map mapState;
}
