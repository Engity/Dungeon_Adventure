/**
 * This Monster class contains all additional instance methods the monster characters
 */



public class Monster extends DungeonCharacter{
    private double myChanceToHeal;
    private int myMaximumHealTime;
    private int myHealTime;

    private boolean myFrenzyStage;

    private int myOriginalDamageMin;
    private int myOriginalDamageMax;

    /**
     *
     * @param theName character Name
     * @param theHitPoint  the hit points
     * @param theAttackSpeed character attack speed
     * @param theHitChance Chance to hit
     * @param theMinDamage  the minimum hit point
     * @param theMaxDamage the maximum hit point
     * @param theHealChance the chance to health
     * @param theMaximumHealTime Maximum time to heal
     */

    public Monster(final String theName, final double theHitPoint, final int theAttackSpeed,
                   final double theHitChance, final int theMinDamage, final int theMaxDamage,final double theHealChance,final int theMaximumHealTime) {
        super(theName, theHitPoint, theAttackSpeed, theHitChance, theMinDamage, theMaxDamage);
        myOriginalDamageMin = theMinDamage;
        myOriginalDamageMax = theMaxDamage;
        myHealTime = 0;
        this.setChanceToHeal(theHealChance);
        myMaximumHealTime = theMaximumHealTime;
        myFrenzyStage = false;
    }
    private void setChanceToHeal(final double theHealthChance) {
        this.myChanceToHeal = theHealthChance;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder (super.toString());
        sb.append ("Chance to self-heal: ").append(String.format("%.2f",myChanceToHeal)).append("\n");
        return sb.toString();
    }

    int getMyHealTime() {
        return myHealTime;
    }
    //Setter only used for testing purposes
    void setMyHealTime(final int theHealTime) {
        this.myHealTime = theHealTime;
    }

    /**
     * activate frenzy Mode
     * @param theIncreaseDamageProportion the proportion the damages are increased
     */
    void frenzyMode(double theIncreaseDamageProportion){
        myFrenzyStage = true;
        setDamageMax((int) (getDamageMax() * theIncreaseDamageProportion));
        setDamageMin((int) (getDamageMin() * theIncreaseDamageProportion));
    }

    /**
     * Process what should the monster do in the fight
     * @param theEnemy the opponent it is fighting with
     * @return the choice
     * 0. Attack
     * 1. Heal itself
     * 2. Go frenzy (Increase damage dealt)
     */

    int combatChoice(final DungeonCharacter theEnemy){
        //Will only heal when the player is not low enough and the monster need healing
        //Check if the player can be killed with 1 more turn of attack
        if (theEnemy.getHitPoints() < getDamageMin() * getAttackSpeed()){
            return 0;
        }

        //Frenzy time (only activated when the monster has less than 50% Health
        if (getHitPoints() < (0.5 * getMaxHitPoints()))
            //Only activate frenzy once
            if (!myFrenzyStage)
                return 2;

        if (myHealTime < myMaximumHealTime &&
                (getHitPoints() < (0.25 * getMaxHitPoints()))) {
            return 1;
        }

        //Just attack
        return 0;
    }

    /**
     * Monster self-heal itself
     * Won't exceed maximum time heal
     * @return the amount it healed
     *        return -1 if exceed the amount of time heal
     *        return 0 if fail to heal
     */
    double selfHeal(){
        double amountHealed = 0;
        if (myHealTime >= myMaximumHealTime){
            return -1;
        }

        //Don't heal at full health
        if (getHitPoints() == getMaxHitPoints()){
            return 0;
        }

        double rollTheDice = DungeonAdventure.RANDOM_SEED.nextDouble();

        if (rollTheDice < myChanceToHeal){
            double theShouldHealedAmount = (0.25 + DungeonAdventure.RANDOM_SEED.nextDouble()) * getMaxHitPoints();//At least 25% of maximum health
            amountHealed = getHitPoints();
            increaseHP(theShouldHealedAmount);
            amountHealed = getHitPoints() - amountHealed;
            setMyHealTime(getMyHealTime() + 1);
        }


        return amountHealed;
    }


}


