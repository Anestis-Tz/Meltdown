package maingameitems;

import maingame.vocab.NounPhrases;
import miscellaneous.Weight;
import java.util.ArrayList;
import java.util.Collections;

public class Item extends ItemDefinition implements java.io.Serializable {

      
    private String long_description;
    private boolean takable;
    private boolean movable;
    private boolean show; 
    private ArrayList<String> adjectives;
    private int mass;
    private ItemHold container;

    public Item(String aName, String aDescription, int aMass) {
        // constructor
        super(aName, aDescription);
        this.mass = aMass;
        this.takable = true;
        this.movable = true;
        this.long_description = "";
        this.show = true;
        adjectives = new ArrayList();
        testMass();
    }

    public Item(String aName, String aDescription,
            int aMass,
            boolean canTake, boolean canMove
    ) {
        // constructor
        super(aName, aDescription);
        this.mass = aMass;
        this.takable = canTake;
        this.movable = canMove;
        this.long_description = "";
        this.show = true;
        adjectives = new ArrayList();
        testMass();
    }

    private void testMass() {
        if ((mass < Weight.UNKNOWN) || (mass > Weight.HUGE)) {
            throw new IncorrectMassException("Mass value " + mass + " is invalid!");
        }
    }

    protected String article(String s) {
        char initial;
        String a;
        
        a = "a";
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        initial = (s.toLowerCase()).charAt(0);
        for (char c : vowels) {
            if (c == initial) {
                a = "an";
            }
        }
        return a;
    }

    public ArrayList getAdjectives() {
        return adjectives;
    }

    public void setAdjectives(ArrayList someAdjectives) {
        this.adjectives = someAdjectives;
    }

    public void addAdjectives(String[] someAdjectives) {
        Collections.addAll(adjectives, someAdjectives);
    }

    public int getMass() {
        return mass;
    }

    public int totalMass() {
        return mass;
    }

    public boolean isTakable() {
        return takable;
    }

    public void setTakable(boolean takable) {
        this.takable = takable;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public ItemHold getContainer() {
        return container;
    }
      
    public void setContainer(ItemHold container) {
        this.container = container;
    }

    public boolean show() {
        return show;
    }

    
    
    public void setShow(boolean doShow) {
        this.show = doShow;
    }

    public String open() {
        return "Cannot open " + getDescription() + " because it isn't a container.";
    }

    public String close() {
        return "Cannot close " + getDescription() + " because it isn't a container.";
    }

    public String describe() {
        String d;
        d = getLongDescription();
        return "It is " + article(d) + " " + d + ".";
    }

    public String getLongDescription() {
        String d;
        if (long_description.isEmpty()) {
            d = getDescription();
        } else {
            d = long_description;
        }
        return d;
    }

    
     public void setLongDescription(String long_description) {
        this.long_description = long_description;
    }

    private boolean adjectivesMatch(ArrayList<String> someAdjectives) {
        boolean ok = true;
        for (String a : someAdjectives) {
            if (!adjectives.contains(a)) {
                ok = false;
            }
        }
        return ok;
    }

    public boolean matchThing(NounPhrases np) {
        boolean match = false;
        if (getName().equals(np.getWord()) && adjectivesMatch(np.getAdjectives())) {
            match = true;
        }
        return match;
    }

    private boolean isInside(ContainerItem aContainer) {
        ItemHold th;
        boolean isInContainer = false;

        th = this.getContainer();
        while (th != null) {
            if (th == aContainer) {
                isInContainer = true;
            }
            th = th.getContainer();
        }
        return isInContainer;
    }

    public boolean isIn(Item t) {
        return (t instanceof ContainerItem) && (this.isInside((ContainerItem) t));
    }
}

class IncorrectMassException extends RuntimeException {

    public IncorrectMassException(String errorMessage) {
        super(errorMessage);
    }
}
