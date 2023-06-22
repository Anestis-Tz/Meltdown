package miscellaneous;

import maingameitems.Item;
import maingameitems.ItemHold;
import maingameitems.checklist.ItemGenList;


public class ContainsItem implements java.io.Serializable{
    
    private Item t;
    private ItemHold th;
    
    public ContainsItem(Item aThing, ItemHold aThingHolder) {
        t = aThing;
        th = aThingHolder;
    }

    public Item getThing() {
        return t;
    }

    public void setThing(Item aThing) {
        this.t = aThing;
    }

    public ItemHold getThingHolder() {
        return th;
    }

    public void setThingHolder(ItemHold aThingHolder) {
        this.th = aThingHolder;
    }
    
    public ItemGenList getList() {
        return th.getThings();
    }
     
}
