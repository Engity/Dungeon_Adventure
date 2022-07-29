/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
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

    private static final Scanner INPUT_SCANNER = new Scanner (System.in);
    private DungeonAdventure myGameController;

    private static TextBasedGUI_CombatView myCombatViewInstance = new TextBasedGUI_CombatView();
    private TextBasedGUI_CombatView(){
        this(null);
    }
    private TextBasedGUI_CombatView(final DungeonAdventure theGameController){
        myGameController = theGameController;
    }

    public static TextBasedGUI_CombatView getInstance(){
        return myCombatViewInstance;
    }

    public void attachController(final DungeonAdventure theGameController){
        myGameController = theGameController;
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
