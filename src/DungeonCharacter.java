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
     * @param theCritChanc
     */
    public DungeonCharacter(final double theHit, final int theAttack, final String theName, final double theMin, final double theMax, final double theBlock, final double theCritChanc) {
        myHitpoints = theHit;
        myAttackSpeed = theAttack;
        myCharacterName = theName;
        myDamageMin = theMin;
        myDamageMax = theMax;
        myBlockChance = theBlock;
        myCritChance =theCritChanc;
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
        Random rand = new Random();
        theEnemy.setMyHitpoints(theEnemy.getMyHitpoints() - (myDamageMin + (myDamageMin + myDamageMax) * rand.nextDouble()));
    }

    public void defend(final double theIncomingDamage) {
        Random rand = new Random();
        if((rand.nextDouble() * 100) < myBlockChance) {
            myHitpoints -= theIncomingDamage;
        }

    }

}