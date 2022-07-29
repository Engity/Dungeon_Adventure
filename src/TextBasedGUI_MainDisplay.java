/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * Text based GUI for general functions like displaying main menu
 * {@code @author:} Toan Nguyen
 * @version 07 29 2022
 */
public class TextBasedGUI_MainDisplay {
    /**
     * The source to input from.
     * Default is System.in
     */
    private static final InputStream INPUT_SOURCE = System.in;

    /**
     * The destination to output to.
     * Default is System.out
     */
    private static final PrintStream OUTPUT_DESTINATION = System.out;

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
        InputChecker mainMenuChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);
        mainMenuChecker.setMyDefaultChoice(1);
        mainMenuChecker.setBound(1,3);

        mainMenuChecker.setMyInitialPrompt("""
                ______                                       ___      _                 _
                |  _  \\                                     / _ \\    | |               | |
                | | | |_   _ _ __   __ _  ___  ___  _ __   / /_\\ \\ __| __   _____ _ __ | |_ _   _ _ __ ___
                | | | | | | | '_ \\ / _` |/ _ \\/ _ \\| '_ \\  |  _  |/ _` \\ \\ / / _ | '_ \\| __| | | | '__/ _ \\
                | |/ /| |_| | | | | (_| |  __| (_) | | | | | | | | (_| |\\ V |  __| | | | |_| |_| | | |  __/
                |___/  \\__,_|_| |_|\\__, |\\___|\\___/|_| |_| \\_| |_/\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|
                                    __/ |
                                   |___/
                By Group 8, T CSS 360 A: Toan Nguyen, Thao Nguyen, Justin Noel, Salahuddin Majed
                """
        );

        mainMenuChecker.setMyRepeatingPrompt("""
                        Please select using your keyboard: 
                        \t1. New Game
                        \t2. Load Game
                        \t3. Exit"""
        );

        mainMenuChecker.setMyWrongRangePrompt("Incorrect format or range, please input again");
        mainMenuChecker.setMyErrorPrompt("Please provide your answer in number. Please input again!");

        int userChoice = mainMenuChecker.inputCheckForNumber();
        switch (userChoice) {
            case 1 -> startNewGame();
            case 2 -> loadGame();
            case 3 -> {
                OUTPUT_DESTINATION.println("Exit now!");
                System.exit(0);
            }
        }
    }



    /**
     * Start a new game
     * Prompt the user for hero's name and class choice
     * Can update to contain input check
     * Can update to have further character customization
     * Updated class choice verification
     */
    private void startNewGame() {
        OUTPUT_DESTINATION.println("Please input your character name");
        String nameCharacter = INPUT_SCANNER.next();

        int classChoice;
        String className;

        InputChecker classChoiceChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);
        classChoiceChecker.setMyDefaultChoice(1);

        classChoiceChecker.setBound(1,3);
        classChoiceChecker.setMyRepeatingPrompt("""
                        Please choose your class (by inputting 1, 2 or 3):
                        \t1. Warrior (Default class)
                        \t2. Priestess
                        \t3. Thief"""
        );

        //classChoiceChecker.setMyErrorPrompt("Please provide your answer in number. Please input again!");
        classChoice = classChoiceChecker.inputCheckForNumber();

        //Calling dungeon adventure to create hero with these specs
        //nameCharacter and classChoice
        className = "";
        switch (classChoice) {
            case 1 -> className = "mighty warrior";
            case 2 -> className = "holy priestess";
            case 3 -> className = "notorious thief";
        }

        OUTPUT_DESTINATION.println("Welcome, " + nameCharacter + ", the " + className + ", to Dungeon Adventure");

        //Just a test to see if anyone read the code and question this
        if (classChoice == 3){
            OUTPUT_DESTINATION.println("Wait, where are my healing potions I left here earlier?");

            try {
                sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            OUTPUT_DESTINATION.println("Oh ... you have gone to the next stage, okay ...");

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
        OUTPUT_DESTINATION.println("Loading save file " + theSaveGame.getName());
        OUTPUT_DESTINATION.println();//Add extra line for readability

        //Scanner use to read save file
        Scanner saveFileReader;

        //Reading data
        try {
            saveFileReader = new Scanner (theSaveGame);

            //Assuming the first line of the save game is always the character name
            String characterName = saveFileReader.nextLine();

            OUTPUT_DESTINATION.println("Welcome back great hero, " + characterName);
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
        //Creating a File object for directory
        File directorySaves = new File(System.getProperty("user.dir") +"\\save\\");

        //List of all files and directories
        String[] saveGameList = directorySaves.list();
        //Check whether the save directory is empty or not
        if (saveGameList == null){
            OUTPUT_DESTINATION.println("There is no save game to load");
        }
        else {
            int saveGameChoice = 0;

            StringBuilder saveGameListString = new StringBuilder("PLease select what save game you would like to load");
            for (int saveGameOrder = 0; saveGameOrder < saveGameList.length; saveGameOrder++) {
                saveGameListString.append("\n");
                saveGameListString.append(saveGameOrder + ". " + saveGameList[saveGameOrder]);
            }

            InputChecker saveGameChoiceChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);
            saveGameChoiceChecker.setMyRepeatingPrompt(saveGameListString.toString());
            saveGameChoiceChecker.setMyWrongRangePrompt("There is no save game with that index, please try again");
            saveGameChoiceChecker.setBound(0, saveGameList.length - 1);
            saveGameChoiceChecker.setMyErrorPrompt("Please provide your answer in number!");

            saveGameChoice = saveGameChoiceChecker.inputCheckForNumber();

            File saveFile = new File(directorySaves.getAbsolutePath() + "\\" + saveGameList[saveGameChoice]);
            loadASaveGame(saveFile);
        }
    }


}
