import java.io.Serializable;
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
    public String getMyDescription() {
        return myDescription;
    }

    public void setMyDescription(String myDescription) {
        this.myDescription = myDescription;
    }




    public String toString() {


        return toString();
    }
}
