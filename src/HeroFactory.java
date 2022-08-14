/**
 * this is the HeroFactory class for SQL
 */
public class HeroFactory {
    /**
     * This method is for creating your hero
     * @param theChoice
     *  1. Warrior
     *  2. Priestess
     *  3. Thief
     * @return the hero
     */
    public static Hero createHero(final String theName, final int theChoice) {
        // Properties
        ConnectionDB sql;

        Hero theHero = null;

        try {
            sql = new ConnectionDB();
            //Default choice is Warrior
            theHero = sql.getHero("WARRIOR");
            theHero.setMyHealingPotion(5);

            switch (theChoice) {
                case 2 -> {
                    theHero = sql.getHero("PRIESTESS");
                    theHero.setMyHealingPotion(5);
                }
                case 3 -> {
                    theHero = sql.getHero("THIEF");
                    theHero.setMyHealingPotion(10);
                }
            }

            theHero.setName(theName);


        } catch(Exception e) {
            //System.out.println("Error: " + e);
        }


        return theHero;
    }
}