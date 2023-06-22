package maingameitems;

import maingameitems.checklist.ItemGenList;
import miscellaneous.Directions;
import miscellaneous.Weight;

public class Location extends ItemHold implements java.io.Serializable {

    private Location n, s, w, e, up, down,north,south,west,east;

    public Location(String aName) {
        super(aName, "", Weight.UNKNOWN, null); 
        this.n = null;
        this.s = null;
        this.w = null;
        this.e = null;
        this.up = null;
        this.down = null;
        this.north = null;
        this.south = null;
        this.west = null;
        this.east = null;
        setThings(new ItemGenList(aName + " List"));
    }

    public void initialize(String aDescription,
            Location aN, Location aS, Location aW, Location aE, Location anUp, Location aDown) {
        setDescription(aDescription);
        this.n = aN;
        this.s = aS;
        this.w = aW;
        this.e = aE;
        this.up = anUp;
        this.down = aDown;
    }

    public void initialize(String aDescription,
            Location aN, Location aS, Location aW, Location aE
    ) {
        setDescription(aDescription);
        
        this.n = aN;
        this.s = aS;
        this.w = aW;
        this.e = aE;
        this.up = Directions.NOEXIT;
        this.down = Directions.NOEXIT;
    }

    // north
    public Location getN() {
        return n;
    }

    public void setN(Location aN) {
        this.n = aN;
    }

    // south
    public Location getS() {
        return s;
    }

    public void setS(Location aS) {
        this.s = aS;
    }

    // east
    public Location getE() {
        return e;
    }

    public void setE(Location aE) {
        this.e = aE;
    }

    // west
    public Location getW() {
        return w;
    }

    public void setW(Location aW) {
        this.w = aW;
    }

    public Location getUp() {
        return up;
    }

    public void setUp(Location up) {
        this.up = up;
    }

    public Location getDown() {
        return down;
    }

    public void setDown(Location down) {
        this.down = down;
    }

    public String describe(boolean useLongDescription) {
        String roomdesc;
        String thingsdesc;
          if (useLongDescription) {
            roomdesc = String.format("This is %s.",  getLongDescription());
        } else {
            roomdesc = String.format("%s: This is %s.", getName(), getDescription());
        }
        thingsdesc = describeTopLevelThings();
        if (!thingsdesc.isEmpty()) {
            roomdesc += "\nItems and People here:\n" + thingsdesc;
        }
        return roomdesc;
       
    }

}
