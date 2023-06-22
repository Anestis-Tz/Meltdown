package maingame.vocab;

import java.util.ArrayList;

public class NounPhrases extends GrammaticalUnits implements java.io.Serializable {

    private ArrayList<String> adjectives;

    NounPhrases(String aNoun, ArrayList<String> someAdjectives) {
        super(aNoun);
        adjectives = someAdjectives;
    }

    public ArrayList<String> getAdjectives() {
        return adjectives;
    }

    public void setAdjectives(ArrayList<String> adjectives) {
        this.adjectives = adjectives;
    }
    
    public String phrase(){
        String s="";
        for (String a: adjectives){
            s += a + " ";            
        }
        s += this.getWord();
        return s;
    }
}
