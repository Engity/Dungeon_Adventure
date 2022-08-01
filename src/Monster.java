public class Monster extends DungeonCharacter{


    private double myChancetoHeal;

    private int myMinHealPoints;
    private int myMaxHealPoints;

    public Monster(String theName, double theHit, int theAttack, double theChance, double theMin, double theMax, double theChancetoHeal) {
        super(theName, theHit,theAttack, theChance,theMin,theMax);

    }

    @Override
    public String toString() {
        return super.toString();
    }

    }

