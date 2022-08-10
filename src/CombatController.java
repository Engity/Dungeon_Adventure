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
    private TextBasedGUI_CombatView myCombatView;
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
    int initiateFight(){
        int userChoice;
        while (true) {
            userChoice = myCombatView.displayPreFightMenu("Will replace this with actual monster name");
            switch (userChoice) {
                case (0) -> {
                    myCombatView.promptUserForFightAction();
                    System.out.println("You have obtained some loot but since we have not implemented game objs...");
                    return 0;
                }
                case (1) -> {
                    TextBasedGUI_MainDisplay.getInstance().saveGame();
                }
                case (2) -> {
                    return 2;
                }
            }
        }


    }

    /**
     * Display a character's stat but in rectangle forms
     * param a dungeon character
     * Return a list of string with equal length
     */

    ArrayList<StringBuilder> parseDungeonCharacter(){
        ArrayList<StringBuilder> res = new ArrayList<>();
        int k = 0, longestWidth = 0;

        //Basic example
        String characterToSTR = """
                Name: asDLK:l;asdlk;
                Health: 90
                Attack speed: 50
                Dodge chance: 20
                dsadasddsaasd
                asdasdasddddddddddddddddddddddd
                """;

        Scanner scanline = new Scanner(characterToSTR);
        
        while (scanline.hasNext()){
            //Init
            StringBuilder line = new StringBuilder();
            //Add to the string
            line.append(scanline.nextLine());
            //Increase the number of rows
            longestWidth = Math.max(longestWidth, line.length());
            res.add(line);
            ++k;
        }

        //Add space to make every string has equal length
        for (int i = 0 ; i < res.size(); i++){
            res.get(i).append(" ".repeat(longestWidth - res.get(i).length()));
        }


        return res;
    }

}
