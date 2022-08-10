/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * Text based GUI for general functions like displaying main menu
 * {@code @author:} Toan Nguyen
 * @version 08 02 2022
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

    private final static TextBasedGUI_MainDisplay myMainDisplayInstance = new TextBasedGUI_MainDisplay();

    private static DungeonAdventure myGameController;
    private TextBasedGUI_MainDisplay(){
    }

    static void attachController(final DungeonAdventure theGameController){
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
    void displayMainMenu(){
        //Without these lines, the instances won't be created
        TextBasedGUI_NavigationView.getInstance();
        TextBasedGUI_CombatView.getInstance();
        TextBasedGUI_MainDisplay.getInstance();
        DungeonAdventure.getInstance();

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
                By Group 8, T CSS 360 A: Toan Nguyen, Thao Nguyen, Justin Noel
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
            case 2 -> loadGame(true);
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

        //Reading data
        try {
            FileInputStream saveFileInput = new FileInputStream(theSaveGame);
            ObjectInputStream objectIS = new ObjectInputStream(saveFileInput);

            //Doing actual deserialization to load the game
            myGameController = (DungeonAdventure) objectIS.readObject();

            OUTPUT_DESTINATION.println("\nSuccessfully load the game\n");

            //OUTPUT_DESTINATION.println("Here is the map " + myGameController.getWorldMapFullVisibility());

        } catch (Exception e) {
            OUTPUT_DESTINATION.println(e.getMessage());
        }
    }

    /**
     * Load a save game
     * List available save game with character name + create date or playtime
     * @param theOriginFromMainMenu true if the origin is from main menu, false otherwise. To keep track whether gameLoop has initiated or not
     *
     */
    void loadGame(final boolean theOriginFromMainMenu){
        //Creating a File object for directory
        File directorySaves = new File(System.getProperty("user.dir") +"\\save\\");

        //List of all files and directories
        String[] saveGameList = directorySaves.list();
        Arrays.sort(saveGameList, Collections.reverseOrder());

        //Check whether the save directory is empty or not
        if (saveGameList == null){
            OUTPUT_DESTINATION.println("There is no save game to load");
        }
        else {
            int saveGameChoice = 0;

            StringBuilder saveGameListString = new StringBuilder("PLease select what save game you would like to load");
            for (int saveGameOrder = 0; saveGameOrder < saveGameList.length; saveGameOrder++) {
                saveGameListString.append("\n");
                saveGameListString.append(saveGameOrder).append(". ").append(saveGameList[saveGameOrder]);
            }

            InputChecker saveGameChoiceChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);
            saveGameChoiceChecker.setMyRepeatingPrompt(saveGameListString.toString());
            saveGameChoiceChecker.setMyWrongRangePrompt("There is no save game with that index, please try again");
            saveGameChoiceChecker.setBound(0, saveGameList.length - 1);
            saveGameChoiceChecker.setMyErrorPrompt("Please provide your answer in number!");

            saveGameChoice = saveGameChoiceChecker.inputCheckForNumber();

            File saveFile = new File(directorySaves.getAbsolutePath() + "\\" + saveGameList[saveGameChoice]);
            loadASaveGame(saveFile);

            if (theOriginFromMainMenu){
                DungeonAdventure.gameLoop();
            }
        }
    }

    /**
     * Save the current state of the game
     * The save name will be the current date and time so the player can distinguish which save game it is.
     */

    void saveGame(){
        try{
            //Obtain the date
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
            //Format it
            String fileName = myDateObj.format(myFormatObj);
            System.out.println("After formatting: " + fileName);

            //Creating the object
            //Creating stream and writing the object
            FileOutputStream fOut = new FileOutputStream("save\\" + fileName + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            out.writeObject(myGameController);
            out.flush();
            //closing the stream
            out.close();
            System.out.println("success");
        }
        catch(Exception e){
            OUTPUT_DESTINATION.println(e.getMessage());
        }
    }

    /**
     * Display the menu when the player has won
     */
    void displayVictoryMenu(){
        InputChecker victoryMenuChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);
        victoryMenuChecker.setMyInitialPrompt("""
                                    .-'''-.                                                    ___  \s
                                   '   _    \\                                               .'/   \\ \s
                                 /   /` '.   \\                              .--.   _..._   / /     \\\s
                 .-.          .-.   |     \\  '                       _     _|__| .'     '. | |     |\s
                  \\ \\        / /|   '      |  '                /\\    \\\\   //.--..   .-.   .| |     |\s
                   \\ \\      / / \\    \\     / /                 `\\\\  //\\\\ // |  ||  '   '  ||/`.   .'\s
                    \\ \\    / /   `.   ` ..' /_    _              \\`//  \\'/  |  ||  |   |  | `.|   | \s
                     \\ \\  / /       '-...-'`| '  / |              \\|   |/   |  ||  |   |  |  ||___| \s
                      \\ `  /               .' | .' |               '        |  ||  |   |  |  |/___/ \s
                       \\  /                /  | /  |                        |__||  |   |  |  .'.--. \s
                       / /                |   `'.  |                            |  |   |  | | |    |\s
                   |`-' /                 '   .'|  '/                           |  |   |  | \\_\\    /\s
                    '..'                   `-'  `--'                            '--'   '--'  `''--'\s
                Congratulation! You have won the game!!!!""");

        victoryMenuChecker.setMyRepeatingPrompt("Please enter your selection: ");
        String [] optionName = {"Start a new game!", "Return to main menu", "Exit"};

        victoryMenuChecker.setMyWrongRangePrompt("There is no the option corresponding to the index you just inputted, please try again");
        victoryMenuChecker.setMyErrorPrompt("Wrong format, please input numbers only!");

        int userChoice = victoryMenuChecker.inputCheckForNumber(optionName);

        switch (userChoice){
            //Creating a new game
            case (0)-> startNewGame();
            //Return to main menu
            case(1)-> TextBasedGUI_MainDisplay.getInstance().displayMainMenu();

            //Exit
            case (2) ->{
                OUTPUT_DESTINATION.println("Exiting the game!");
                System.exit(0);
            }
        }

    }

    /**
     * Display the menu when the player has died
     */
    void displayGameOverMenu(){
        InputChecker gameOverMenuChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);
        gameOverMenuChecker.setMyInitialPrompt("""
                                 ...
                               ;::::;
                             ;::::; :;
                           ;:::::'   :;
                          ;:::::;     ;.
                         ,:::::'       ;           OOO\\
                         ::::::;       ;          OOOOO\\
                         ;:::::;       ;         OOOOOOOO
                        ,;::::::;     ;'         / OOOOOOO
                      ;:::::::::`. ,,,;.        /  / DOOOOOO
                    .';:::::::::::::::::;,     /  /     DOOOO
                   ,::::::;::::::;;;;::::;,   /  /        DOOO
                  ;`::::::`'::::::;;;::::: ,#/  /          DOOO
                  :`:::::::`;::::::;;::: ;::#  /            DOOO
                  ::`:::::::`;:::::::: ;::::# /              DOO
                  `:`:::::::`;:::::: ;::::::#/               DOO
                   :::`:::::::`;; ;:::::::::##                OO
                   ::::`:::::::`;::::::::;:::#                OO
                   `:::::`::::::::::::;'`:;::#                O
                    `:::::`::::::::;' /  / `:#
                     ::::::`:::::;'  /  /   `#
                                                                                ,---.\s
                ,--.   ,--.,-----. ,--. ,--.    ,------.  ,--.,------.,------.  |   |\s
                 \\  `.'  /'  .-.  '|  | |  |    |  .-.  \\ |  ||  .---'|  .-.  \\ |  .'\s
                  '.    / |  | |  ||  | |  |    |  |  \\  :|  ||  `--, |  |  \\  :|  | \s
                    |  |  '  '-'  ''  '-'  '    |  '--'  /|  ||  `---.|  '--'  /`--' \s
                    `--'   `-----'  `-----'     `-------' `--'`------'`-------' .--. \s
                                                                                '--' \s
                """);

        gameOverMenuChecker.setMyRepeatingPrompt("Please enter your selection!");
        String [] optionName = {"Load a save game", "Return to main menu", "Exit"};

        gameOverMenuChecker.setMyWrongRangePrompt("There is no the option corresponding to the index you just inputted, please try again");
        gameOverMenuChecker.setMyErrorPrompt("Wrong format, please input numbers only!");
        gameOverMenuChecker.setBound(0, optionName.length - 1);

        int userChoice = gameOverMenuChecker.inputCheckForNumber(optionName);

        switch (userChoice){
            //Loading a new game
            case (0)-> loadGame(false);
            //Return to main menu
            case(1)-> TextBasedGUI_MainDisplay.getInstance().displayMainMenu();

            //Exit
            case (2) ->{
                OUTPUT_DESTINATION.println("Exiting the game!");
                System.exit(0);
            }
        }
    }

}