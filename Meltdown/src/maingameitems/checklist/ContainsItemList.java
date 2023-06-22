package maingameitems.checklist;

import maingameitems.Item;
import miscellaneous.ContainsItem;
import java.util.ArrayList;

public class ContainsItemList extends ArrayList<ContainsItem> implements java.io.Serializable {

    public ContainsItemList() {
        super();
    }
    
    public boolean isNullOrEmpty(){
       return ((this == null) || (this.isEmpty()));
    }
}
