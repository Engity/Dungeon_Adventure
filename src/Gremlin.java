public class Gremlin extends Monster{


    public Gremlin(String theName, double theHit, int theAttack, double theChance, double theMin, double theMax, double theChancetoHeal) {
        super(theName, theHit, theAttack, theChance, theMin, theMax, theChancetoHeal);
    }

    public Gremlin() {
        super("Gremlin", 70, 5, .8, 15, 30,0.4);
    }
}

