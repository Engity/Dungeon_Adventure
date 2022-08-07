import java.util.ArrayList;

public class Main {
    public static void main(String args[]){
//        TextBasedGUI_NavigationView.getInstance();
//        TextBasedGUI_CombatView.getInstance();
//        TextBasedGUI_MainDisplay.getInstance();
//        DungeonAdventure.getInstance();
//
//        TextBasedGUI_MainDisplay.getInstance().displayGameOverMenu();
//        TextBasedGUI_MainDisplay.getInstance().displayVictoryMenu();
//
//        Test parsing
//        ArrayList<StringBuilder> test = CombatController.getInstance().parseDungeonCharacter();
//        for (int i =0 ; i < test.size(); i++) {
//            System.out.println(test.get(i));
//        }
        TextBasedGUI_MainDisplay.getInstance().displayMainMenu();


//        StringBuilder swordDecorationStB = new StringBuilder("""
//                                __
//                                   \\  /
//                                     \\\\
//                                       \\\\
//                     ___________________||/\\_
//                    (___________________()| _||||||||||||||||||||||||||||||||||||||||||>
//                                        ||\\/
//                                       //
//                                     //
//                                   /__\\
//                """);
//        String[] swordDecoration = swordDecorationStB.toString().split("\n");
//        for (int i = 0 ; i < swordDecoration.length;i++)
//            System.out.println(swordDecoration[i]);

        TextBasedGUI_CombatView.getInstance().promptUserForAction();
    }

}
