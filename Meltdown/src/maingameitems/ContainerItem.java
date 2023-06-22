package maingameitems;

import maingameitems.checklist.ItemGenList;


public class ContainerItem extends ItemHold implements java.io.Serializable {

    private boolean openable;
    private boolean isopen;
    private int container_volume;

    
    public ContainerItem(String aName, String aDescription, int aMass) {
        super(aName, aDescription, aMass, new ItemGenList(aDescription + " list"));
        isopen = true;
        openable = false;        
        container_volume = getMass() * 2; 
    }

  
    public ContainerItem(String aName, String aDescription, int aMass,
            boolean canTake, boolean canMove, boolean canOpen, boolean isOpen) {
        super(aName, aDescription, aMass, canTake, canMove, new ItemGenList(aDescription + " list"));
        openable = canOpen;
        isopen = isOpen;
        container_volume = getMass() * 2; 
    }

    public boolean isOpenable() {
        return openable;
    }

    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    public boolean isOpen() {
        return isopen;
    }
    
    public void setOpen(boolean openState){
        isopen = openState;
    }

    // --- actions on a Container
    @Override
    public String open() {
        String s;

        if (!openable) {
            s = "Can't open the " + getDescription();
        } else {
            if (isopen) {
                s = "The " + getDescription() + " is already open.";
            } else {
                isopen = true;
                s = "You open the " + getDescription();
            }
        }
        return s;
    }

    @Override
    public String close() {
        String s;

        if (!openable) {
            s = "Can't close the " + getDescription();
        } else {
            if (isopen) {
                isopen = false;
                s = "You close the " + getDescription();
            } else {
                s = "The " + getDescription() + " is already closed.";
            }
        }
        return s;
    }

    @Override
    public String describe() {
        String s;

        s = super.describe();
        if (openable) {
            if (isopen) {
                s += " (open)";
            } else {
                s += " (closed)";
            }
        }
        if (isopen) {
            if (getThings().size() > 0) {
                s += "\nThere is something in it.";
            }
        }
        return s;
    }

   
    public int contentsMass() {
        ItemGenList tl;
        int countmass;

        tl = flatten();
        countmass = 0;
        for (Item t : tl) {
            countmass += t.getMass();
        }
        return countmass;
    }

    
    @Override
    public int totalMass() {
        return this.getMass() + contentsMass();
    }

    public int volume() {
        return container_volume;
    }

    public void setVolume(int aVolume) {
        container_volume = aVolume;
    }
}
