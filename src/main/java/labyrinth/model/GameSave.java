package labyrinth.model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The {@code SaveGame} object. It is responsible for the model of savegames.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "saveName", "saveDate", "mapState"})
@Data
public class GameSave {

    @XmlAttribute
    String saveName;

    @XmlAttribute
    Date saveDate;

    @XmlElement
    Map mapState;


    /**
     * Instantiates a new Game save.
     */
    public GameSave() {
        this.saveName = null;
        this.saveDate = null;
        this.mapState = null;
    }

    /**
     * Instantiates a new Game save.
     *
     * @param map the map
     */
    public GameSave(Map map) {
        this.mapState = map;
        this.saveDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.saveName = formatter.format(saveDate);
    }
}
