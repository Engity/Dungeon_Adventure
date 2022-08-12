import java.io.Serializable;
public class GameObjects implements Serializable {
    private String myItemName;
    public GameObjects(final String theItemName) {
        this.setMyItemName(theItemName);
    }



    public void setMyItemName(final String theItemName) {
        this.myItemName = theItemName;
    }
    public String getMyItemName() {
        return myItemName;
    }




    public String toString() {
        return "Object name " + getMyItemName();
    }
}
