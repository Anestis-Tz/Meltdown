package maingameitems;

import maingameitems.checklist.ItemGenList;
import maingame.vocab.NounPhrases;
import maingameitems.checklist.ContainsItemList;
import miscellaneous.ContainsItem;
import java.util.ArrayList;

public class ItemHold extends Item implements java.io.Serializable {

    private ItemGenList things;
    private ContainsItem t_and_th = null;
    private ContainsItemList thingsfound = new ContainsItemList();
    private String thingStr = "";
    ItemGenList flatlist = new ItemGenList(); 

    public ItemHold(String aName, String aDescription, int aSize, ItemGenList tl) {
        super(aName, aDescription, aSize);
        things = tl;
    }

    public ItemHold(String aName, String aDescription, int aSize, boolean canTake,
            boolean canMove, ItemGenList tl) {
        super(aName, aDescription, aSize, canTake, canMove);
        things = tl;
    }

    public ArrayList<ContainsItem> getThingsfound() {
        return thingsfound;
    }

    public static ContainerItem toContainerThing(Item t) {
        ContainerItem ct = null;

        if (t instanceof ContainerItem) {
            ct = (ContainerItem) t;
        }
        return ct;
    }

    public int numberOfThings() {
        return things.size();
    }

    private ItemGenList thingsToFlatList(ItemHold th) {

        for (Item t : th.getThings()) {
            flatlist.add(t);
            if (t instanceof ContainerItem) {
                thingsToFlatList((ContainerItem) t);
            }
        }
        return flatlist;
    }

    public ItemGenList flatten() {
        flatlist.clear();
        return thingsToFlatList(this);
    }

    private void findThingInAnyList(ItemHold th, NounPhrases np) {
        ContainerItem container;

        for (Item t : th.getThings()) {
            if (t.matchThing(np)) {
                t_and_th = new ContainsItem(t, th);
                thingsfound.add(t_and_th);
            }
            container = toContainerThing(t);
            if ((container != null) && (container.isOpen())) {
                findThingInAnyList(container, np);
            }
        }
    }

    private void doDescribeThings(ItemHold th, boolean describeAll) {
        ItemGenList tlist = th.getThings();
        ContainerItem container;

        for (Item t : tlist) {
            String inContainer = "";
            if (t.getContainer() instanceof ContainerItem) {
                inContainer = " (in the " + t.getContainer().getDescription() + ")";
            }
            if (t.show()) {
                thingStr += t.getDescription() + inContainer + "\n";
            }
            if (describeAll) {
                container = toContainerThing(t);
                if ((container != null) && (container.isOpen())) {
                    if (container.numberOfThings() > 0) {
                        doDescribeThings(container, describeAll);
                    }
                }
            }
        }
    }

    public String describeThings() {
        thingStr = "";
        doDescribeThings(this, true);
        return thingStr;
    }

    public String describeTopLevelThings() {
        thingStr = "";
        doDescribeThings(this, false);
        return thingStr;
    }

    public boolean containsThing(Item t, boolean searchAllContainers) {
        boolean yes;

        if (searchAllContainers) {
            yes = containsThingInNestedLists(t);
        } else {
            yes = getThings().contains(t);
        }
        return yes;
    }

    
    private boolean containsThingInNestedLists(Item t) {
        ItemGenList tl;
        boolean yes;

        tl = flatten();
        if (tl.contains(t)) {
            yes = true;
        } else {
            yes = false;
        }
        return yes;
    }

    public ContainsItemList findThings(NounPhrases np) {
        t_and_th = null;
        thingsfound = new ContainsItemList();
        findThingInAnyList(this, np);
        return thingsfound;
    }

    
        public boolean inTopLevelList(Item t) {
        boolean yes;

        if (things.contains(t)) {
            yes = true;
        } else {
            yes = false;
        }
        return yes;
    }

    public ItemGenList getThings() {
        return things;
    }

    public void setThings(ItemGenList things) {
        this.things = things;
    }

    public void remove(Item t) {
        things.remove(t);
    }

    public void addItem(Item t) {
        things.add(t);
        t.setContainer(this);
    }

    private void add(Item t) {
        things.add(t);
    }

    protected void transferOb(Item t, ItemHold from_TH, ItemHold to_TH) {
        from_TH.remove(t);
        to_TH.add(t);
        t.setContainer(to_TH);
    }

}
