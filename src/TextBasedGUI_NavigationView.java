import java.util.Scanner;

public class TextBasedGUI_NavigationView {
    /**
     * The source to input from.
     * Default is System.in
     */

    private static final Scanner INPUT_SCANNER = new Scanner (System.in);
    private DungeonAdventure myGameController;
    public TextBasedGUI_NavigationView(){
        this(null);
    }
    public TextBasedGUI_NavigationView(final DungeonAdventure theGameController){
        myGameController = theGameController;
    }
}
