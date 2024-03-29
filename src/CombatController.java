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

    private static DungeonAdventure myGameController;

    static void attachController(final DungeonAdventure theGameController){
        myGameController = theGameController;
    }
    private CombatController(){
        int myCurrentTurnAmount = 0;
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
    int initiateFight(final Hero theHero, final Room theRoom){
        int userChoice = myCombatView.displayPreFightMenu(theRoom.getGuardian().getCharacterName());
        switch (userChoice) {
            case (0) -> {
                int fightResult = fighting(theHero, theRoom.getGuardian());
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
                        for (var item: loot){
                            //Add to the pillar loot if it is player's
                            if (item.getClass() == Pillar.class){
                                theHero.addPillarsToStorage((Pillar)item);
                            }

                            //Refresh the vision potion for the player
                            if (item.getClass() == VisionBuff.class){
                                theHero.getMyVisionBuff().refreshDuration();
                                //Tell the dungeon adventure about the buff
                                myGameController.setVisionBuffStatus(true);
                            }
                        }

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

    int fighting(final Hero theHero, final Monster theMonster){
        StringBuilder message = new StringBuilder("Turn 1\nFight!");
        int turnNumber = 1;
        while (!theMonster.isDead() && !theHero.isDead()){
            //Player's turn
            int userChoice = myCombatView.promptUserForFightAction(message.toString(), theHero, theMonster);
            message = new StringBuilder("Turn ").append(++turnNumber);
            message.append("\nYour Turn: ");
            switch (userChoice){
                //attack
                case (0)-> {
                    message.append("\n- You attacked: ");
                    double [] playerInflictDamage = theHero.normalAttackMove();
                    for (int i = 0; i < playerInflictDamage.length; ++i) {
                        double playerActualInflictDamage = theMonster.applyDamage(playerInflictDamage[i]);
                        message.append("\n\tIn strike ").append(i + 1).append(":\n");
                        if (playerActualInflictDamage != 0) {
                            message.append("\t\tYou have inflicted ").append(String.format("%.2f", playerActualInflictDamage)).append(" damage.");
                        } else {
                            message.append("\t\tMonster managed to block your attack");
                        }

                        if (theMonster.isDead()){
                            message.append("\n\tThe monster appears to be not moving anymore, so you stopped your attack.");
                            break;
                        }
                    }
                }
                //Special attack
                case(1)-> {
                    message.append("\n- You try to use your special attack skill: ");

                    double specialAttack = theHero.specialSkill(theMonster);
                    //Not enough mana
                    if (specialAttack < 0){
                        message.append("\n\tDespite trying with all your dedication and might, you failed due to not having enough mana!");
                    }else {
                        switch (theHero.getClassName()) {
                            case "Warrior" -> {
                                //Used special attack but failed to hit
                                if (specialAttack == 0){
                                    message.append("\n\tYou used Crushing Blow, but the monster dodged it with ease!");
                                }
                                else {
                                    message.append("\n\tYou used Crushing Blow, dealing ");
                                    message.append(String.format("%.2f",
                                            theMonster.applyTrueDamage(specialAttack)
                                    ));
                                }
                            }
                            case "Priestess" -> {
                                message.append("\n\tThe heaven heard your prayer, healing you for ");
                                message.append(String.format("%.2f",
                                        theMonster.applyTrueDamage(specialAttack)
                                ));
                            }
                            case "Thief" -> {
                                //Used special attack but failed to hit
                                if (specialAttack == 0){
                                    message.append("\n\tYou sneak behind the monster, but it noticed you causing you to miss the attack attack!");
                                }
                                else {
                                    if (specialAttack > 2){
                                        message.append("\n\tThe monster is surprised by your attack." +
                                                " Thrilled by the adrenaline inside you, you have gained additional attack speed!");
                                    }
                                    double [] sneakAttackDamage = theHero.normalAttackMove();
                                    double totalSneakAttackDamage = 0;
                                    for (int i = 0; i < sneakAttackDamage.length; ++i) {
                                        totalSneakAttackDamage += theMonster.applyDamage(sneakAttackDamage[i]);
                                    }
                                    message.append("\n\tCaught the monster by surprised, you have dealt a total damage of " + totalSneakAttackDamage);

                                }
                            }
                        }
                    }
                }

                //Use potion
                case (2) ->{
                    double hpBeforeHeal = theHero.getHitPoints();
                    boolean useHealing = theHero.useHealingPotion();
                    double hpAfterHeal = theHero.getHitPoints();
                    double amountHealed = hpAfterHeal - hpBeforeHeal;

                    message.append("\n- You chose to use a healing potion:\n");
                    if (!useHealing){
                        message.append("\tYou rummaged your inventory but sadly, there was no healing potion left!");
                    }else if (amountHealed == 0){
                        message.append("\tYou burped loudly. Drinking a potion felt weird when you are at full health.");
                    }
                    else{
                        message.append("\tYou used a healing potion, healed for ");
                        message.append(String.format("%.2f", amountHealed));
                    }
                }
            }

            //If the monster dies, it cannot attack
            if (theMonster.isDead()) {
                TextBasedGUI_CombatView.getInstance().displayNotificationForTheLastCombatTurn(message.toString(), true);
                return 1;
            }

            //Monster's turn
            message.append("\n\nThe monster's turn: ");
            int theMonsterChoice = theMonster.combatChoice(theHero);
            switch (theMonsterChoice){
                //Attack
                case 0 ->{
                    message.append("\n- The monster's attack: ");
                    double[] monsterInflictDamage = theMonster.normalAttackMove();
                    for (int i = 0; i < monsterInflictDamage.length; ++i) {
                        double monsterActualInflictDamage = theHero.applyDamage(monsterInflictDamage[i]);
                        message.append("\n\tIn strike ").append(i + 1).append(":\n");
                        //Update notification
                        //if the monster chose to attack
                        if (monsterActualInflictDamage != 0) {
                            message.append("\t\tThe monster has attacked you, inflicted ").append(String.format("%.2f", monsterActualInflictDamage)).append(" damage.");
                        } else {
                            message.append("\t\tYou managed to block the monster's attack");
                        }

                        if (theHero.isDead()){
                            message.append("\n\tYou appear to be not moving anymore, so the monster stopped its attack.");
                            break;
                        }
                    }

                    if (theHero.isDead()) {
                        TextBasedGUI_CombatView.getInstance().displayNotificationForTheLastCombatTurn(message.toString(), false);
                        return 0;
                    }
                }
                //Healing
                case 1->{
                    double amountHeal = theMonster.selfHeal();
                    message.append("\n- The monster try to heal itself");
                    if (amountHeal == 0){
                        message.append("\n\tIt appears to be too wounded and tired from the fight. It has failed at healing itself.");
                    }
                    else{
                        message.append("\n\tThe monster roars, its vein starting to show. It has successfully healed itself for the amount ");
                        message.append(String.format("%.2f", amountHeal));
                    }

                }
                //Frenzy
                case 2->{
                    message.append("\n- Low on health, the monster entered a frenzy stage, increasing its damage");

                    //Triple damage
                    theMonster.frenzyMode(3);
                }
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
