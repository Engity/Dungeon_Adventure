import java.util.Random;

/**
 * this is the complete Priestess class that extends the Hero class
 */
public class Priestess extends Hero{

    /**
     * THis is the super class for all hero characters
     * @param theHit the characters health
     * @param theAttack the attack speed
     * @param theName the name for the character
     * @param theMin the min damage
     * @param theMax the max damage
     * @param theBlock the block speed
     */
    public Priestess(Double theHit, int theAttack, String theName, int theMin, int theMax, int theBlock, int theCritChance) {
        super(theHit, theAttack, theName, theMin, theMax, theBlock, theCritChance);
    }

    @Override
    protected boolean useHealingPotion() {
        return super.useHealingPotion();
    }

    @Override
    protected double specialSkill(DungeonCharacter theEnemy) {
        if (getMyMana() < 100){
            //Do nothing if we don't have enough mana
            return -1;
        }

        double healAmount = super.getMyHitPoints()+(10 + (50-10) * DungeonCharacter.MY_RANDOM_SEED.nextDouble());
        super.setMyMaxHitPoints(healAmount);
        setMyMana(0);//Reset the mana to 0
        return healAmount;
    }

}