/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.io.Serializable;
/**
 * Game object
 * {@code @author:} Toan Nguyen, Thao Nguyen
 * @version 08 14 2022
 */
public class GameObjects implements Serializable {
    private String myItemName;

    private String myDescription;



    public GameObjects(final String theItemName, final String theDescription) {
        this.setMyItemName(theItemName);
        this.setMyDescription(theDescription);
    }



    public void setMyItemName(final String theItemName) {
        this.myItemName = theItemName;
    }
    public String getMyItemName() {

        return myItemName;
    }




    public void setMyDescription(String myDescription) {
        this.myDescription = myDescription;
    }
    public String getMyDescription() {
        return myDescription;
    }




    public String toString() {
        StringBuilder sb = new StringBuilder (super.toString());
        sb.append ("The Item Name is: ").append(myItemName).append("\n");
        sb.append ("The Item description is: ").append(myDescription).append("\n");
        return sb.toString();
    }
}

