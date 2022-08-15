/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
/**
 * Priestess class controller that extends Hero class
 * {@code @author:} Toan Nguyen, Justin Noel
 * @version 08 14 2022
 */
public class Priestess extends Hero{

    /**
     * makes a priestess object
     * @param theHit
     * @param theAttack
     * @param theName
     * @param theMin
     * @param theMax
     * @param theBlock
     * @param theCritChance
     */
    public Priestess(Double theHit, int theAttack, String theName, int theMin, int theMax, Double theBlock, double theCritChance) {
        super(theHit, theAttack, theName, theMin, theMax, theBlock, theCritChance);
    }

    /**
     * Apply super healing
     * @return Return the amount heal
     */
    @Override
    protected double specialSkill(DungeonCharacter theEnemy) {
        if (getMyMana() < 100){
            //Do nothing if we don't have enough mana
            return -1;
        }
        //Will always heal at least 25% of maxHealth and maximum of 50% of maxHealth
        double healProportion = DungeonAdventure.RANDOM_SEED.nextDouble(0.25) + 0.25;

        double healAmount = getMaxHitPoints() * healProportion;
        double actualHealAmount = getHitPoints();
        super.increaseHP(healAmount);

        actualHealAmount = getHitPoints() - actualHealAmount;

        setMyMana(0);//Reset the mana to 0
        return actualHealAmount;
    }

    /**
     * Return a random number between the range [myDamageMin, myDamageMax)
     * Will based on myHitChance if fails, return 0
     * Priestess increases more mana per attack
     */
    @Override
    protected double normalAttackStrike() {
        setMyMana(getMyMana() + 15); //Increase the mana
        return super.normalAttackStrike();
    }

}