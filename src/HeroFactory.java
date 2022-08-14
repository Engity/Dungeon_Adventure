/**
 * this is the HeroFactory class for SQL
 */
public class HeroFactory {


    /**
     * This method is for creating your hero
     * @param theHero
     * @return
     */
    public Hero createHero(final String theHero) {

        // Properties
        ConnectionDB sql = null;

        try {

            sql = new ConnectionDB();


        } catch(Exception e) {
            //System.out.println("Error: " + e);
        }

        return sql.getHero(theHero);
    }
}