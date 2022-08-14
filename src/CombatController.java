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
    int initiateFight(final DungeonCharacter theHero, final Room theRoom){
        int userChoice = myCombatView.displayPreFightMenu(theRoom.getMyGuardian().getMyCharacterName());
        switch (userChoice) {
            case (0) -> {
                int fightResult = fighting(theHero, theRoom.getMyGuardian());
                switch (fightResult){
                    case 0 -> {
                        //Return 0 to let DungeonAdventure know that the player lost the fight and died
                        return 0;
                    }
                    case 1 -> {
                        //Win the fight get the loot
                        var loot = theRoom.retrieveLoot();
                        boolean tmp = TextBasedGUI_CombatView.getInstance().displayPostFightMenu(loot);
                        //Add the loot to theHero

                        //If it is buff or de-buff then use different stuffs

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

    int fighting(final DungeonCharacter theHero, final Monster theMonster){
        StringBuilder message = new StringBuilder("Turn 1\nFight!");

        int turnNumber = 1;

        while (!theMonster.isDead() && !theHero.isDead()){

            //Player's turn
            int userChoice = myCombatView.promptUserForFightAction(message.toString(), theHero, theMonster);
            message = new StringBuilder("Turn ").append(++turnNumber);

            double playerInflictDamage;
            switch (userChoice){
                //attack
                case (0)-> {
                    playerInflictDamage = theHero.normalAttackMove();
                    double playerActualInflictDamage = theMonster.applyDamage(playerInflictDamage);
                    if (playerInflictDamage != 0){
                        message.append("\nYou have inflicted ").append(String.format("%.2f", playerActualInflictDamage)).append(" damage.");
                    }
                    else{
                        message.append("\nMonster managed to block your attack");
                    }
                }
                //Special attack
                case(1)-> {
                    System.out.println("I use special attack");
                    System.out.println("It suppose to be a function but there it has not been developed so you just lost a turn, for now");
                }

                //Use potion
                case (2) ->{
                    System.out.println("I use a potion");
                    System.out.println("It suppose to be a function but there it has not been developed so you just lost a turn, for now");
                }
            }

            //Monster's turn
            //just attack for now
            double monsterInflictDamage = theMonster.normalAttackMove();
            double monsterActualInflictDamage = theHero.applyDamage(monsterInflictDamage);

            //Update notification
            //if the monster chose to attack
            if (monsterActualInflictDamage != 0){
                message.append("\nThe monster has attacked you, inflicted ").append(String.format("%.2f", monsterActualInflictDamage)).append(" damage.");
            }
            else{
                message.append("\nYou managed to block the monster's attack");
            }
        }

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