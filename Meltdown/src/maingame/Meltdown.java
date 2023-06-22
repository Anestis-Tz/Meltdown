package maingame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Meltdown {
    
     static String fileextension = "txt";
     static InGameCommands game;
     static BufferedReader in;

    // read input from prompt - return as trimmed string
    public static String getUserInput() {
        String s = "";
        try {
            s = in.readLine().trim();
        } catch (IOException ex) {
            System.out.print(ex.getClass() + ": " + ex.getMessage());
        }
        return s;
    }

       public static boolean overwriteFile(String fn) {
        boolean ok;
        String s;

        System.out.print(fn + " exists. Overwrite (Y/N)? ");
        s = getUserInput().toLowerCase();
        ok = (s.equals("yes")) || (s.equals("y"));
        return ok;
    }

    public static boolean fileExist(String fn) {
        boolean exists;
        File fil;
        fil = new File(fn);
        exists = fil.exists();
        return exists;
    }

    public static String getFileExtension(String fn) {
        String ext = "";

        if (fn.contains(".") && fn.lastIndexOf(".") != 0) {
            ext = fn.substring(fn.lastIndexOf(".") + 1);
        }
        return ext;
    }

    private static String getFileName() {
        String filename = "";
        String ext;

        System.out.print("Enter file name: ");
        filename = getUserInput();
        if (!filename.isEmpty()) {
            ext = getFileExtension(filename);
            if (!ext.equals(fileextension)) {
                System.out.println("Error: File must have an ." +fileextension + " extension");
                filename = "";
            }
        }
        return filename;
    }

    // Save
    private static void saveGame() {
        String filename;
        boolean Save;

        Save = true;
        filename = getFileName();
        if (filename.isEmpty()) {
            Save = false;
        } else if (fileExist(filename)) {
            if (!overwriteFile(filename)) {
                Save = false;
            }
        }
        if (Save) {
            try {
                FileOutputStream file_os = new FileOutputStream(filename);
                ObjectOutputStream object_os = new ObjectOutputStream(file_os);
                object_os.writeObject(game); // game
                object_os.flush(); 
                object_os.close();
                System.out.print("Game Saved Successfully \n");
            } catch (Exception e) {
                System.out.print("Serialization Error! Can't save data.\n"
                        + e.getClass() + ": " + e.getMessage());
            }
        } else {
            System.out.println("Error: File couldn't be saved");
        }
    }

    // Load
    private static void loadGame() {
        String filename;
        filename = getFileName();
        if (filename.isEmpty()) {
            System.out.println("Error: File Load Failed");
        } else {
            try {
                FileInputStream file_is = new FileInputStream(filename);
                ObjectInputStream object_is = new ObjectInputStream(file_is);
                game = (InGameCommands) object_is.readObject();
                object_is.close();
                System.out.print("\n---Game Successfuly Loaded---\n");
            } catch (Exception e) {
                System.out.print(" Can't load data.\n");
                System.out.print(e.getClass() + ": " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String input;
        String output = "";

        game = new InGameCommands();
        in = new BufferedReader(new InputStreamReader(System.in));
        game.showIntro();      
        do {
            System.out.print("> ");            
            input = getUserInput().toLowerCase();
            switch (input) {
                case "save game":
                    output = "";
                    saveGame();
                    break;
                case "load game":
                    output = "";
                    loadGame();
                    break;
                default:
                    output = game.runCommand(input);
                    break;
            }
            if (!output.trim().isEmpty()) {
                game.showStr(output);
            }
        } while (!"q".equals(input));
    }

}
