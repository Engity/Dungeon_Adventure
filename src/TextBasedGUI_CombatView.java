/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Text based GUI for combat
 * {@code @author:} Toan Nguyen
 * @version 08 07 2022
 */
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

    int promptUserForAction(){
        InputChecker fightInputChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);

        //Get the player's stat
        ArrayList<StringBuilder> playerStat = CombatController.getInstance().parseDungeonCharacter();
        //Get the monster's stat
        ArrayList<StringBuilder> monsterStat = CombatController.getInstance().parseDungeonCharacter();

        //Get the height of the string
        int myRepeatingPromptHeight = Math.max(playerStat.size(), monsterStat.size());

        //Parse the decoration
        String decorationStB ="""
                                     __
                                   \\   /
                                     \\\\
                                       \\\\
                     ___________________||/\\_
                    (___________________()| _||||||||||||||||||||||||||||||||||||||||||>\t
                                        ||\\/
                                       //
                                     //
                                   /__\\
                """;
        String[] decorationStr = decorationStB.split("\n");
        StringBuilder[] repeatingPromptDecoration = new StringBuilder [decorationStr.length];
        int decorationStrWidth = 0;
        for (int i = 0 ; i < decorationStr.length;i++) {
            decorationStrWidth = Math.max(decorationStr[i].length(), decorationStrWidth);
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
        myRepeatingPrompt.append("\nNotification: There is a huge monster here....\n");

        myRepeatingPrompt.append("Please enter your choice: ");

        String [] optionName = {"Attack", "Defend", "Use Potion", "Flee"};

        fightInputChecker.setMyRepeatingPrompt(myRepeatingPrompt.toString());

        fightInputChecker.setMyWrongRangePrompt("There is no the option corresponding to the index you just inputted, please try again");
        fightInputChecker.setMyErrorPrompt("Wrong format, please input numbers only!");

        int userChoice = fightInputChecker.inputCheckForNumber(optionName);

        switch (userChoice){
            //attack
            case (0)-> {
                System.out.println("I attack");
                System.out.println("It suppose to be a function but there it has not been developed");
            }
            //Defend
            case(1)-> {
                System.out.println("I defend");
                System.out.println("It suppose to be a function but there it has not been developed");
            }

            //Use potion
            case (2) ->{
                System.out.println("I use a potion");
                System.out.println("It suppose to be a function but there it has not been developed");
            }

            //Flee
            case (3) ->{
                System.out.println("I ran");
                System.out.println("It suppose to be a function but there it has not been developed");
            }
        }

        return 0;
    }



    //Sword art used for combat (The biggest width is 68 chars)

    //  ______   ______   .___  ___. .______        ___   .___________.
    // /      | /  __  \  |   \/   | |   _  \      /   \  |           |
    //|  ,----'|  |  |  | |  \  /  | |  |_)  |    /  ^  \ `---|  |----`
    //|  |     |  |  |  | |  |\/|  | |   _  <    /  /_\  \    |  |
    //|  `----.|  `--'  | |  |  |  | |  |_)  |  /  _____  \   |  |
    // \______| \______/  |__|  |__| |______/  /__/     \__\  |__|

}