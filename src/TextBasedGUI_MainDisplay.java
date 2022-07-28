/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * Text based GUI for general functions like displaying main menu
 * {@code @author:} Toan Nguyen
 * @version 07 17 2022
 */
public class TextBasedGUI_MainDisplay {
    /**
     * The source to input from.
     * Default is System.in
     */
    private static final Scanner INPUT_SCANNER = new Scanner (System.in);

    //Singleton design

    private static TextBasedGUI_MainDisplay myMainDisplayInstance = new TextBasedGUI_MainDisplay();
    private DungeonAdventure myGameController;
    private TextBasedGUI_MainDisplay(){
    }

    public void attachController(final DungeonAdventure theGameController){
        myGameController = theGameController;

    }

    public static TextBasedGUI_MainDisplay getInstance(){
        return myMainDisplayInstance;
    }

    /**
     * Displaying the whole menu including function
     * 1. Start a new game {@link TextBasedGUI_MainDisplay#startNewGame()}
     * 2. Load a save game {@link TextBasedGUI_MainDisplay#loadGame()}
     * 3. Exit
     */
    public void displayMainMenu(){
        System.out.println("""
                ______                                       ___      _                 _
                |  _  \\                                     / _ \\    | |               | |
                | | | |_   _ _ __   __ _  ___  ___  _ __   / /_\\ \\ __| __   _____ _ __ | |_ _   _ _ __ ___
                | | | | | | | '_ \\ / _` |/ _ \\/ _ \\| '_ \\  |  _  |/ _` \\ \\ / / _ | '_ \\| __| | | | '__/ _ \\
                | |/ /| |_| | | | | (_| |  __| (_) | | | | | | | | (_| |\\ V |  __| | | | |_| |_| | | |  __/
                |___/  \\__,_|_| |_|\\__, |\\___|\\___/|_| |_| \\_| |_/\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|
                                    __/ |
                                   |___/
                """
        );
        System.out.println("By Group 8, T CSS 360 A: Toan Nguyen, Thao Nguyen, Justin Noel");
        System.out.println("Please select using your keyboard: ");
        System.out.println("""
                1. New Game
                2. Load Game
                3. Exit"""
        );

        String choice = INPUT_SCANNER.next();
        switch (choice) {
            case "1" -> startNewGame();
            case "2" -> loadGame();
            case "3" -> {
                System.out.println("Exit now!");
                return;
            }
        }

        System.out.println("\n***END EXAMPLE for GUI Text based***");
    }

    /**
     * Start a new game
     * Prompt the user for hero's name and class choice
     * Can update to contain input check
     * Can update to have further character customization
     * Updated class choice verification
     */
    private void startNewGame() {
        System.out.println("Please input your character name");
        String nameCharacter = INPUT_SCANNER.next();
        int classChoice = 1;
        String className;
        while (true){
            System.out.println("""
                        Please choose your class (by inputting 1, 2 or 3):
                        \t1. Warrior (Default class)
                        \t2. Priestess
                        \t3. Thief"""
            );

            String promptAnswer = INPUT_SCANNER.next();

            //Blank answer provided, will go for default class
            if (promptAnswer.isBlank()){
                break;
            }

            //Input check
            try {
                classChoice = Integer.parseInt(promptAnswer);
                //Checking whether the answer is within range
                if (classChoice < 0 || classChoice > 3){
                    System.out.println("Incorrect format, please input again");
                }
                else{
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please provide your answer in number. Please input again!");
            }
        }

        //Calling dungeon adventure to create hero with these specs
        //nameCharacter and classChoice
        className = "";
        switch (classChoice) {
            case 1 -> className = "mighty warrior";
            case 2 -> className = "holy priestess";
            case 3 -> className = "notorious thief";
        }

        System.out.println("Welcome, " + nameCharacter + ", the " + className + ", to Dungeon Adventure");

        //Just a test to see if anyone read the code
        //Delete it  when merge to main branch
        if (classChoice == 3){
            System.out.println("Wait, where are my healing potions I left here earlier?");

            try {
                sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Oh ... you have gone to the next stage, okay ...");

        }

        //Starting new game here

        myGameController.createANewGame(nameCharacter, classChoice);
    }

    /**
     * Currently is just a placeholder
     * loadGame helper
     * Load a specific save game
     * Potential way to implementation: Call Dungeon adventure constructor to create a new state of the game with state in the save file
     * Potential update: Use LZW or Huffman encoder\decoder to secure save files, discouraging cheating via file editing
     * @param theSaveGame Contains the save game to load
     */
    private void loadASaveGame(final File theSaveGame){
        System.out.println("Loading save file " + theSaveGame.getName());
        System.out.println();//Add extra line for readability

        //Scanner use to read save file
        Scanner saveFileReader;

        //Reading data
        try {
            saveFileReader = new Scanner (theSaveGame);

            //Assuming the first line of the save game is always the character name
            String characterName = saveFileReader.nextLine();

            System.out.println("Welcome back great hero, " + characterName);
            //Call Dungeon adventure constructor to create a new state of the game with state in the save file


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Currently is just a placeholder
     * Load a save game
     * List available save game with character name + create date or playtime
     */
    private void loadGame(){
        //Create date reference
        //https://stackoverflow.com/questions/2723838/determine-file-creation-date-in-java

        System.out.println("PLease select what save game you would like to load");

        //Creating a File object for directory
        File directorySaves = new File(System.getProperty("user.dir") +"\\save\\");

        //List of all files and directories
        String[] saveGameList = directorySaves.list();
        //Check whether the save directory is empty or not
        if (saveGameList == null){
            System.out.println("There is no save game to load");
        }
        else {
            int saveGameChoice = 0;
            while (true) {
                for (int saveGameOrder = 0; saveGameOrder < saveGameList.length; saveGameOrder++) {
                    System.out.println(saveGameOrder + ". " + saveGameList[saveGameOrder]);
                }

                //Getting the answer from console
                String promptAnswer = INPUT_SCANNER.next();


                //Input check
                try {
                    saveGameChoice = Integer.parseInt(promptAnswer);
                    //Checking whether the answer is within range
                    if (saveGameChoice < 0 || saveGameChoice >= saveGameList.length) {
                        System.out.println("There is no save game with that index, please try again");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please provide your answer in number!");
                }
            }

            File saveFile = new File(directorySaves.getAbsolutePath() + "\\" + saveGameList[saveGameChoice]);
            loadASaveGame(saveFile);
        }
    }

    //Sword art used for (The biggest width is 68 chars)
    //                __
    //               \  /
    //                 \\
    //                   \\
    // ___________________||/\_
    //(___________________()| _||||||||||||||||||||||||||||||||||||||||||>
    //                    ||\/
    //                   //
    //                 //
    //               /__\
}