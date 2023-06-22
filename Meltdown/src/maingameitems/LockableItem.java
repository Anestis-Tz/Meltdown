package maingameitems;

public class LockableItem extends ContainerItem implements java.io.Serializable {

    private boolean locked;
    private Item thingToUnlockWith; 

    public LockableItem(String aName, String aDescription, int aMass, boolean isLocked) {
        super(aName, aDescription, aMass);
        locked = isLocked;
        thingToUnlockWith = null;
    }

    public LockableItem(String aName, String aDescription, int aMass,
            boolean canTake, boolean canMove, boolean canOpen, boolean isOpen,
            boolean isLocked) {
        super(aName, aDescription, aMass, canTake, canMove, canOpen, isOpen);
        locked = isLocked;
        thingToUnlockWith = null;
    }

       public void canBeUnlockedWith(Item t) {
       thingToUnlockWith = t;
    }

    public String trytounlockWith(Item t) {
        String s;

        if (!locked) {
            s = getDescription() + " is already unlocked";
        } else if (t == thingToUnlockWith) {
            locked = false;
            s = "You unlock the " + this.getDescription() + " with the " + t.getDescription();
        } else {
            s = "You can't unlock the " + this.getDescription() + " with the " + t.getDescription();
        }
        return s;
    }

    public String trytolockWith(Item t) {
        String s;

        if (locked) {
            s = "The " + getDescription() + " is already locked.";
        } else if (isOpen()) {
            s = "You cannot lock the " + getDescription() + " while it is open.";
        } else if (t == thingToUnlockWith) {
            locked = true;
            s = "You lock the " + getDescription();
        } else {
            s = "You can't lock the " + this.getDescription() + " with the " + t.getDescription();
        }
        return s;
    }

    @Override
    public String open() {
        String s;

        if (locked) {
            s = "You can't open the " + getDescription() + " because it's locked.";
        } else {
            s = super.open();
        }
        return s;
    }

}
