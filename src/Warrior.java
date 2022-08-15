/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */

/**
 * Warrior class controller that extends Hero class
 * {@code @author:} Toan Nguyen, Justin Noel
 * @version 08 14 2022
 */
public class Warrior extends Hero{

    private double mySpecialDamage;

    /**
     * THis is the super class for all hero characters
     * @param theHit the characters health
     * @param theAttack the attack speed
     * @param theName the name for the character
     * @param theMin the min damage
     * @param theMax the max damage
     * @param theBlock the block speed
     */
    public Warrior(Double theHit, int theAttack, String theName, int theMin, int theMax, Double theBlock, double theCritChance) {
        super(theHit, theAttack, theName, theMin, theMax, theBlock, theCritChance);
        mySpecialDamage = 0;
    }

    @Override
    protected double specialSkill(final DungeonCharacter theEnemy) {
        if (getMyMana() < 100){
            //Do nothing if we don't have enough mana
            return -1;
        }

        double attackChance = DungeonAdventure.RANDOM_SEED.nextDouble();

        //Deal 1.5 x Max Damage to 3
        mySpecialDamage = (1.5 + 1.5 * DungeonAdventure.RANDOM_SEED.nextDouble()) * getMyDamageMax();

        //Have 40% chance of succeed
        if(attackChance <= 0.4) {
            setMyMana(0);//Reset the mana to 0
            return  mySpecialDamage;

        }
        //Use the special attack but does not succeed
        setMyMana(0);//Reset the mana to 0
        return 0;
    }

}
