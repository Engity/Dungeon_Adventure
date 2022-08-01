import java.util.Random;
public class DungeonCharacter {
    private String myCharacterName;

    private double myHitPoints;
    private int myAttackSpeed;

    private double myChangeToHit;

    private double myDamageMin;
    private double myDamageMax;
    Random RANDOM_SEED;


    public DungeonCharacter(final String theName,final double theHit, final int theAttack,final double theChance,  final double theMin, final double theMax) {
        myCharacterName = theName;
        myHitPoints = theHit;
        myAttackSpeed = theAttack;
        myChangeToHit = theChance;
        myDamageMin = theMin;
        myDamageMax = theMax;
        RANDOM_SEED = new Random();
    }

    public DungeonCharacter() {

    }


    public boolean isDeath() {

        if ((myHitPoints <= 0))
            return true;
        else
            return false;
    }

}