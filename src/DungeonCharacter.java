import java.util.Random;
import java.io.Serializable;
public class DungeonCharacter implements Serializable {
    private String myCharacterName;
    private int myHitPoints;
    private int myAttackSpeed;
    private double myChangeToHit;
    private int myDamageMin;
    private int myDamageMax;

    final static Random RANDOM_SEED = new Random();



    public DungeonCharacter(final String theName, final int theHit, final int theAttack,
                            final double theChance, final int theMin, final int theMax) {
        this.setName(theName);
        this.setHitPoint(theHit);
        this.setAttackSpeed(theAttack);
        this.setChanceToHit(theChance);
        this.setDamageMin(theMin);
        this.setDamageMax(theMax);

    }

    private void setDamageMax(final int theMax) {
        this.myDamageMax = theMax;

    }

    private void setDamageMin(final int theMin) {
        this.myDamageMin = theMin;

    }

    private void setChanceToHit(final double theChance) {
        this.myChangeToHit = theChance;

    }

    private void setAttackSpeed(final int theAttack) {
        this.myAttackSpeed = theAttack;

    }

    private void setHitPoint(final int theHit) {
        this.myHitPoints = theHit;

    }

    private void setName(final String theName) {

        this.myCharacterName = theName;
    }

    public String getMyCharacterName() {
        return myCharacterName;
    }

    public int getMyHitPoints() {
        return myHitPoints;
    }

    public int getMyAttackSpeed() {
        return myAttackSpeed;
    }

    public double getMyChangeToHit() {
        return myChangeToHit;
    }

    public int getMyDamageMin() {
        return myDamageMin;
    }

    public int getMyDamageMax() {
        return myDamageMax;
    }

    // Check if the character is death or not
    public boolean isDeath() {

        return ((myHitPoints <= 0));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder ();
        sb.append ("Name: ").append(myCharacterName).append("\n");
        sb.append ("Hit Points: ").append(myHitPoints).append("\n");
        sb.append ("Attack Speed: ").append(myAttackSpeed).append("\n");
        sb.append ("Chance to hit: ").append(myChangeToHit).append("\n");
        sb.append ("Minimum Damage: ").append(myDamageMin).append("\n");
        sb.append ("Maximum Damage: ").append(myDamageMax).append("\n");
        return sb.toString();
    }

}



