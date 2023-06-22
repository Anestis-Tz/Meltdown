package maingameitems;

public class NonContainerItem extends Item implements java.io.Serializable {

    public NonContainerItem(String aName, String aDescription, int aMass) {
        super(aName, aDescription, aMass);     
    }

    public NonContainerItem(String aName, String aDescription,
            int aMass,
            boolean canTake, boolean canMove) {
        super(aName, aDescription, aMass, canTake, canMove);
    }

}
