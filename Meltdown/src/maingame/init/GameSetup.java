package maingame.init;

import maingameitems.Player;
import maingameitems.ContainerItem;
import maingameitems.NonContainerItem;
import maingameitems.GeneralItem;
import maingameitems.LockableItem;
import maingameitems.Location;
import maingameitems.Item;
import miscellaneous.Directions;
import miscellaneous.Weight;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class GameSetup implements java.io.Serializable {

    public static ArrayList<Location> map;    
    public static HashMap<String, Item> things; 
    public static Player player;  
    private static String introtext = ""; 

    public static void initGame() {

        
        // Location Creation
        Location rangerStationCharlie = new Location("Ranger Station Charlie");
        Location mojaveDesert = new Location("Mojave Desert");
        Location goodsprings = new Location("Goodsprings");
        Location mojaveOutpost = new Location("Mojave Outpost");
        Location primm = new Location("Primm");
        Location redRockCanyon = new Location("Red Rock Kanyon");
        Location fiends = new Location("Fiends");
        Location novac = new Location("Novac");
        Location boulderBeach = new Location("Boulder Beach");
        Location caesarsLegion = new Location("Caesars Legion");
        Location campMcCarran = new Location("Camp McCarran");
        Location newVegasEntrance = new Location("New Vegas Entrance");
        Location newVegas = new Location("New Vegas");

               
        // Container Creation   
        // -  Footlocker
        ContainerItem foot_locker = new ContainerItem("footlocker", "small shiny footlocker",Weight.MEDIUM);
        foot_locker.setOpenable(true);
        foot_locker.setOpen(false);
        foot_locker.addAdjectives(new String[]{"shiny", "footlocker"});
        
        //chest
        LockableItem chest = new LockableItem("chest","Chest covered in sand",Weight.BIG,false,false,true,false,false);
        chest.setMovable(false);
        chest.setOpen(true);
        chest.setShow(true);
        chest.addAdjectives(new String[]{"covered", "sand"});
        
        // -  Drawer
        ContainerItem drawer = new ContainerItem("slot", "small drawer", Weight.TINY, false, false, false, true);
        drawer.addAdjectives(new String[]{"small"});
        drawer.setOpenable(true);
        drawer.setShow(true);

        //  - Garbage Bin
        ContainerItem garbage_bin = new ContainerItem("bin", "dirty garbage bin", Weight.MEDIUM);
        garbage_bin.setOpenable(true);
        garbage_bin.setOpen(false);
        garbage_bin.setShow(true);
        garbage_bin.addAdjectives(new String[]{"dirty", "garbage"});

        //  - Vending Machine
        ContainerItem vending_machine = new ContainerItem("vending machine", "rusty vending machine", Weight.HUGE);
        vending_machine.setOpenable(true);
        vending_machine.setOpen(false);
        vending_machine.addAdjectives(new String[]{"huge", "rusty"});
        vending_machine.setLongDescription("huge sunset sarsaparilla vending machine covered in rust");

        //   - Safe (locked)
        LockableItem safe = new LockableItem("safe", "Fancy Looking Safe", Weight.BIG,false, false, true, false, true);
        safe.setShow(false);
                
        // - Cabinet
        ContainerItem cabinet = new ContainerItem("cabinet", "tall cabinet",Weight.HUGE);
        cabinet.setOpen(true);
        cabinet.setTakable(false);
        cabinet.addAdjectives(new String[]{"cabinet"});

        
        
        // Game Item Creation
        NonContainerItem desk = new NonContainerItem("desk", "wooden desk", Weight.BIG, false, false);
        desk.addAdjectives(new String[]{"wooden"});
        desk.setLongDescription("wooden desk.\n" + "There is a small drawer inside the desk");
        desk.setShow(false);

        NonContainerItem console = new NonContainerItem("console", "display console", Weight.MEDIUM, false, false);
        console.addAdjectives(new String[]{"display"});
        console.setLongDescription("display console built into the desk.\nIt is displaying this message:\n" + "Place the micro chip into slot to open the vault");
        console.setShow(false);

        NonContainerItem goldKey = new NonContainerItem("key", "gold key", Weight.TINY);
        goldKey.addAdjectives(new String[]{"small", "gold", "golden"});
        goldKey.setLongDescription("small, gold key");

        NonContainerItem silverKey = new NonContainerItem("key", "silver key", Weight.TINY);
        silverKey.setAdjectives(new ArrayList<>(Arrays.asList("small", "silver")));
        silverKey.setLongDescription("small, silver key");

        NonContainerItem microChip = new NonContainerItem("chip", "micro chip",Weight.TINY);
        microChip.addAdjectives(new String[]{"micro", "mini", "tiny"});
        microChip.setTakable(true);
        microChip.setShow(true);
        
        NonContainerItem caps = new NonContainerItem("1000 caps","one thousand bottle caps",Weight.TINY);
        caps.setTakable(true);
        caps.setShow(true);
        caps.setLongDescription("One thousand bottle caps, this should give you entrance to New Vegas");

        //Simple Characters
        
        NonContainerItem securitronmk5 = new NonContainerItem("securitronkmk5","\nThere is a heavily armored Securitron Mark 5 guarding the gate to New Vegas\n"
                + "He tells you that in order to let you into the city you need to give him 1000 bottle caps",Weight.HUGE);
        securitronmk5.setTakable(false);
        securitronmk5.addAdjectives(new String[]{"heavy","metal","mark5"});
        
        NonContainerItem villager = new NonContainerItem("villager","\nA local Villager is wandering alone \n"
                + "As he notices you looking at him, he tells you that he hasn't seen you here before\n"
                + "You remain silent as you dont want to draw any unwanted attention and he continues on to tell you\n"
                + "that there is a locked safe in the abandoned building that nobody managed to open and if you manage it,\n"
                + "it's contents are yours",Weight.HUGE);
        villager.addAdjectives(new String[]{"tall","friendly","local"});
        villager.setTakable(false);
        villager.setShow(true);
        
        
        
         // Adding NonContainerItems into ContainerItems
        foot_locker.addItem(silverKey);
        safe.addItem(caps);
        garbage_bin.addItem(silverKey);
        vending_machine.addItem(microChip);
        drawer.addItem(desk);
        chest.addItem(goldKey);

        // General Items (Items the player can only interact with by looking at them)
        GeneralItem canyon = new GeneralItem("canyon", "giant canyon with an endless river cutting through it", false);
        GeneralItem barracks = new GeneralItem("barracks", "huge spiky plants",true);
        GeneralItem legionaries = new GeneralItem("legionaries", "big army of legionaries", true);
        GeneralItem villagers = new GeneralItem("villagers","villagers be villaging",true);

        legionaries.addAdjectives(new String[]{"big", "army"});
        barracks.addAdjectives(new String[]{"old", "military"});
        canyon.addAdjectives(new String[]{"giant","endless"});
        villagers.addAdjectives(new String[]{"idk"});
     
        // 5) Adding Items to Locations            
        goodsprings.addItem(garbage_bin);
        mojaveDesert.addItem(chest);
        mojaveOutpost.addItem(barracks);
        novac.addItem(barracks);
        redRockCanyon.addItem(canyon);
        goodsprings.addItem(villager);
        goodsprings.addItem(safe);
        newVegasEntrance.addItem(securitronmk5);
        redRockCanyon.addItem(canyon);
        caesarsLegion.addItem(legionaries);
        mojaveOutpost.addItem(safe);
        mojaveOutpost.addItem(chest);
        rangerStationCharlie.addItem(drawer);
        rangerStationCharlie.addItem(vending_machine);
        
        // 6) Set any 'special' attributes
        safe.canBeUnlockedWith(goldKey);

       
               

        //Location Exit Initialization
        // Goodsprings
        goodsprings.initialize("A small town west of the mojave.",
                mojaveDesert, Directions.NOEXIT, mojaveOutpost, Directions.NOEXIT);
        // Mojave Outpost
        mojaveOutpost.initialize("A military hub for all NCR operations in the southwestern Mojave. A huge viewscreen dominates one wall",
                primm, rangerStationCharlie, Directions.NOEXIT, goodsprings, Directions.NOEXIT, Directions.NOEXIT);
        //Ranger Station Charlie
        rangerStationCharlie.initialize("An NCR ranger camp, It is located along a railroad track and pre-War powerline towers,\n southwest of Novac and east of Harper's shack.",
                mojaveDesert, Directions.NOEXIT, mojaveOutpost, Directions.NOEXIT);
        // Primm
        primm.initialize("Primm is an independent settlement in the Mojave Wasteland, overrun by raiders",
                redRockCanyon, mojaveOutpost, Directions.NOEXIT, Directions.NOEXIT, Directions.NOEXIT, Directions.NOEXIT
        );
        // Red Rock Canyon
        redRockCanyon.initialize("Red Rock Canyon is a location that serves as a refuge to the Great Khans. It is located in the\n northwest of the Mojave Wasteland, west of Chance's map.",
                fiends, primm, Directions.NOEXIT, Directions.NOEXIT, Directions.NOEXIT, Directions.NOEXIT
        );
        // Fiends
        fiends.initialize("The Fiends have little organization, except for their occupation of Vault 3, often aimlessly roaming the\n northern Mojave looking for their next chem score or an innocent wastelander to jump",
                Directions.NOEXIT, redRockCanyon, Directions.NOEXIT, Directions.NOEXIT, Directions.NOEXIT, Directions.NOEXIT
        );
        // Mojave Desert
        mojaveDesert.initialize("The Mojave Wasteland is the region synonymous with the old world Mojave Desert, spanning large \nportions of the pre-War states of California, Nevada, Utah, and Arizona",
                campMcCarran, rangerStationCharlie, goodsprings, novac, Directions.NOEXIT, Directions.NOEXIT
        );
        // Novac
        novac.initialize("Novac is an independent town in the Mojave Wasteland. It is located south of the Gibson scrap yard and \nnorth of the Viper's encampment along Highway 95",
                Directions.NOEXIT, mojaveDesert, campMcCarran, boulderBeach
        );
        // Boulder Beach
        boulderBeach.initialize("The campground consists of a few cliffside campsites looking over Lake Mead with a bed inside \na trailer and a campfire for crafting. It serves as an entrance to the Caesar's Legion",
                novac, caesarsLegion, Directions.NOEXIT, Directions.NOEXIT
        );
        // Caesars Legion
        caesarsLegion.initialize("Fortification Hill is a vast and heavily fortified base for Caesar's Legion on the east bank of the \nColorado River, which serves as Caesar's main base of operations during his war against the NCR.",
                boulderBeach, Directions.NOEXIT, Directions.NOEXIT, Directions.NOEXIT
        );
        // Camp McCarran
        campMcCarran.initialize("Camp McCarran, known before the War as McCarran International Airport, is a pre-War airport \nwhich serves as the headquarters for the New California Republic Army in the Mojave Wasteland",
                newVegasEntrance, mojaveDesert, Directions.NOEXIT, novac
        );
        // New Vegas Entrance
        newVegasEntrance.initialize("The entrance to the Strip is located in the southwest section of Freeside.\n The area in front of the gate is guarded by five PDQ-88b securitron gatekeepers, \nwhich only grant access to those who are able to pass the credit check of 2,000 caps or passport owners",
                newVegas, campMcCarran, Directions.NOEXIT, Directions.NOEXIT
        );
        // New Vegas
        newVegas.initialize("New Vegas is a city rebuilt amidst the remains of pre-War Las Vegas, Nevada. \nThe city's main attraction, the New Vegas Strip is managed by Robert House,\n who utilizes his army of Securitron robots to maintain peace and order in the post-War city in the heart of the vast Mojave Desert.",
                Directions.NOEXIT, newVegasEntrance, Directions.NOEXIT, Directions.NOEXIT
        );

        // 10) Player Creation and Setting Starting Location
        player = new Player("player", "The Courier",goodsprings);

        // 11 )Intro Text
        defineIntroText();
    }

    // Intro - add any text to be shown when game starts
    private static void defineIntroText() {
        introtext = "a Nuclear Fallout has destroyed the world, only a few people managed to survive by hiding in vaults. \n"
                + "Decades after the apocalypse the world has turned into a never ending ruin covered in sand\n"
                + "The people who survived have formed factions and crime is ruling all of the mojave \n"
                + "You are a courier that was given the task to deliver a mysterious item to the city of New Vegas  \n\n"
                + "You begin your journey in a small town called Goodsprings \n"
                + "What do you want to do?\n"
                + "(Enter q to quit the game)";
    }

    public static String introText() {
        return introtext;
    }
}
