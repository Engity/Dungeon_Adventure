import java.util.Random;

public class Thief extends Hero{
    String mySpecialAttack;
    double mySpecialDamage;
    double mySpecialAttackChance;

    public Thief(String theSpecialAttack, double theSpecialAttackChance, double theSpecialAttackDamage) {
        super(100, 100, "Thief", 100, 100, 100, 100);
        mySpecialAttack = theSpecialAttack;
        mySpecialAttackChance = theSpecialAttackChance;
        mySpecialDamage = theSpecialAttackDamage;
    }

    public double specialAttack() {
        Random rand = new Random();
        double attackChance = super.getMyDamageMin() + (super.getMyDamageMax() - super.getMyDamageMin()) * rand.nextDouble();
        if(attackChance > mySpecialAttackChance) {
            return mySpecialDamage;
        }
        return 0;
    }

}