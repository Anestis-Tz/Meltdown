package maingameitems.checklist.tool;

import maingameitems.checklist.ContainsItemList;
import miscellaneous.ContainsItem;
import java.util.ArrayList;

public class ItemVerification {

    private static String listMultipleThings(ArrayList<ContainsItem> things) {
        String s = "";

        for (ContainsItem tth : things) {
            s += tth.getThing().getDescription() + "\n";
        }
        return s;
    }

      public static String oneThingAndOneContainerInLists(
            ContainsItemList things,
            ContainsItemList container_things,
            String thing1Desc,
            String thing2Desc,
            String verb, String preposition) {
        String s = "";

        if (things.isNullOrEmpty()) {
            s = "You haven't got " + thing1Desc;
        } else if (container_things.isNullOrEmpty()) {
            s = "There is no " + thing2Desc + " here!";
        } else if (things.size() > 1) {
            s = "Which of these do you want to " + verb + " " + preposition + " the " + thing2Desc + "?\n";
            s += listMultipleThings(things);
        } else if (container_things.size() > 1) {
            s = "Which " + thing2Desc + " do you want to put the " + thing1Desc + " into?\n";
            s += listMultipleThings(container_things);
        }

        return s;
    }

   
    public static String oneThingInList(ContainsItemList things, String thingDesc,
        String verb, boolean mustBeInInventory) { 
        String s = "";

        if (things.isNullOrEmpty()) {
            if (mustBeInInventory) {
                s = "You haven't got " + thingDesc;
            } else {
                s = "Can't see " + thingDesc + " here.";
            }
        } else if (things.size() > 1) {
            s = "Which of these do you want to " + verb + "?\n";
            s += listMultipleThings(things);
        }
        return s;
    }

     public static String oneThingInList(ContainsItemList things, String thingDesc,
            String verb) {
        String s = "";
        return oneThingInList(things, thingDesc, verb, false);
    }

}
