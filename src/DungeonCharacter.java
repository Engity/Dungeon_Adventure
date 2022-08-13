import java.util.Random;
import java.io.Serializable;
public class DungeonCharacter implements Serializable {
    private String myCharacterName;
    private double myHitPoints;
    private double myMaxHitPoints;
    private int myAttackSpeed;
    private double myHitChance;

    private double myBlockChance;
    private int myDamageMin;
    private int myDamageMax;



    final static Random RANDOM_SEED = new Random();



    public DungeonCharacter(final String theName, final double theHit, final int theAttack,
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
        this.myHitChance = theChance;

    }

    private void setAttackSpeed(final int theAttack) {
        this.myAttackSpeed = theAttack;

    }

    private void setHitPoint(final double theHit) {
        this.myHitPoints = theHit;

    }

    private void setName(final String theName) {

        this.myCharacterName = theName;
    }

    public String getMyCharacterName() {
        return myCharacterName;
    }

    public double getMyHitPoints() {
        return myHitPoints;
    }

    public int getMyAttackSpeed() {
        return myAttackSpeed;
    }

    public double getMyHitChance() {
        return myHitChance;
    }

    public int getMyDamageMin() {
        return myDamageMin;
    }

    public int getMyDamageMax() {
        return myDamageMax;
    }

    public double getMyMaxHitPoints() {
        return myMaxHitPoints;
    }

    public void setMyMaxHitPoints(final double theMaxHitPoints) {
        this.myMaxHitPoints = theMaxHitPoints;
    }

    public double getMyBlockChance() {
        return myBlockChance;
    }

    public void setMyBlockChance(final double theBlockChance) {
        this.myBlockChance = theBlockChance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder ();
        sb.append ("Name: ").append(myCharacterName).append("\n");
        sb.append ("Hit Points: ").append(myHitPoints).append("\n");
        sb.append ("Attack Speed: ").append(myAttackSpeed).append("\n");
        sb.append ("Chance to hit: ").append(myHitChance).append("\n");
        sb.append ("Minimum Damage: ").append(myDamageMin).append("\n");
        sb.append ("Maximum Damage: ").append(myDamageMax).append("\n");
        return sb.toString();
    }

    /**
     * Apply damage to this character
     * Won't go over 0
     * @param theDamage Damage this character should receive
     */
    void applyDamage(final double theDamage){
        double rollTheDice = DungeonAdventure.RANDOM_SEED.nextDouble();
        //Fail to block case
        if (rollTheDice > myBlockChance){
            myHitPoints = Math.max(0, myHitPoints - theDamage);
            return;
        }
        //Reduce damage if manage to block
        double reducedDamage = rollTheDice * theDamage;
        myHitPoints = Math.max(0, myHitPoints - reducedDamage);

    }

    /**
     * Increase health to this character
     * Won't go over myMaxHitPoints
     * @param theHealingAmount Health this character should receive
     */
    void increaseHP(final double theHealingAmount){
        myHitPoints = Math.min(myMaxHitPoints, myHitPoints + theHealingAmount);
    }

    /**
     * Return a random number between the range [myDamageMin, myDamageMax)
     * Will based on myHitChance if fails, return 0
     */
    double normalAttackMove(){
        double rollTheDice = DungeonAdventure.RANDOM_SEED.nextDouble();
        //Randomize based on hit chance
        if (rollTheDice > myHitChance){
            return 0;
        }
        //Random the damage this character would inflict
        return DungeonAdventure.RANDOM_SEED.nextDouble(myDamageMin, myDamageMax);
    }

}



