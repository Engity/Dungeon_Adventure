/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
/**
 * Thief class controller that extends Hero class
 * {@code @author:} Toan Nguyen, Justin Noel
 * @version 08 14 2022
 */
public class Thief extends Hero{
    private int myBoostAttackSpeed;//How many times the thief can have additional attack strikes
    /**
     * THis is the super class for all hero characters
     * @param theHit the characters health
     * @param theAttack the attack speed
     * @param theName the name for the character
     * @param theMin the min damage
     * @param theMax the max damage
     * @param theBlock the block speed
     */
    public Thief(final Double theHit, final int theAttack, final String theName, final int theMin, final int theMax, final Double theBlock, final double theCritChance) {
        super(theHit, theAttack, theName, theMin, theMax, theBlock, theCritChance);
    }

    /**
     * Thief's special attack mechanics.
     * 40 percent chance it is successful.
     *         If it is successful, Thief gets an attack and another turn (extra attack) in the current round.
     *         There is a 20 percent chance the Thief is caught in which case no attack at all is rendered.
     *         The other 40 percent is just a normal attack.
     * @return 0 if the thief failed to perform this attack
     *         1 if the thief succeed at a stealth attack
     *         2 if the thief gained additional attack speed next round
     */
    @Override
    public double specialSkill(final DungeonCharacter theEnemy) {
        if (getMyMana() < 100){
            //Do nothing if we don't have enough mana
            return -1;
        }

        double attackChance = DungeonAdventure.RANDOM_SEED.nextDouble();
        setMyMana(0);//Reset the mana to 0
        //No attack is rendered when it is less than 20%
        if (attackChance > 0.2){
            //Stealth attack if the chance is 0.6
            if (attackChance < 0.6){
               return 1;
            }
            //Boost attack speed for the next turn
            myBoostAttackSpeed = 2;
            return 2;
        }
        return 0;
    }

    /**
     * Loop reflecting attack speed (how many times this character can strike in a turn)
     * Has bonus attack speed
     * Call the singular attack methods to represent a strike
     * @return an array, showing the damage for each strike
     */
    @Override
    double[] normalAttackMove(){
        double[] res;
        if (myBoostAttackSpeed > 0){
            //Gained 1.5 times more attack strikes
            setAttackSpeed(getAttackSpeed() * 3 / 2);
        }
        res = super.normalAttackMove();
        if (myBoostAttackSpeed > 0){
            --myBoostAttackSpeed;
        }

        if (myBoostAttackSpeed == 0){
            resetAttackSpeed();
        }

        return res;
    }

}