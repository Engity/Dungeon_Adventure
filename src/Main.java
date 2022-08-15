public class Main {
    public static void main(String[] args){
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

        //TextBasedGUI_CombatView.getInstance().promptUserForFightAction();
        //Database test
//        try {
//            ConnectionDB con = new ConnectionDB();
//            Hero theHero = con.getHero("WARRIOR");
//            theHero.setMyHealingPotion(5);
//
//            Monster theMonster = con.getMonster("OGRE");
//            System.out.println(theMonster.toString());
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

}