import java.util.ArrayList;
import java.util.Random;

/**
 * @author Justin Noel
 *
 */
public abstract class Hero extends DungeonCharacter{

    private double myBlockChance;
    private ArrayList<String> myInventory;
    private double myMana;


    private int myHealingPotion;

    /**
     *
     * @param theHit
     * @param theAttack
     * @param theName
     * @param theMin
     * @param theMax
     * @param theBlock
     */
    protected Hero(final double theHit, final int theAttack, final String theName, final int theMin, final int theMax, final int theBlock, final int theChance) {
        super(theName, theHit, theAttack, theChance, theMin, theMax);
        myBlockChance = theBlock;
        myMana = 0;
        myHealingPotion = 0;
        myInventory = new ArrayList<>();
    }

    /**
     * this method gets the number of pillars in your inventory
     * @return
     */
    public int getNumberOfPillars() {
        return myInventory.size();
    }

    /**
     * this method gets what is currently in your inventory
     * @return
     */
    public String getMyInventory() {
        return myInventory.toString();
    }

    /**
     * adds pillar to inventory
     * @param thePillar
     */
    public void addToInventory(final String thePillar) {
        myInventory.add(thePillar);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * this is the method for using the healing potion
     */
    protected boolean useHealingPotion() {
        if(myHealingPotion > 0) {
            myHealingPotion--;
            return true;
        }
        return false;
    }


    /**
     * Attack method
     * @param theEnemy the enemy we are attacking
     * @return the damage inflicted
     */
    @Override
    protected double attack(final DungeonCharacter theEnemy) {
        myMana += 10; //Increase the mana
        return super.attack(theEnemy);
    }

    // Gets the healing potions
    protected int getMyHealingPotion() {
        return myHealingPotion;
    }

    // Gets the healing potions
    protected double getBlockChance() {
        return myBlockChance;
    }

    // Sets the block chance
    protected void setMyBlockChance(final double theBlock) {
        myBlockChance = theBlock;
    }

    // Sets the Healing potions
    protected void setMyHealingPotion(final int theHealingPotion) {
        this.myHealingPotion = theHealingPotion;
    }

    // This is the method for the player to use the special skill
    protected abstract double specialSkill(final DungeonCharacter theEnemy);

    // This method will have a character defend against another
    protected boolean defend() {
        Random rand = new Random();
        if( (100 * rand.nextDouble())< myBlockChance) {
            return true;
        }
        return false;
    }

    void setMyMana(final double theValue){
        myMana = theValue;
    }

    double getMyMana(){
        return myMana;
    }
}


