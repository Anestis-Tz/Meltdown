package maingameitems.checklist;

import maingameitems.Item;
import java.util.ArrayList;

public class ItemGenList extends ArrayList<Item> implements java.io.Serializable {

    private String name; 

    public ItemGenList() {
        super();
        name = "unnamed";
    }

    public ItemGenList(String aName) {
        super();
        name = aName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
