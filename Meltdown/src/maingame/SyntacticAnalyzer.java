package maingame;

import static maingame.Meltdown.game;
import static maingame.init.RecognisableWords.vocab;
import maingame.vocab.TypeAndWord;
import maingame.vocab.GrammaticalUnits;
import maingame.vocab.NounPhrases;
import maingame.vocab.Prepositions;
import maingame.vocab.LineAnalyzer;
import maingame.vocab.Verbs;
import miscellaneous.WordTypes;
import java.util.ArrayList;
import java.util.List;

public class SyntacticAnalyzer implements java.io.Serializable {

    private static String last_input; 
    
    static String processVerbNounPhrasePrepositionNounPhrase(List<GrammaticalUnits> command) {
        String msg = "";
        GrammaticalUnits gu1 = command.get(0);
        GrammaticalUnits gu2 = command.get(1);
        GrammaticalUnits gu3 = command.get(2);
        GrammaticalUnits gu4 = command.get(3);
        String verb_word = gu1.getWord();
        String noun_word = gu2.getWord();
        String preposition_word = gu3.getWord();
        String noun_word2 = gu4.getWord();

        Verbs v = null;
        Prepositions prep = null;
        NounPhrases np = null;
        NounPhrases np2 = null;

        if (gu1 instanceof Verbs) {
            v = (Verbs) gu1;
        }
        if (gu2 instanceof NounPhrases) {
            np = (NounPhrases) gu2;
        }
        if (gu3 instanceof Prepositions) {
            prep = (Prepositions) gu3;
        }
        if (gu4 instanceof NounPhrases) {
            np2 = (NounPhrases) gu4;
        }

        if ((v == null) || (prep == null)) {
            msg = "Can't do this because I don't understand how to '" + verb_word + " something " + preposition_word + "' something!";
        } else if (np == null) {
            msg = "Can't do this because '" + noun_word + "' is not an object!\r\n";
        } else if (np2 == null) {
            msg = "Can't do this because '" + noun_word2 + "' is not an object!\r\n";
        } else {
            switch (verb_word + preposition_word) {
                case "putin":
                case "putinto":
                    msg = game.putObjectInContainer(np, np2);
                    break;
                case "openwith":
                    msg = game.openObjectWithSomething(np, np2);
                    break;
                case "lockwith":
                    msg = game.lockObjectWithSomething(np, np2);
                    break;
                case "unlockwith":
                    msg = game.unlockObjectWithSomething(np, np2);
                    break;
                default:
                    msg = "I don't know how to " + verb_word + " " + noun_word + " " + preposition_word + " " + noun_word2 + "!";
                    break;
            }
        }
        return msg;
    }

    static String processVerbPrepositionNounPhrase(List<GrammaticalUnits> command) {
        String msg = "";
        GrammaticalUnits gu1 = command.get(0);
        GrammaticalUnits gu2 = command.get(1);
        GrammaticalUnits gu3 = command.get(2);
        String verb_word = gu1.getWord();
        String preposition_word = gu2.getWord();
        String noun_word = gu3.getWord();
        Verbs v = null;
        Prepositions prep = null;
        NounPhrases np = null;

        if (gu1 instanceof Verbs) {
            v = (Verbs) gu1;
        }
        if (gu2 instanceof Prepositions) {
            prep = (Prepositions) gu2;
        }
        if (gu3 instanceof NounPhrases) {
            np = (NounPhrases) gu3;
        }

        if ((v == null) || (prep == null)) {
            msg = "Can't do this because I don't understand '" + last_input + "' !";
        } else if (np == null) {
            msg = "Can't do this because '" + noun_word + "' is not an object!\r\n";
        } else {
            switch (verb_word + preposition_word) {
                case "lookat":
                    msg = Meltdown.game.lookAtObject(np);
                    break;
                case "lookin":
                case "lookinto":
                    msg = Meltdown.game.lookInObject(np);
                    break;
                default:
                    msg = "I don't know how to " + verb_word + " " + preposition_word + " " + noun_word + "!";
                    break;
            }
        }
        return msg;
    }

    static String processVerbNounPhrase(List<GrammaticalUnits> command) {
        String msg = "";
        GrammaticalUnits gu1 = command.get(0);
        GrammaticalUnits gu2 = command.get(1);
        String verb_word = gu1.getWord();
        String noun_word = gu2.getWord();
        Verbs v = null;
        NounPhrases np = null;

        if (gu1 instanceof Verbs) {
            v = (Verbs) gu1;
        }
        if (gu2 instanceof NounPhrases) {
            np = (NounPhrases) gu2;
        }
        if (v == null) {
            msg = "Can't do this because '" + verb_word + "' is not a command!";
        } else if (np == null) {
            msg = "Can't do this because '" + noun_word + "' is not an object!";
        } else {
            switch (verb_word) {
                case "take":
                case "get":
                    msg = game.takeObject(np);
                    break;
                case "drop":
                    msg = game.dropObject(np);
                    break;
                case "open":
                    msg = game.openObject(np);
                    break;
                case "close":
                    msg = game.closeObject(np);
                    break;
                case "lock":
                    msg = game.lockObject(np);
                    break;
                case "unlock":
                    msg = game.unlockObject(np);
                    break;
                default:
                    msg = verb_word + " (not implemented)";
                    break;
            }
        }
        return msg;
    }

    static String processVerb(List<GrammaticalUnits> command) {
        String msg = "";
        GrammaticalUnits gu = command.get(0);
        String word = gu.getWord();
        Verbs v = null;

        if (gu instanceof Verbs) {
            v = (Verbs) gu;
        }
        if (v == null) {
            msg = "Can't do this because '" + word + "' is not a command!";
        } else {
            switch (word) {
                case "n":
                    game.goN();
                    break;
                case "s":
                    game.goS();
                    break;
                case "w":
                    game.goW();
                    break;
                case "e":
                    game.goE();
                    break;
                case "u":
                case "up":
                    game.goUp();
                    break;
                case "d":
                case "down":
                    game.goDown();
                    break;
                case "north":
                    game.goN();
                    break;
                     case "south":
                    game.goS();
                    break;
                     case "west":
                    game.goW();
                    break;
                     case "east":
                    game.goE();
                    break;
                case "l":
                case "look":
                    game.look();
                    break;
                case "inventory":
                case "i":
                    game.showInventory();
                    break;
                    default:
                    msg = word + " (not implemented)";
                    break;
            }
        }
        return msg;
    }

    static String processCommand(List<TypeAndWord> command) {
        String s = "";
        LineAnalyzer analyzer;
        analyzer = new LineAnalyzer(command); 
              
        List<GrammaticalUnits> grammarunits = new ArrayList<>(); 
        grammarunits = analyzer.analyze();
        
        if (grammarunits.isEmpty()) {
            s = "You must write a command!";
        } else if (grammarunits.size() > 4) {
            s = "That command is too long!";
        } else if (analyzer.containsError()) {
            s = "Cannot understand that command - " + analyzer.getError();
        } else {
            switch (grammarunits.size()) {
                case 1:
                    s = processVerb(grammarunits);
                    break;
                case 2:
                    s = processVerbNounPhrase(grammarunits);
                    break;
                case 3:
                    s = processVerbPrepositionNounPhrase(grammarunits);
                    break;
                case 4:
                    s = processVerbNounPhrasePrepositionNounPhrase(grammarunits);
                    break;
                default:
                    s = "Unable to process command";
                    break;
            }
        }
        return s;
    }

    static List<TypeAndWord> parseCommand(List<String> wordlist) {
        List<TypeAndWord> command = new ArrayList<>();
        WordTypes wordtype;

        for (String k : wordlist) {
            if (vocab.containsKey(k)) {
                wordtype = vocab.get(k);
                if (wordtype == WordTypes.ARTICLE) {          
                } else {
                    command.add(new TypeAndWord(k, wordtype));
                }
            } else {
                command.add(new TypeAndWord(k, WordTypes.ERROR));
            }
        }
        return command;
    }

    static private String parseErrors(List<TypeAndWord> command) {
        String s = "";

        for (TypeAndWord wt : command) {
            if (wt.getWordtype() == WordTypes.ERROR) {
                s += wt.getWord() + " not understood\n";
            }
        }
        return s;
    }

    static List<String> wordList(String input) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> strlist = new ArrayList<>();
        String[] words = input.split(delims);

        for (String word : words) {
            strlist.add(word);
        }
        return strlist;
    }

    static String runCommand(String input) {
        List<String> words;
        String s;
        String lowstr;
        List<TypeAndWord> command;

        s = "ok";
        lowstr = input.trim().toLowerCase();
        if (!lowstr.equals("q")) {
            if (lowstr.equals("")) {
                s = "You must enter a command";
            } else {
                last_input = input; 
                words = wordList(lowstr);
                command = parseCommand(words);
                s = parseErrors(command);
                if (s.isEmpty()) {
                    s = processCommand(command);
                } else {
                    s = "Invalid command '" + input + "'\n" + s;
                }
            }
        }
        return s;
    }
}
