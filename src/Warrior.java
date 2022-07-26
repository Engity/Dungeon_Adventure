import java.util.Random;

public class Warrior extends Hero{
    String mySpecialAttack;
    double mySpecialDamage;
    double mySpecialAttackChance;

    public Warrior(String theSpecialAttack, double theSpecialAttackChance, double theSpecialAttackDamage) {
        super(180, 100, "Warrior", 100, 100, 100, 100);
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
