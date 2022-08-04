import java.util.Random;

/**
 * @author Justin Noel
 *
 */
public class DungeonCharacter {

    private double myHitpoints;
    private int myAttackSpeed;
    private String myCharacterName;
    private double myDamageMin;
    private double myDamageMax;
    private double myBlockChance;
    private double myCritChance;
    Random RANDOM_SEED;

    /**
     * parameters for hero and subclasses
     * @param theHit
     * @param theAttack
     * @param theName
     * @param theMin
     * @param theMax
     * @param theBlock
     * @param theCritChance
     */
    protected DungeonCharacter(final double theHit, final int theAttack, final String theName, final double theMin, final double theMax, final double theBlock, final double theCritChance) {
        myHitpoints = theHit;
        myAttackSpeed = theAttack;
        myCharacterName = theName;
        myDamageMin = theMin;
        myDamageMax = theMax;
        myBlockChance = theBlock;
        myCritChance =theCritChance;
        RANDOM_SEED = new Random();
    }

    /**
     * Returns myHitPoints
     * @return
     */
    protected double getMyHitpoints() {
        return myHitpoints;
    }
    protected double getMyDamageMin() { return myDamageMin; }
    protected double getMyDamageMax() { return myDamageMax; }

    /**
     * parameters for myHitpoints
     * @param myHitpoints
     */
    protected void setMyHitpoints(double myHitpoints) {
        this.myHitpoints = myHitpoints;
    }

    /**
     * parameters for fighting an enemy
     * @param theEnemy
     */
    protected String attack(final DungeonCharacter theEnemy) {

        double damage = (myDamageMin + (myDamageMin + myDamageMax) * RANDOM_SEED.nextDouble());

        theEnemy.setMyHitpoints(theEnemy.getMyHitpoints() - damage);

        return "Player hit the enemy and dealt " + damage + "amount of damage";
    }

    protected void defend(final double theIncomingDamage) {
        Random rand = new Random();
        if((rand.nextDouble() * 100) < myBlockChance) {
            myHitpoints -= theIncomingDamage;

        }
        //check players Hitpoints and return whether they are alive or dead
    }
    protected boolean isDeath() {

        return ((myHitpoints <= 0));

    }

}