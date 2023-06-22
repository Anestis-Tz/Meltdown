
package maingameitems;

import maingameitems.specialitems.MagicActions;
import miscellaneous.Debugging;
import miscellaneous.Directions;
import maingameitems.Location;
import miscellaneous.Weight;
import miscellaneous.ContainsItem;
import maingameitems.checklist.ItemGenList;
import maingame.vocab.NounPhrases;
import maingameitems.checklist.ContainsItemList;
import maingameitems.checklist.tool.ItemVerification;


public class Player extends ItemHold implements java.io.Serializable {

    final int MAXLOAD = Weight.MEDIUM + Weight.SMALL;

    private int load;
    public int hp;
    
    public Player(String aName, String aDescription, Location aRoom) {
        super(aName, aDescription, Weight.UNKNOWN, new ItemGenList()); 
        this.setContainer(aRoom);
        load = 0;
        hp = 0;
    }

    public void setLocation(Location aRoom) {
        setContainer(aRoom);
    }

    public Location getLocation() {
        return (Location) getContainer();
    }

    public String describeLocation(boolean useLongdescription) {
        return ((Location) getContainer()).describe(useLongdescription);
    }
    
           
        public String inventory() {
        String s;

        s = describeThings();
        if (s.isEmpty()) {
            s = "nothing";
        }
        return "You have " + s;
    }

    public ContainsItemList matchingThingsHere(NounPhrases np) {
        ContainsItemList things;

        things = matchingThingsInInventory(np);
        things.addAll(matchingThingsInRoom(np));
        return things;
    }

    public ContainsItemList matchingThingsInInventory(NounPhrases np) {
        ContainsItemList things;

        things = findThings(np);
        if (Debugging.FULL) {
            if (things != null) {
                for (ContainsItem tth : things) {
                    System.out.println("FOUND in isThingInInventory(): " + tth.getThing().getName() + " : " + tth.getThing().getDescription());
                    System.out.println(tth.getThing().getAdjectives());
                }
            }
        }
        return things;
    }

    public ContainsItemList matchingThingsInRoom(NounPhrases np) {
        ItemHold th;
        Location r;
        ContainsItemList things;

        th = getContainer();
        r = (Location) th;
        things = (r.findThings(np));
        if (Debugging.FULL) {
            if (things != null) {
                for (ContainsItem tth : things) {
                    System.out.println("FOUND in isThingInRoom(): " + tth.getThing().getName() + " : " + tth.getThing().getDescription());
                    System.out.println(tth.getThing().getAdjectives());
                }
            }
        }
        return things;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public String openWith(NounPhrases np, NounPhrases np2) {
        String s;
        ContainsItemList thingToOpenList;
        ContainsItemList thingToUseList;

        thingToOpenList = matchingThingsHere(np);
        thingToUseList = matchingThingsInInventory(np2);
        s = ItemVerification.oneThingInList(thingToOpenList, np.phrase(), "open");
        if (s.isEmpty()) {
            s = ItemVerification.oneThingInList(thingToUseList, np2.phrase(), "use", true);
            if (s.isEmpty()) {
                s = "You can't open the " + np.phrase() + " with the " + np2.phrase();
            }
        }
        return s;
    }

    public String lockWith(NounPhrases np, NounPhrases np2) {
        String s;
        Item t;
        Item t2;
        ContainsItem t_th;
        ContainsItem t_th2;
        ContainsItemList thingToOpenList;
        ContainsItemList thingToUseList;

        thingToOpenList = matchingThingsHere(np);
        thingToUseList = matchingThingsInInventory(np2);
        s = ItemVerification.oneThingInList(thingToOpenList, np.phrase(), "lock");
        if (s.isEmpty()) {
            s = ItemVerification.oneThingInList(thingToUseList, np2.phrase(), "use", true);
            if (s.isEmpty()) {
                t_th = thingToOpenList.get(0);
                t_th2 = thingToUseList.get(0);
                t = t_th.getThing();
                t2 = t_th2.getThing();
                if (t instanceof LockableItem) {
                    s = ((LockableItem) t).trytolockWith(t2);
                } else {
                    s = "You can't lock the " + np.phrase() + " with the " + np2.phrase();
                }
            }
        }
        return s;
    }

    public String unlockWith(NounPhrases np, NounPhrases np2) {
        String s;
        Item t;
        Item t2;
        ContainsItem t_th;
        ContainsItem t_th2;
        ContainsItemList thingToOpenList;
        ContainsItemList thingToUseList;

        thingToOpenList = matchingThingsHere(np);
        thingToUseList = matchingThingsInInventory(np2);
        s = ItemVerification.oneThingInList(thingToOpenList, np.phrase(), "unlock");
        if (s.isEmpty()) {
            s = ItemVerification.oneThingInList(thingToUseList, np2.phrase(), "use", true);
            if (s.isEmpty()) {
                t_th = thingToOpenList.get(0);
                t_th2 = thingToUseList.get(0);
                t = t_th.getThing();
                t2 = t_th2.getThing();
                if (t instanceof LockableItem) {
                    s = ((LockableItem) t).trytounlockWith(t2);
                } else {
                    s = "You can't unlock the " + np.phrase() + " with the " + np2.phrase();
                }
            }
        }
        return s;
    }

    public String lockOb(NounPhrases np) {
        String s;
        ContainsItem t_th;
        ContainsItemList things;

        things = matchingThingsHere(np);
        s = ItemVerification.oneThingInList(things, np.phrase(), "lock");
        if (s.isEmpty()) {
            s = "You need to say what you want to lock it with!";
        }
        return s;
    }

    public String unlockOb(NounPhrases np) {
        String s;
        ContainsItem t_th;
        ContainsItemList things;

        things = matchingThingsHere(np);
        s = ItemVerification.oneThingInList(things, np.phrase(), "unlock");
        if (s.isEmpty()) {
            s = "You need to say what you want to unlock it with!";
        }
        return s;
    }

    public String openOb(NounPhrases np) {
        String s;
        ContainsItem t_th;
        ContainsItemList things;
        Item t;

        things = matchingThingsHere(np);
        s = ItemVerification.oneThingInList(things, np.phrase(), "open");
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            s = t.open();
        }
        return s;
    }

    public String closeOb(NounPhrases np) {
        String s;
        ContainsItem t_th;
        ContainsItemList things;
        Item t;

        things = matchingThingsHere(np);
        s = ItemVerification.oneThingInList(things, np.phrase(), "close");
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            s = t.close();
        }
        return s;
    }

    public String lookIn(NounPhrases np) {
        String s = "";
        ContainsItem t_th;
        Item t;
        ContainerItem container;
        ContainsItemList things;

        things = matchingThingsHere(np);
        s = ItemVerification.oneThingInList(things, np.phrase(), "look in");
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            container = toContainerThing(t);
            if (container == null) {
                s = "You can't look inside the " + t.getDescription() + ".";
            } else {
                if (container.isOpen()) {
                    s = container.describeThings();
                    if (s.isEmpty()) {
                        s = "There is nothing in the " + container.getDescription();
                    } else {
                        s = "The " + container.getDescription() + " contains:\n" + s;
                    }
                } else {
                    s += "The " + container.getDescription() + " isn't open.";
                }
            }
        }
        return s;
    }

    public String lookAt(NounPhrases np) {
        String s = "";
        ContainsItem t_th;
        Item t;
        ItemHold th;
        ContainsItemList things;

        things = matchingThingsHere(np);
        s = ItemVerification.oneThingInList(things, np.phrase(), "look at");
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            th = t_th.getThingHolder();
            if (th instanceof ContainerItem) {
                s = "[The " + t.getDescription() + " is inside " + th.getDescription() + "]\n";
            }
            s += t.describe();
        }
        return s;
    }

    private void doPutInto(Item t, ItemHold th, ContainerItem container) {
        boolean containerIsInRoom;
        containerIsInRoom = false;

        transferOb(t, th, container);
       
        if (this.getLocation().containsThing(container, true)) {
            containerIsInRoom = true;
        }
        if (containerIsInRoom) { 
            load -= t.getMass();
        }

    }

    private String putInto_SanityCheck(Item t, ContainerItem container,
            String thingStr, String containerStr) {
        String s = "";

        if ((container == null)) { 
            s = "You can't put the " + thingStr + " into the " + containerStr + "!";
        } else if (t == container) {
            s = "You can't put the " + thingStr + " into itself!";
        } else if (container.containsThing(t, true)) {
            s = "The " + thingStr + " is already in the " + containerStr;
        } else if (!(container).isOpen()) {
            s = "You can't put the " + thingStr + " into a closed " + containerStr + "!";
        } else if (container.isIn(t)) {
            s = "You can't put the " + thingStr + " into the " + containerStr
                    + "\nbecause the " + containerStr + " is already in the " + thingStr + "!";
        } else if ((container.totalMass() + t.totalMass()) > container.volume()) {
            s = "The " + containerStr + " isn't big enough for the " + thingStr;
            if (container.numberOfThings() > 0) {
                s += "\nMaybe you could take something out of it and try again?";
            }
        }
        return s;
    }

    public String putInto(NounPhrases np, NounPhrases np2) {
        String s;
        ContainsItem t_th;
        Item t;
        ContainsItem cont_th;
        ContainsItemList things;
        ContainsItemList container_things;
        ContainerItem container;

        things = matchingThingsInInventory(np);
        container_things = matchingThingsHere(np2);
        s = ItemVerification.oneThingAndOneContainerInLists(things, container_things, np.phrase(),
                np2.phrase(), "put", "into");
        if (s.isEmpty()) {                  
            cont_th = container_things.get(0);
            t_th = things.get(0);
            t = t_th.getThing();
            container = toContainerThing(cont_th.getThing());
            s = putInto_SanityCheck(t, container, np.phrase(), np2.phrase());
            if (s.isEmpty()) {
              if (s.isEmpty()) {
                    doPutInto(t, t_th.getThingHolder(), container);
                    s = "You put the " + np.phrase() + " into the " + np2.phrase() + ".";
                }
            }
        }
        return s;
    }

    private String debugTakeDrop(Item t, int totalMass, boolean isContainer) {
        String s = "";

        s += "\nPlayer load=" + load + " MAXLOAD=" + MAXLOAD
                + ". Mass of " + t.getName() + "=" + t.getMass();
        if (isContainer) {
            s += " (This is a Container) total mass = " + totalMass;
        }
        return s;
    }

    private String doTake(Item t, ItemHold th) {
        String s;
        ContainerItem ct;
        int tmass;

        tmass = t.totalMass();
        if (t.isTakable()) {
            if (this.inTopLevelList(t)) {
                s = "You already have the " + t.getDescription();
            } else if ((load + tmass) > MAXLOAD) {
                s = "You are carrying too much. You need to drop\n"
                        + "something before taking the " + t.getDescription();
            } else {
                load += tmass;
                // special action?
                s = MagicActions.takeSpecial(t, th, this.getLocation(), tmass);
                if (s.isEmpty()) {
                    transferOb(t, th, this);
                    if (th instanceof ContainerItem) {
                        s = "You take the " + t.getDescription() + " from the " + th.getDescription();
                    } else {
                        s = t.getDescription() + " taken!";
                    }
                }
            }
        } else { 
            s = "You can't take the " + t.getDescription() + "!";
        }
        if (Debugging.ON) {
            ct = toContainerThing(t);
            s += debugTakeDrop(t, tmass, ct != null);
        }
        return s;
    }

    public String take(NounPhrases np) {
        String s;
        ContainsItem t_th;
        Item t;
        ItemHold th;
        ContainsItemList things;

        things = matchingThingsHere(np);
        s = ItemVerification.oneThingInList(things, np.phrase(), "take");
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            th = t_th.getThingHolder();
            s = doTake(t, th);
        }
        return s;
    }
 
    public String drop(NounPhrases np) {
        String s;
        ContainsItem t_th;
        Item t;
        ItemHold th;
        ContainsItemList things;
        ContainerItem ct;
        int tmass;

        things = matchingThingsInInventory(np);
        s = ItemVerification.oneThingInList(things, np.phrase(), "drop", true);
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            th = t_th.getThingHolder();
            tmass = t.totalMass();
            // special action?
            s = MagicActions.dropSpecial(t, th, this.getLocation(), tmass);
            if (s.isEmpty()) {
                transferOb(t, th, this.getLocation());
                load -= tmass;
                s = t.getDescription() + " dropped!";
                if (Debugging.ON) {
                    ct = toContainerThing(t);
                    s += debugTakeDrop(t, tmass, ct != null);
                }
            }
        }
        return s;
    }
    
    

    public boolean moveTo(Directions dir) {
        Location r;
        Location exit;
        boolean moved;

        moved = false;
        r = getLocation();
        switch (dir) {
            case NORTH:
                exit = r.getN();
                break;
            case SOUTH:
                exit = r.getS();
                break;
            case EAST:
                exit = r.getE();
                break;
            case WEST:
                exit = r.getW();
                break;
            case UP:
                exit = r.getUp();
                break;
            case DOWN:
                exit = r.getDown();
                break;
            default:
                exit = null; 
                break;
        }
        if (exit != null) {
            setLocation(exit);
            moved = true;
        }
        return moved;
    }

   

}
