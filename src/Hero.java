import java.util.Random;

/**
 * @author Justin Noel
 *
 */
public abstract class Hero extends DungeonCharacter{

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

    // This is the method for the player to use the special skill
    protected abstract void specialSkill(final DungeonCharacter theEnemy);

    // This method will have a character defend against another
    protected boolean defend() {
        Random rand = new Random();
        if( (100 * rand.nextDouble())< myBlockChance) {
            return true;
        }
        return false;
    }
}

