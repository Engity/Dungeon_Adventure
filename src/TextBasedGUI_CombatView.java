/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
/**
 * Text based GUI for combat
 * {@code @author:} Toan Nguyen
 * @version 07 27 2022
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
    private DungeonAdventure myGameController;

    private static final TextBasedGUI_CombatView myCombatViewInstance = new TextBasedGUI_CombatView();

    public static TextBasedGUI_CombatView getInstance(){
        return myCombatViewInstance;
    }

    void attachController(final DungeonAdventure theGameController){
        myGameController = theGameController;
    }
    private TextBasedGUI_CombatView(){

    }



    //Sword art used for combat (The biggest width is 68 chars)
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
