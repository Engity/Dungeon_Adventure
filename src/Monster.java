public class Monster extends DungeonCharacter{


    private final double myChancetoHeal;
    private int myMinHealPoints;
    private int myMaxHealPoints;

    public Monster(String theName, double theHit, int theAttack, double theChance, double theMin, double theMax, double myChancetoHeal, int myMinHealPoints, int myMaxHealPoints) {
        super(theName, theHit,theAttack, theChance,theMin,theMax);


        this.myChancetoHeal = myChancetoHeal;
        this.myMinHealPoints = myMinHealPoints;
        this.myMaxHealPoints = myMaxHealPoints;
    }
// the ability to heal of the monster determine by random math in range
    public void heal ()
    {
        int healPoints;
        boolean canHeal = (Math.random()<= myChancetoHeal) && (myHitPoints > 0);
        if (canHeal) {
            healPoints = (int) (Math.random() * (myMaxHealPoints - myMinHealPoints));
            addHitPoints(healPoints);
        }
    }

    public void minusHitPoints(int myHitPoints) {
        super.minusHitPoints(myHitPoints);
        heal();
    }



    @Override
    public String toString() {
        return super.toString();
    }

    }

