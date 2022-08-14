
public class Pillar extends GameObjects{
    public  Pillar(final String theItemName, final String theDescription){
        super(theItemName, theDescription);

    }
    @Override
    public String toString() {

        return "Pillar " + getMyItemName();
    }
}








