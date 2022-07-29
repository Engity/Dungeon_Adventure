/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.util.Scanner;

/**
 * Text based GUI for navigating the rooms
 * {@code @author:} Toan Nguyen
 * @version 07 27 2022
 */
public class TextBasedGUI_NavigationView {
    /**
     * The source to input from.
     * Default is System.in
     */

    private static final Scanner INPUT_SCANNER = new Scanner (System.in);
    private DungeonAdventure myGameController;
    private TextBasedGUI_NavigationView(){

    }
    private static TextBasedGUI_NavigationView myNavigationViewInstance = new TextBasedGUI_NavigationView();

    public static TextBasedGUI_NavigationView getInstance(){
        return myNavigationViewInstance;
    }

    public void attachController(final DungeonAdventure theGameController){
        myGameController = theGameController;
    }

    public int promptUserForDirection(int theCurrentRoomAccess){
        int userChoice = 0;



        return userChoice;
    }
}
