import java.util.Random;

/**
 * @author Justin Noel
 *
 */
public abstract class DungeonCharacter {

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
        myCritChance = theCritChance;
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
    protected double getHealth() {
        return myHitpoints;
    }

    protected void setHealth(double theHealth) {
        myHitpoints = theHealth;
    }



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
    protected void attack(final DungeonCharacter theEnemy) {

        Random rand = new Random();

        double attackHit = 100 * rand.nextDouble();
        double damage = myDamageMin + (myDamageMax - myDamageMin) * rand.nextDouble();

        // The Warrior hit the enemy
        if(attackHit > myCritChance) {
            theEnemy.setHealth(theEnemy.getHealth() - damage);
        }
    }

}