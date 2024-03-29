/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */

import java.io.Serializable;
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

public class Room implements Serializable {
    private final int id;
    private ArrayList<Object> myContents;
    private byte myAccessCode ; //Bit 0th for North, 1st for East, 2nd for South, 3rd for West

    private Monster myGuardian;

    public Room(final int theID){
        myContents = new ArrayList<>();
        myAccessCode = 0;//Every door is closed
        myGuardian = null;
        id = theID;
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
        //Get bits corresponding to the direction
        return ((myAccessCode >> theDirection) & 1) == 1;
    }

    /**
     * Set the corresponding direction can be accessed
     * @param theDirection direction wishes to set, 0 for North, 1 for East, 2 for South, 3 for West
     */
    void openAccess(final int theDirection){
        myAccessCode |= 1 << theDirection;
    }

    /**
     * Compare the rooms
     * @param theRoom the room to be compared
     * @return true if the room id is equal, false otherwise
     */
    boolean equals(final Room theRoom){
        return id == theRoom.getID();
    }

    /**
     * Convert ID to Coordinate
     * @param theID the ID to be converted
     * @return an array with the first element is the x coordinate, second is the y coordinate
     */
    static int[] convertIDtoCoordinate(final int theID){
        int[] res = new int[2];
        res[0] = theID / DungeonAdventure.getInstance().getMapSizeWidth();
        res[1] = theID % DungeonAdventure.getInstance().getMapSizeWidth();
        return res;
    }

    /**
     * Convert Coordinate to ID
     * @param theXPos the x position
     * @param theYPos the y position
     * @return an int, the ID retrieve from these coordinate
     */

    static int convertCoordinateToID(final int theXPos, final int theYPos){
        return DungeonAdventure.getInstance().getMapSizeWidth() * theXPos + theYPos;
    }

    /**
     * Put a monster in this room
     * @param theMonster the monster that is put in this room
     */
    void setMyGuardian(final Monster theMonster){
        myGuardian = theMonster;
    }

    /**
     * Get the guardian
     * @return the guardian in this room
     */
    Monster getGuardian(){
        return myGuardian;
    }

    /**
     * Add loot content to the room
     */
    void addRoomContent(final Object theContent){
        myContents.add(theContent);
    }


    /**
     * Retrieve the loot, called when guardian is defeated
     * @return all the loot in this room
     * Erase the content in this room
     */
    ArrayList<Object> retrieveLoot(){//final Monster theMonster){
        ArrayList<Object> res = myContents;
        myContents = new ArrayList<>();
        return res;
    }

}

