package maingame;

import maingame.init.RecognisableWords;
import maingame.init.GameSetup;
import maingame.vocab.NounPhrases;
import maingameitems.Player;
import miscellaneous.Directions;

public class InGameCommands implements java.io.Serializable {

    private Player player;  

    public InGameCommands() {
        RecognisableWords.initVocab();
        GameSetup.initGame();
        player = GameSetup.player;
    }

    public String openObjectWithSomething(NounPhrases np, NounPhrases np2) {
        return player.openWith(np, np2);
    }

    public String lockObjectWithSomething(NounPhrases np, NounPhrases np2) {
        return player.lockWith(np, np2);
    }

    public String unlockObjectWithSomething(NounPhrases np, NounPhrases np2) {
        return player.unlockWith(np, np2);
    }

    public String putObjectInContainer(NounPhrases np, NounPhrases np2) {
        return player.putInto(np, np2);
    }

    public String openObject(NounPhrases np) {
        return player.openOb(np);
    }
      
    public String closeObject(NounPhrases np) {
        return player.closeOb(np);
    }

    public String lockObject(NounPhrases np) {
        return player.lockOb(np);
    }

    public String unlockObject(NounPhrases np) {
        return player.unlockOb(np);
    }

    String takeObject(NounPhrases np) {
        String retStr;

        retStr = player.take(np);
        return retStr;
    }

    String dropObject(NounPhrases np) {
        String retStr;

        retStr = player.drop(np);
        return retStr;
    }

    void movePlayerTo(Directions dir) {
        if (player.moveTo(dir)) {
            showStr(player.describeLocation(false));
        } else {
            showStr("No Exit!");
        }
    }

    void goN() {
        movePlayerTo(Directions.NORTH);
    }

    void goS() {
        movePlayerTo(Directions.SOUTH);
    }

    void goW() {
        movePlayerTo(Directions.WEST);
    }

    void goE() {
        movePlayerTo(Directions.EAST);
    }

    void goUp() {
        movePlayerTo(Directions.UP);
    }

    void goDown() {
        movePlayerTo(Directions.DOWN);
    }

    void look() {
        showStr(player.describeLocation(true));
    }
    
        void showStr(String s) {
        if (s.endsWith("\n")) {
            s = s.substring(0, s.length() - 1);
        }
        if (!s.isEmpty()) {
            System.out.println(s);
        }
    }

    void showInventory() {
        showStr(player.inventory());
    }

    String lookAtObject(NounPhrases np) {
        return player.lookAt(np);
    }

    String lookInObject(NounPhrases np) {
        return player.lookIn(np);
    }

    public void showIntro() {
        showStr(GameSetup.introText());
    }

    public String runCommand(String inputstr) {
        String s;
        String lowstr;

        s = "ok";
        lowstr = inputstr.trim().toLowerCase();

        if (!lowstr.equals("q")) {
            if (lowstr.equals("")) {
                s = "You must enter a command";
            } else {
                s = SyntacticAnalyzer.runCommand(inputstr);
            }
        }
        return s;
    }

    void showTest(String s) {
        showStr("> " + s);
        showStr(runCommand(s));
    }

       void showWeight(int m) {
        showStr("Weight=" + m);
    }

    
}
