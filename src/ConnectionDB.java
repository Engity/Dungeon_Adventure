import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Justin Noel
 *
 * Simple class to demonstrate SQLite connectivity
 * 1) create connection
 * 2) add a table
 * 3) add entries to the table
 * 4) query the table for its contents
 * 5) display the results
 *
 * NOTE: any interactions with a database should utilize a try/catch
 * since things can go wrong
 *
 * @see <a href="https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/">
https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/</a>
 *
 */
public class ConnectionDB {
//    public static void main(String[] args) {
//        try {
//            ConnectionDB con = new ConnectionDB();
//            Hero theHero = con.getHero("WARRIOR");
//            theHero.setMyHealingPotion(5);
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }


    private SQLiteDataSource myMonsters;
    private String myQueryM;
    private SQLiteDataSource myHeroes;
    private String myQueryH;


    /**
     *  Default constructor
     * @throws SQLException sql error
     */
    public ConnectionDB() throws SQLException {
        monsterTable();
        fillTableM();
        heroTable();
        fillTableH();
    }


    // Makes the monster table
    private void monsterTable() {

        // Properties
        myMonsters = null;

        try {

            // Get the DB connection and create the table/statement
            myMonsters = new SQLiteDataSource();
            myMonsters.setUrl("jdbc:sqlite:myMonsters.db");

        } catch (Exception e) {

            // There was an error
            System.out.println("ERROR creating monster data from table: " + e);
            System.exit(0);
        }

        //Drop the table if it has existed and create it again
        myQueryM = "DROP TABLE IF EXISTS myMonsters";

        try(Connection conn = myMonsters.getConnection(); Statement stmt = conn.createStatement(); ) {

            int rv = stmt.executeUpdate(myQueryM);

        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

        // Set up table statement
        myQueryM = "CREATE TABLE IF NOT EXISTS myMonsters ( " +
                "NAME TEXT NOT NULL, " +
                "HEALTH TEXT NOT NULL, " +
                "DAMAGE_MIN TEXT NOT NULL, " +
                "DAMAGE_MAX TEXT NOT NULL, " +
                "ATTACK_SPEED TEXT NOT NULL, " +
                "HIT_CHANCE TEXT NOT NULL, " +
                "HEAL_CHANCE TEXT NOT NULL, " +
                "MIN_HEAL TEXT NOT NULL, " +
                "MAX_HEAL TEXT NOT NULL, " +
                "HEAL_MAX TEXT NOT NULL)";



        try(Connection conn = myMonsters.getConnection(); Statement stmt = conn.createStatement(); ) {

            int rv = stmt.executeUpdate(myQueryM);

        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

        //System.out.println( "Created monsters table successfully" );

    }

    // Fills the table
    private void fillTableM() {
        //next insert two rows of data
        //System.out.println( "Attempting to insert two rows into questions table" );

        // Query the data
        myQueryM = "INSERT INTO myMonsters (NAME, HEALTH, DAMAGE_MIN, DAMAGE_MAX , ATTACK_SPEED , HIT_CHANCE , HEAL_CHANCE, MIN_HEAL, MAX_HEAL, HEAL_MAX) VALUES ( 'Ogre', '200','20', '60', '2', '0.6', '0.1', '20', '40', '1') , ('Skeleton', '110','20', '60', '3', '0.8', '0.4', '20', '30', '2'), ('Gremlin', '80','10', '25', '5', '0.8', '0.2', '20', '40', '4')";

        try (Connection conn = myMonsters.getConnection(); Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate(myQueryM);
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

    }

    /**
     *  This method will extract a monster.
     *
     * @param theMonster the name oif the monster to extract.
     * @return this the monster to return.
     */
    public Monster getMonster(final String theMonster) {

        // Debug statement
        //System.out.println( "Selecting all rows from monster table" );

        String query = theMonster.toUpperCase();
        Monster monster = null;

        // Determine the select statement
        switch (query) {
            case "OGRE":
                query = "SELECT * FROM myMonsters WHERE NAME = 'Ogre'";
                break;
            case "SKELETON":
                query = "SELECT * FROM myMonsters WHERE NAME = 'Skeleton'";
                break;
            case "GREMLIN":
                query = "SELECT * FROM myMonsters WHERE NAME = 'Gremlin'";
                break;

        }

        try (Connection conn = myMonsters.getConnection(); Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            String theName = rs.getString( "NAME" );
            Integer theHit = Integer.parseInt(rs.getString( "HEALTH" ));
            Integer theMin = Integer.parseInt(rs.getString( "DAMAGE_MIN" ));
            Integer theMax = Integer.parseInt(rs.getString( "DAMAGE_MAX" ));
            Integer theAttack = Integer.parseInt(rs.getString( "ATTACK_SPEED" ));
            Integer theChance = Integer.parseInt(rs.getString( "HEAL_CHANCE" ));
            Integer theMinHeal = Integer.parseInt(rs.getString( "MIN_HEAL" ));
            Integer theMaxHeal = Integer.parseInt(rs.getString( "MAX_HEAL" ));
            Integer theHealChance = Integer.parseInt(rs.getString( "HEAL_MAX" ));


            monster = new Monster(theName,theHit,theAttack, theChance, theMin,  theMax,theHealChance,theMinHeal,theMaxHeal);


        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        return monster;
    }

    // Makes the hero table
    private void heroTable() {

        // Properties
        myHeroes = null;

        try {
            // Get the DB connection and create the table/statement
            myHeroes = new SQLiteDataSource();
            myHeroes.setUrl("jdbc:sqlite:myHeros.db");

        } catch (Exception e) {

            // There was an error
            System.exit(0);
        }

        //Drop the table if it has existed and create it again
        myQueryH = "DROP TABLE IF EXISTS myHeros";

        try(Connection conn = myHeroes.getConnection();
            Statement stmt = conn.createStatement(); ) {

            int rv = stmt.executeUpdate(myQueryH);

        } catch(Exception e) {
        }

        // Set up table statement
        myQueryH = "CREATE TABLE IF NOT EXISTS myHeros ( " +
                "theName TEXT NOT NULL, " +
                "theHit TEXT NOT NULL, " +
                "theMin TEXT NOT NULL, " +
                "theMax TEXT NOT NULL, " +
                "theAttack TEXT NOT NULL, " +
                "theCritChance TEXT NOT NULL, " +
                "theBlock TEXT NOT NULL) ";

        try(Connection conn = myHeroes.getConnection();
            Statement stmt = conn.createStatement(); ) {

            int rv = stmt.executeUpdate(myQueryH);

        } catch(Exception e) {
        }



    }

    // Fills the hero table
    private void fillTableH() {

        //next insert two rows of data
        //System.out.println( "Attempting to insert two rows into hero table" );

        // Query the data
        myQueryH = "INSERT INTO myHeros (theName, theHit, theMin, theMax, theAttack, theCritChance, theBlock) " +
                "VALUES ( 'Warrior', '650', '30', '40', '3', '0.5', '0.6'), " +
                "('Priestess', '350', '10', '25', '5', '0.7', '0.2'), " +
                "('Thief', '400', '15', '30', '6', '0.9', '0.4')";

        try (Connection conn = myHeroes.getConnection(); Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate(myQueryH);
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

    }

    /**
     *  This method will extract a hero.
     *
     * @param theHero the name oif the hero to extract.
     * @return this the hero to return.
     */
    public Hero getHero(final String theHero) {

        // Debug statement
        //System.out.println( "Selecting all rows from hero table" );

        String query = theHero.toUpperCase();
        Hero hero = null;

        // Determine the select statement
        switch (query) {
            case "WARRIOR":
                query = "SELECT * FROM myHeros WHERE theName = 'Warrior'";
                break;
            case "PRIESTESS":
                query = "SELECT * FROM myHeros WHERE theName = 'Priestess'";
                break;
            case "THIEF":
                query = "SELECT * FROM myHeros WHERE theName = 'Thief'";
                break;

        }

        try (Connection conn = myHeroes.getConnection(); Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            String theName = rs.getString( "theName" );
            Double theHit = Double.parseDouble(rs.getString( "theHit" ));
            Integer theMin = Integer.parseInt(rs.getString( "theMin" ));
            Integer theMax = Integer.parseInt(rs.getString( "theMax" ));
            Integer theAttack =Integer.parseInt(rs.getString( "theAttack" ));
            Double theCritChance = Double.parseDouble(rs.getString( "theCritChance" ));
            Double theBlock = Double.parseDouble(rs.getString( "theBlock" ));

            switch (theHero.toUpperCase()) {
                case "WARRIOR":
                    hero = new Warrior (theHit, theAttack, theName, theMin, theMax, theBlock, theCritChance);
                    break;
                case "PRIESTESS":
                    hero = new Priestess (theHit, theAttack, theName, theMin, theMax, theBlock, theCritChance);
                    break;
                case "THIEF":
                    hero = new Thief (theHit, theAttack, theName, theMin, theMax, theBlock, theCritChance);
                    break;
                default:
                    hero = new Thief(theHit, theAttack, theName, theMin, theMax, theBlock, theCritChance);;
            }



        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        return hero;
    }
}

