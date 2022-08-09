import java.util.Random;
import java.io.Serializable;
public class DungeonCharacter implements Serializable {
    private String myCharacterName;

    double myHitPoints;
    private int myAttackSpeed;

    private double myChangeToHit;

    private double myDamageMin;
    private double myDamageMax;
// Getter and Setter for the var
    public String getMyCharacterName() {
        return myCharacterName;
    }

    public void setMyCharacterName(String myCharacterName) {
        this.myCharacterName = myCharacterName;
    }

    public double getMyHitPoints() {
        return myHitPoints;
    }

    public void setMyHitPoints(double myHitPoints) {
        this.myHitPoints = myHitPoints;
    }

    public int getMyAttackSpeed() {
        return myAttackSpeed;
    }

    public void setMyAttackSpeed(int myAttackSpeed) {
        this.myAttackSpeed = myAttackSpeed;
    }

    public double getMyChangeToHit() {
        return myChangeToHit;
    }

    public void setMyChangeToHit(double myChangeToHit) {
        this.myChangeToHit = myChangeToHit;
    }

    public double getMyDamageMin() {
        return myDamageMin;
    }

    public void setMyDamageMin(double myDamageMin) {
        this.myDamageMin = myDamageMin;
    }

    public double getMyDamageMax() {
        return myDamageMax;
    }

    public void setMyDamageMax(double myDamageMax) {
        this.myDamageMax = myDamageMax;
    }

    Random RANDOM_SEED;


    public DungeonCharacter(final String theName, final double theHit, final int theAttack,
                            final double theChance, final double theMin, final double theMax) {
        myCharacterName = theName;
        myHitPoints = theHit;
        myAttackSpeed = theAttack;
        myChangeToHit = theChance;
        myDamageMin = theMin;
        myDamageMax = theMax;
        RANDOM_SEED = new Random();
    }
// Increment the hitpoints of the character
    public void addHitPoints(int myHitPoints) {
        if (myHitPoints > 0) {
            this.myHitPoints += myHitPoints;
        }
    }
// Decrement the hitpoints of the character
    public void minusHitPoints (int myHitPoints){
        if (myHitPoints > 0)
            this.myHitPoints -= myHitPoints;
    }

// Check if the character is death or not
    public boolean isDeath() {

        return ((myHitPoints <= 0));
    }
}