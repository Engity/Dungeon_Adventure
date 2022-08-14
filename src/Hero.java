/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
/**
 * Hero controller
 * {@code @author:} Toan Nguyen, Justin Noel
 * @version 08 14 2022
 */

public abstract class Hero extends DungeonCharacter{
    private Set<Pillar> myPillarStorage;
    private double myMana;
    private int myHealingPotion;
    private double myHealByPotionAmount;

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
        setBlockChance(theBlock);
        myMana = 0;
        myHealingPotion = 5;
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
        if( (100 * rand.nextDouble())< getBlockChance()) {
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
        if (myHealingPotion <= 0)
            return false;
        --myHealingPotion;
        super.increaseHP(myHealByPotionAmount);
        return true;
    }

    /**
     * Increase the amount of healing potions
     */
    void addHealingPotion(){
        ++myHealingPotion;
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

    /**
     * To string method
     * Add in healing potion amount
     */

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder (super.toString());
        res.append("Your healing potions: ").append(myHealingPotion).append("\n");

        return res.toString();
    }



}


