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
    private ArrayList<Object> myContents;
    private byte myAccessCode ; //Bit 0th for North, 1st for West, 2nd for South, 3rd for East

    //private Monster myGuardian; //Comment out because monster has not yet been implemented
    private boolean myDiscoveredStatus;

    //Default constructor, creating an empty room with no guardian
    Room(){
        myContents = new ArrayList<>();
        myAccessCode = 0;//Every door is closed
        myDiscoveredStatus = false;
        //myGuardian = null;
    }
    void setAccessCode(byte theNewAccessCode){
        byte mask = 0xf;//15 in Dec, 0000 1111 in Bin

        //Comparing the last 4 bits
        myAccessCode &= theNewAccessCode & mask;
    }

    byte getAccessCode(){
        return myAccessCode;
    }

    void setDiscoveredStatus(boolean theNewDiscoveredStatus){
        myDiscoveredStatus = theNewDiscoveredStatus ;
    }

    boolean getDiscoveredStatus(){
        return myDiscoveredStatus;
    }

}
