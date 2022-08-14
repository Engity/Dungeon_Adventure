import java.util.*;
/**
 * This is the complete Warrior class that extends Hero class
 */
public class Warrior extends Hero{

    private double mySpecialDamage;

    /**
     * makes a warrior object
     * @param theHit
     * @param theAttack
     * @param theName
     * @param theMin
     * @param theMax
     * @param theBlock
     * @param theCritChance
     */
    public Warrior(Double theHit, int theAttack, String theName, int theMin, int theMax, Double theBlock, double theCritChance) {
        super(theHit, theAttack, theName, theMin, theMax, theBlock, theCritChance);
        mySpecialDamage = 0;
    }

    @Override
    protected boolean useHealingPotion() {
        return super.useHealingPotion();
    }

    @Override
    protected double specialSkill(final DungeonCharacter theEnemy) {

        if (getMyMana() < 100){
            //Do nothing if we don't have enough mana
            return -1;
        }

        double attackChance = super.getMyDamageMin() + (super.getMyDamageMax() - super.getMyDamageMin()) * DungeonCharacter.MY_RANDOM_SEED.nextDouble();
        mySpecialDamage = 75 + (175-75) * DungeonCharacter.MY_RANDOM_SEED.nextDouble();
        if(attackChance < 40.0) {
            setMyMana(0);//Reset the mana to 0
            return  mySpecialDamage;

        }
        setMyMana(0);//Reset the mana to 0
        return 0;
    }

}