/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.util.ArrayList;

/**
 * Model code for Room
 * Contains contents like buff, de-buff (add-on) or pillars
 * Has monster as guardian that player has to battle
 * Track whether player has visited
 * Has doors to the other rooms with access code in binary (Bit 0th for North, 1st for West, 2nd for South, 3rd for East)
 *      If bit 0th is 0, meaning door to the north is blocked.
 * {@code @author:} Toan Nguyen
 * @version 07 20 2022
 */

public class Room {
    private int id;
    private ArrayList<Object> myContents;
    private byte myAccessCode ; //Bit 0th for North, 1st for East, 2nd for South, 3rd for West

    //private Monster myGuardian; //Comment out because monster has not yet been implemented

    //Default constructor, creating an empty room with no guardian
    public Room(){
        this(0);
    }

    public Room(final int theID){
        myContents = new ArrayList<>();
        myAccessCode = 0;//Every door is closed
        //myGuardian = null;
        id = theID;
    }

    /**
     * Set @link Room#myAccessCode
     * @param theNewAccessCode new data for {@link Room#myAccessCode}
     */
    void setAccessCode(final byte theNewAccessCode){
        byte mask = 0xf;//15 in Dec, 0000 1111 in Bin

        //Override the last 4 bits
        myAccessCode &= theNewAccessCode & mask;
    }

    /**
     *
     * @return @link Room#myAccessCode
     */

    byte getAccessCode(){
        return myAccessCode;
    }

    /**
     *
     * @return @link Room#id
     */
    int getID(){
        return id;
    }

    /**
     * return whether the corresponding direction can be accessed
     * @param theDirection direction wishes to return, 0 for North, 1 for East, 2 for South, 3 for West
     */
    boolean getAccess(final int theDirection){
        //Get bit corresponding to the direction
        if (((myAccessCode >> theDirection) & 1) == 1){
            return true;
        }
        return false;
    }

    /**
     * Set the corresponding direction can be accessed
     * @param theDirection direction wishes to set, 0 for North, 1 for East, 2 for South, 3 for West
     */
    void openAccess(final int theDirection){
        myAccessCode |= 1 << theDirection;
    }

}
