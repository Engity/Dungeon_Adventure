import java.util.ArrayList;

public class Adventurer {

    private Hero myPlayer;
    private int myPotions;
    private boolean myBuff;




    public Adventurer (String theHero){

        HeroFactory fac =new HeroFactory();

        myPlayer = fac.createHero(theHero);
    myBuff = false;
        myPotions = 0;
    }

    public int getPotions() {
        return myPotions;
    }
    public void setPotions(int thePotions) {
        myPotions++;
    }

    /**
     * makes the buff for vision potion so that character can see rooms around them
     * @return
     */
    public boolean getVisionBuff() {
        return myBuff;
    }

    /**
     * vision potion buff status
     * @param theBuff
     */
    public void setVisionBuff(final boolean theBuff) {
        myBuff = theBuff;
    }


}

