/**
 * @author Justin Noel
 *
 */
public class Hero extends DungeonCharacter{

    private double myBlockChance;

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
    protected Hero(final double theHit, final int theAttack, final String theName, final double theMin, final double theMax, final double theBlock, final double theCritChance) {
        super(theHit, theAttack, theName, theMin, theMax, theBlock, theCritChance);
        myBlockChance = theBlock;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * this is the attack behavior and other battle behavior
     */
    protected void attackBehavior() {

    }

    protected double getBlockChance() {
        return myBlockChance;
    }

    protected void setMyBlockChance(final double theBlock) {
        myBlockChance = theBlock;
    }

}
