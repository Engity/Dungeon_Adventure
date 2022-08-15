/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Text based GUI for combat
 * {@code @author:} Toan Nguyen
 * @version 08 13 2022
 */
@SuppressWarnings("GrazieInspection")
public class TextBasedGUI_CombatView {
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

    private static final TextBasedGUI_CombatView myCombatViewInstance = new TextBasedGUI_CombatView();

    public static TextBasedGUI_CombatView getInstance(){
        return myCombatViewInstance;
    }


    private TextBasedGUI_CombatView(){

    }

    /**
     * Prompt the user for what to do in the fight
     * @param theMessage containing status of the fight like what happen in the last turn
     * @return the user choice
     */
    int promptUserForFightAction(String theMessage, final Hero theHero, final Monster theMonster){
        InputChecker fightInputChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);

        //Get the player's stat
        ArrayList<StringBuilder> playerStat = CombatController.getInstance().parseDungeonCharacter(theHero);
        //Get the monster's stat
        ArrayList<StringBuilder> monsterStat = CombatController.getInstance().parseDungeonCharacter(theMonster);

        //Get the height of the string
        int myRepeatingPromptHeight = Math.max(playerStat.size(), monsterStat.size());

        //Parse the decoration
        String decorationStB = """
                                     __
                                   \\   /
                                     \\\\
                                       \\\\
                     ___________________||/\\_
                    (___________________()| _||||||||||||||||||||||||||||||||||||||||||>
                                        ||\\/
                                       //
                                     //
                                   /__\\
                """;
        String[] decorationStr = decorationStB.split("\n");
        StringBuilder[] repeatingPromptDecoration = new StringBuilder [decorationStr.length];
        int decorationStrWidth = 0;
        for (String s : decorationStr) {
            decorationStrWidth = Math.max(s.length() + 3, decorationStrWidth);
        }

        //Attach it to the string builder
        for (int i = 0 ; i < decorationStr.length;i++) {
            repeatingPromptDecoration[i] = new StringBuilder();
            repeatingPromptDecoration[i].append(decorationStr[i]);
            repeatingPromptDecoration[i].append(" ".repeat(decorationStrWidth - decorationStr[i].length()));

        }

        myRepeatingPromptHeight = Math.max(myRepeatingPromptHeight, repeatingPromptDecoration.length);

        StringBuilder myRepeatingPrompt = new StringBuilder("""
                     _______  __    _______  __    __  .___________. __
                    |   ____||  |  /  _____||  |  |  | |           ||  |
                    |  |__   |  | |  |  __  |  |__|  | `---|  |----`|  |
                    |   __|  |  | |  | |_ | |   __   |     |  |     |  |
                    |  |     |  | |  |__| | |  |  |  |     |  |     |__|
                    |__|     |__|  \\______| |__|  |__|     |__|     (__)
                """);
        //Attach the prompts together
        for (int i = 0; i < myRepeatingPromptHeight; i++){
            myRepeatingPrompt.append("\n");
            if (i < playerStat.size())
                myRepeatingPrompt.append(playerStat.get(i));
            else{
                if (playerStat.size() > 0)
                    myRepeatingPrompt.append(" ".repeat(playerStat.get(0).length()));
            }
            if (i < repeatingPromptDecoration.length)
                myRepeatingPrompt.append(repeatingPromptDecoration[i]);
            if (i < monsterStat.size())
                myRepeatingPrompt.append(monsterStat.get(i));
            else{
                myRepeatingPrompt.append(" ".repeat(monsterStat.get(0).length()));
            }
        }
        //outputting out the notification
        if (theMessage != null)
            myRepeatingPrompt.append("\n\nNotification: ").append(theMessage);

        myRepeatingPrompt.append("\nPlease enter your choice: ");

        String [] optionName = {"Attack", "Special attack (Will fail if mana is not 100)", "Use Potion"};

        fightInputChecker.setMyRepeatingPrompt(myRepeatingPrompt.toString());

        fightInputChecker.setMyWrongRangePrompt("There is no the option corresponding to the index you just inputted, please try again");
        fightInputChecker.setMyErrorPrompt("Wrong format, please input numbers only!");

        return fightInputChecker.inputCheckForNumber(optionName);
    }

    /**
     * Display the notification for the last turn, either hero win or lose
     * @param theMessage containing previous turn message
     * @param theHeroWin whether the hero win or not
     * @return Acknowledgement from the player
     */
    boolean displayNotificationForTheLastCombatTurn(final String theMessage, final boolean theHeroWin){
        InputChecker lastTurnNotificationChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);
        StringBuilder myRepeatingPrompt = new StringBuilder().append("\nNotification: ").append(theMessage);

        if (theHeroWin){
            myRepeatingPrompt.append("\nYou have defeated the monster.\n");
        }
        else{
            myRepeatingPrompt.append("\nThe monster has defeated you\n");
        }

        myRepeatingPrompt.append("Press Enter key to continue.");

        lastTurnNotificationChecker.setMyRepeatingPrompt(myRepeatingPrompt.toString());
        return lastTurnNotificationChecker.inputAnyKeyToContinue();
    }

    /**
     * Display the pre-fight menu to let user save the game or choose not to fight
     * @param theMonsterName containing the monster's stat
     * @return the user's choice of action
     */

    int displayPreFightMenu(final String theMonsterName){
        InputChecker preFightMenuChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);
        int userChoice = 0;
        StringBuilder repeatingPrompt = new StringBuilder("You have encounter a ").append(theMonsterName);
        repeatingPrompt.append("\nGet ready to fight!");

        String [] optionName = {"Ready!", "Save the game", "Flee"};

        preFightMenuChecker.setMyRepeatingPrompt(repeatingPrompt.toString());

        userChoice = preFightMenuChecker.inputCheckForNumber(optionName);

        return userChoice;
    }

    /**
     * Display the post-fight notification showing what loot the player has gained
     * @param theLoot containing the room's loot
     * @return whether the player's choose to continue or not
     */

    boolean displayPostFightMenu(ArrayList<Object> theLoot){
        InputChecker postFightMenuChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);

        StringBuilder repeatingPrompt = new StringBuilder("""
                 ____   ____  _____   ______  _________    ___   _______     _____   ___   _____  _____   ______  \s
                |_  _| |_  _||_   _|.' ___  ||  _   _  | .'   `.|_   __ \\   |_   _|.'   `.|_   _||_   _|.' ____ \\ \s
                  \\ \\   / /    | | / .'   \\_||_/ | | \\_|/  .-.  \\ | |__) |    | | /  .-.  \\ | |    | |  | (___ \\_|\s
                   \\ \\ / /     | | | |           | |    | |   | | |  __ /     | | | |   | | | '    ' |   _.____`. \s
                    \\ ' /     _| |_\\ `.___.'\\   _| |_   \\  `-'  /_| |  \\ \\_  _| |_\\  `-'  /  \\ \\__/ /   | \\____) |\s
                     \\_/     |_____|`.____ .'  |_____|   `.___.'|____| |___||_____|`.___.'    `.__.'     \\______.'
                """);

        if (theLoot.isEmpty()){
            repeatingPrompt.append("There is nothing worthy to pick up.\n");
        } else {
            //Loot to get every object
            for (var item : theLoot) {
                //If it is pillar
                if (item.getClass() == Pillar.class) {
                    repeatingPrompt.append("You have obtained the pillar ").append(((Pillar) item).getMyItemName());
                    repeatingPrompt.append("\n");
                }
            }
        }

        repeatingPrompt.append("Press Enter key to continue.");

        postFightMenuChecker.setMyRepeatingPrompt(repeatingPrompt.toString());
        return postFightMenuChecker.inputAnyKeyToContinue();
    }
}
