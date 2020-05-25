package labyrinth.model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    public GameSave() {
        this.saveName = null;
        this.saveDate = null;
        this.mapState = null;
    }

    public GameSave(Map map) {
        this.mapState = map;
        this.saveDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.saveName = formatter.format(saveDate);
    }
}
