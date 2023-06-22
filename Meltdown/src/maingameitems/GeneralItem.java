package maingameitems;

import miscellaneous.Weight;


public class GeneralItem extends Item implements java.io.Serializable {

    private boolean plural;  

    public GeneralItem(String aName, String aDescription, boolean aPlural) {
        super(aName, aDescription, Weight.UNKNOWN, false, false);
        plural = aPlural;
        setShow(false); 
    }

    public boolean isPlural() {
        return plural;
    }

    @Override
    protected String article(String s) {
        String a;
        
        if (this.isPlural()) {
          a = "";
        } else {
            a = super.article(s);
        }
        return a;
    }

    @Override
    public String describe() {
        String d;
        String s;
        
        d = getLongDescription();
        if (this.isPlural()) {
            s = "They are " + article(d) + d + ".";
        } else {
            s = "It is " + article(d) + " " + d + ".";
        }
        return s;
    }
}
