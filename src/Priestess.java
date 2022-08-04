import java.util.Random;

public class Priestess extends Hero{
    private String mySpecialSkill;
    private double myHealPoints;

    public Priestess(String theSpecialSkill, double theHeal) {
        super(80, 5, "Priestess", 20, 50, 0.2, 0.7);
        mySpecialSkill = theSpecialSkill;
        myHealPoints = theHeal;
    }

    public void specialSkill() {
        Random rand = new Random();
        super.setMyHitpoints(super.getMyHitpoints()+(10 + (50-10) * rand.nextDouble()));

    }

}