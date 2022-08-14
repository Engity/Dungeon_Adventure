import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Justin Noel
 *
 */
public abstract class Hero extends DungeonCharacter{

    private double myBlockChance;
    private Set<Pillar> myPillarStorage;
    private double myMana;

    private int myHealingPotions;
    private double myHealByPotionAmount;
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
    protected Hero(final double theHit, final int theAttack, final String theName, final int theMin, final int theMax, final Double theBlock, final Double theChance) {
        super(theName, theHit, theAttack, theChance, theMin, theMax);
        myBlockChance = theBlock;
        myMana = 0;
        myHealingPotion = 0;
        myPillarStorage = new HashSet<>();
        myHealByPotionAmount = theHit / 2;//50%
    }

    /**
     * this method gets the number of pillars in your inventory
     * @return
     */
    int getNumberOfPillars() {
        return myPillarStorage.size();
    }

    @Override
    public String toString() {
        return super.toString();
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

    /**
     * Use the healing potion, heal hero and decrease the amount by 1
     * @return true if successfully use a potion
     */

    boolean useHealingPotion(){
        if (myHealByPotionAmount <= 0)
            return false;
        --myHealingPotions;
        super.increaseHP(myHealByPotionAmount);
        return true;
    }

    /**
     * Increase the amount of healing potions
     */
    void addHealingPotion(){
        ++myHealingPotions;
    }

    /**
     * Add the pillar to the storage
     * @param thePillar to be added
     */
    void addPillarsToStorage(final Pillar thePillar){
        myPillarStorage.add(thePillar);
    }

    /**
     * Use to retrieve the pillar and clear the storage
     * @return An array containing all pillars the hero has
     */
    ArrayList<Pillar> retrievePillars(){
        ArrayList<Pillar> res = new ArrayList<>();
        for (var item: myPillarStorage){
            res.add(item);
        }
        myPillarStorage.clear();
        return res;
    }


}


