/**
 * this is the complete Priestess class that extends the Hero class
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
        super.setMaxHitPoints(healAmount);
        setMyMana(0);//Reset the mana to 0
        return healAmount;
    }

}