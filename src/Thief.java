import java.util.Random;

public class Thief extends Hero{
    String mySpecialAttack;
    double mySpecialDamage;
    double mySpecialAttackChance;

    public Thief(String theSpecialAttack, double theSpecialAttackChance, double theSpecialAttackDamage) {
        super(60, 100, "Thief", 15, 40, 0.4, 0.9);
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