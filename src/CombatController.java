/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Keep the current status in a fight
 * Control the player and monster during the fight
 * {@code @author:} Toan Nguyen
 * @version 08 09 2022
 */
public class CombatController {
    private final TextBasedGUI_CombatView myCombatView;
    private int myCurrentTurnAmount;

    private static DungeonAdventure myGameController;

    static void attachController(final DungeonAdventure theGameController){
        myGameController = theGameController;
    }
    private CombatController(){
        myCurrentTurnAmount = 0;
        myCombatView = TextBasedGUI_CombatView.getInstance();
    }

    private static final CombatController myCombatControllerInstance = new CombatController();

    public static CombatController getInstance(){
        return myCombatControllerInstance;
    }

    /**
     * Initiate the fight
     * Take in 2 dungeon characters, if one of them is hero, will ask the player for control
     */
    //Need other classes
    int initiateFight(final Room theRoom){
        int userChoice = myCombatView.displayPreFightMenu(theRoom.getMyGuardian().getMyCharacterName());
        switch (userChoice) {
            case (0) -> {
                int fightResult = fighting(theRoom.getMyGuardian());
                switch (fightResult){
                    case 0 -> {
                        //Return 0 to let DungeonAdventure know that the player lost the fight and died
                        return 0;
                    }
                    case 1 -> {
                        //Win the fight get the loot
                        var loot = theRoom.retrieveLoot();

                        //Return 2 to let DungeonAdventure know that the player win the fight
                        return 2;
                    }
                }
            }
            case (1) -> TextBasedGUI_MainDisplay.getInstance().saveGame();
            case (2) -> {
                //return 3 to tell DungeonAdventure to flee
                return 3;
            }
        }

        //If the player has died return a different value to let the DungeonAdventure it is game over

        return 0;
    }

    /**
     * Start the fighting
     * @param theMonster the monster the player supposed to fight
     */

    int fighting(final Monster theMonster){
        myCombatView.promptUserForFightAction(null, theMonster);
//        while (!theMonster.isDeath()){
//
//        }

        if (theMonster.isDead())
            return 1;
        return 0;
    }

    /**
     * Display a character's stat but in rectangle forms
     * param a dungeon character
     * Return a list of string with equal length
     */

    ArrayList<StringBuilder> parseDungeonCharacter(final DungeonCharacter theDungeonCharacter){
        ArrayList<StringBuilder> res = new ArrayList<>();

        if (theDungeonCharacter == null){
            return res;
        }

        int longestWidth = 0;

        String characterToSTR = theDungeonCharacter.toString();

        Scanner scanline = new Scanner(characterToSTR);
        
        while (scanline.hasNext()){
            //Init
            StringBuilder line = new StringBuilder();
            //Add to the string
            line.append(scanline.nextLine());
            //Increase the number of rows
            longestWidth = Math.max(longestWidth, line.length());
            res.add(line);
        }

        //Add space to make every string has equal length
        for (StringBuilder re : res) {
            re.append(" ".repeat(longestWidth - re.length()));
        }


        return res;
    }

}
