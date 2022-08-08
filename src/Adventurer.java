public class Adventurer {

    private Hero myPlayer;
    private int myPotions;
    private int myVisionPotions;
    private String[] myPillars;
    private boolean myPotionBuff;
    private boolean myVisionBuff;

    public Adventurer (Hero thePlayer){
        myPlayer = thePlayer;
        myPotions = 0;
        myVisionPotions = 0;
        myPillars = new String[4];
        myPotionBuff = false;
        myVisionBuff = false;
    }

    public int getPotions() {
        return myPotions;
    }

    public int getVisionPotions() {
        return myVisionPotions;
    }

    public String getPillars() {
        return myPillars.toString();
    }

    public void setPotions(int thePotions) {
        myPotions++;
    }
    public void setVisionPotions(int thePotions) {
        myVisionPotions++;
    }


    public boolean getPotionBuff() {
        return myPotionBuff;
    }

    public boolean getVisionPotionBuff() {
        return myVisionBuff;
    }

    public void setPotionBuff(boolean theBuff) {
        myPotionBuff = theBuff;
    }

    public void setVisionBuff(boolean theBuff) {
        myVisionBuff = theBuff;
    }


}
