import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

/**
 *
 * @author tom capaul
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
public class SQL {

    public static void main(String[] args) {
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:questions.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println( "Opened database successfully" );


        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS Monster ( " +
                "C1 TEXT NOT NULL, " +
                "C2 TEXT NOT NULL )";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println( "Created questions table successfully" );

        //next insert two rows of data
        System.out.println( "Attempting to insert three rows into questions table" );

        String query1 = "INSERT INTO Monster ( C1, C2 ) VALUES ( 'Orge', '200 2 0.6 30 60 0.1 30 60' )";
        String query2 = "INSERT INTO Monster ( C1, C2 ) VALUES ( 'Skeleton', '100 3 0.8 30 50 0.3 30 50' )";
        String query3 = "INSERT INTO Monster ( C1, C2 ) VALUES ( 'Gremlin', '70 5 0.8 15 30 0.4 20 40' )";


        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query1 );
            System.out.println( "1st executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query2 );
            System.out.println( "2nd executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query3 );
            System.out.println( "3rd executeUpdate() returned " + rv );

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }


        //now query the database table for all its contents and display the results
        System.out.println( "Selecting all rows from test table" );
        query = "SELECT * FROM Monster";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            // and print it
            while ( rs.next() ) {
                String question = rs.getString( "C1" );
                String answer = rs.getString( "C2" );

                System.out.println( "Result: Monster = " + question +
                        ", Stats = " + answer );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

}