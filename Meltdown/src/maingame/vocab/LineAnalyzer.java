package maingame.vocab;

import miscellaneous.WordTypes;
import java.util.ArrayList;
import java.util.List;

public class LineAnalyzer implements java.io.Serializable {

    private List<GrammaticalUnits> sentence;
    private List<TypeAndWord> rest;
    private String error;

    public LineAnalyzer(List<TypeAndWord> wtlist) {
        rest = wtlist;
        sentence = new ArrayList<GrammaticalUnits>();
        error = "";
    }

    private TypeAndWord getNextElement() {
        if (rest.isEmpty()) {
            return null;
        } else {
            return rest.get(0);
        }
    }

    private ArrayList<String> getAdjectives(TypeAndWord wt) {
        ArrayList<String> adjectives = new ArrayList<>();
        boolean runloop = true;

        while (runloop) {
            if (wt == null) {
                runloop = false;
            } else if (wt.getWordtype() == WordTypes.ADJECTIVE) {
                adjectives.add(wt.getWord());
                rest.remove(wt);
                wt = getNextElement();
            } else {
                runloop = false;
            }
        }
        return adjectives;
    }

    public String getError() {
        return error;
    }

    public boolean containsError() {
        boolean yes = false;
        error = "";
        for (GrammaticalUnits gu : sentence) {
            if (gu instanceof GrammaticalErrors) {
                error += ((GrammaticalErrors) gu).getWord() + "! ";
                yes = true;
            }
        }
        return yes;
    }

    private String getNoun(TypeAndWord wt) {
        String s = "";
        if (wt != null) {
            if (wt.getWordtype() == WordTypes.NOUN) {
                s = wt.getWord();
            }
        }
        return s;
    }

    private void addNounPhrase(TypeAndWord wt) {
        TypeAndWord nextWT;
        String noun;
        ArrayList<String> adjectives;

        nextWT = wt;
        adjectives = getAdjectives(nextWT);
        if (adjectives.isEmpty()) {
            noun = getNoun(nextWT);
        } else {
            nextWT = getNextElement();
            noun = getNoun(nextWT);
        }
        if (noun.isEmpty()) {
            addError("Missing Noun");
        } else {
            sentence.add(new NounPhrases(noun, adjectives));
            rest.remove(nextWT);
        }
    }

    private void addError(String errorMsg) {
        sentence.add(new GrammaticalErrors(errorMsg));
    }

    private void addPreposition(TypeAndWord wt) {
        sentence.add(new Prepositions(wt.getWord()));
        rest.remove(wt);
    }

    private void addVerb(TypeAndWord wt) {
        sentence.add(new Verbs(wt.getWord()));
        rest.remove(wt);
    }

    public List<GrammaticalUnits> analyze() {
        TypeAndWord wt;
        String word;

        while (!rest.isEmpty()) {
            wt = getNextElement();
            word = wt.getWord();
            switch (wt.getWordtype()) {
                case VERB:
                    addVerb(wt);
                    break;
                case ADJECTIVE:
                case NOUN:
                    addNounPhrase(wt);
                    break;
                case PREPOSITION:
                    addPreposition(wt);
                    break;
                case ERROR:
                    addError("Grammar analysis ERROR");
                    break;
                default:
                    break;
            }
        }
        return sentence;
    }
}
