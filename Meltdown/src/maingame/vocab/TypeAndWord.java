package maingame.vocab;

import miscellaneous.WordTypes;

public class TypeAndWord implements java.io.Serializable {

    private String word;
    private WordTypes wordtype;

    public TypeAndWord(String wd, WordTypes wt) {
        word = wd;
        wordtype = wt;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WordTypes getWordtype() {
        return wordtype;
    }

    public void setWordtype(WordTypes wordtype) {
        this.wordtype = wordtype;
    }

}
