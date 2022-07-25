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

    /**
     *
     * @param theHit
     * @param theAttack
     * @param theName
     * @param theMin
     * @param theMax
     * @param theBlock
     * @param theCritChance
     */
    public DungeonCharacter(final double theHit, final int theAttack, final String theName, final double theMin, final double theMax, final double theBlock, final double theCritChance) {
        myHitpoints = theHit;
        myAttackSpeed = theAttack;
        myCharacterName = theName;
        myDamageMin = theMin;
        myDamageMax = theMax;
        myBlockChance = theBlock;
        myCritChance =theCritChance;
    }

    /**
     *
     * @return
     */
    public double getMyHitpoints() {
        return myHitpoints;
    }

    /**
     *
     * @param myHitpoints
     */
    public void setMyHitpoints(double myHitpoints) {
        this.myHitpoints = myHitpoints;
    }

    /**
     *
     * @param theEnemy
     */
    public void attack(final DungeonCharacter theEnemy) {
        Random RANDOM_SEED = new Random();
        theEnemy.setMyHitpoints(theEnemy.getMyHitpoints() - (myDamageMin + (myDamageMin + myDamageMax) * RANDOM_SEED.nextDouble()));
    }

    public void defend(final double theIncomingDamage) {
        Random rand = new Random();
        if((rand.nextDouble() * 100) < myBlockChance) {
            myHitpoints -= theIncomingDamage;
        }

    }

}