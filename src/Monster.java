public class Monster extends DungeonCharacter{



    private double myChancetoHeal;
    private int myMinHealPoints;
    private int myMaxHealPoints;



    public Monster(final String theName, final int theHit, final int theAttack,
                   final double theChance, final int theMin, final int theMax,final double theHealChance,final int theMinHeal, final int theMaxHeal) {
        super(theName, theHit, theAttack, theChance, theMin, theMax);

        this.setChanceToHeal(theHealChance);
        this.setMinHealPoints(theMinHeal);
        this.setMaxHealPoints(theMaxHeal);
    }
    private void setChanceToHeal(final double theHealthChance) {
    }
    private void setMinHealPoints(final int theMinHeal) {
    }
    private void setMaxHealPoints(final int theMaxHealth) {
    }

    @Override
    public String toString() {
        return super.toString();
    }

    }

