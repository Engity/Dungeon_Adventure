import java.util.*;
/**
 * this is the complete Thief class that extends Hero class
 */
public class Thief extends Hero{


    /**
     * makes a thief object
     * @param theHit
     * @param theAttack
     * @param theName
     * @param theMin
     * @param theMax
     * @param theBlock
     * @param theCritChance
     */
    public Thief(Double theHit, int theAttack, String theName, int theMin, int theMax, Double theBlock, double theCritChance) {
        super(theHit, theAttack, theName, theMin, theMax, theBlock, theCritChance);
    }

    @Override
    protected boolean useHealingPotion() {
        return super.useHealingPotion();
    }

    @Override
    public double specialSkill(final DungeonCharacter theEnemy) {

        if (getMyMana() < 100){
            //Do nothing if we don't have enough mana
            return -1;
        }

        double attackChance = super.getMyDamageMin() + (super.getMyDamageMax() - super.getMyDamageMin()) * DungeonCharacter.MY_RANDOM_SEED.nextDouble();
        if(attackChance < 40 && attackChance > 20) {
            setMyMana(0);//Reset the mana to 0
            return 2;
        } else if( attackChance < 20) {
            setMyMana(0);//Reset the mana to 0
            return -1;
        }
        setMyMana(0);//Reset the mana to 0
        return 1;


    }

}