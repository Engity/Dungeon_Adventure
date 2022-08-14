import java.util.Random;

/**
 * This Monster class contains all addtional instance methods the monster characters
 */



public class Monster extends DungeonCharacter{

    private int myHealTime;
    private int myMinHealPoints;
    private int myMaxHealPoints;

    public int getMyHealTime() {
        return myHealTime;
    }

    public int getMyMinHealPoints() {
        return myMinHealPoints;
    }

    public int getMyMaxHealPoints() {
        return myMaxHealPoints;
    }



    /**
     *
     * @param theName character Name
     * @param theHit  the hit points
     * @param theAttack character attack speed
     * @param theChance Chance to hit
     * @param theMin  the minimum hit point
     * @param theMax the maximum hit point
     * @param theHealthTime the chance to health
     * @param theMinHeal the minimum heal points
     * @param theMaxHeal the maximum heal points
     */

    public Monster(final String theName, final int theHit, final int theAttack,
                   final double theChance, final int theMin, final int theMax,final int theHealthTime,final int theMinHeal, final int theMaxHeal) {
        super(theName, theHit, theAttack, theChance, theMin, theMax);


        this.setMyHealthTime(theHealthTime);
        this.setMinHealPoints(theMinHeal);
        this.setMaxHealPoints(theMaxHeal);
    }

    private void setMyHealthTime(int theHealthTime) {
    }

    private void setMinHealPoints(final int theMinHeal) {
        this.myMinHealPoints = theMinHeal;
    }
    private void setMaxHealPoints(final int theMaxHealth) {
        this.myMaxHealPoints = theMaxHealth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder (super.toString());
        sb.append ("Health Time: ").append(myHealTime).append("\n");
        sb.append ("Minimum Health Points: ").append(myMinHealPoints).append("\n");
        sb.append ("Maximum Health Points: ").append(myMaxHealPoints).append("\n");

        return sb.toString();
    }

    }

