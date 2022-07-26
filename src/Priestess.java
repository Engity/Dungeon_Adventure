import java.util.Random;

public class Priestess extends Hero{
    String mySpecialAttack;
    double mySpecialDamage;
    double mySpecialAttackChance;

    public Priestess(String theSpecialSkill, double theSpecialSkillChance, double theSpecialSkillDamage) {
        super(100, 100, "Priestess", 100, 100, 100, 100);
        mySpecialAttack = theSpecialSkill;
        mySpecialAttackChance = theSpecialSkillChance;
        mySpecialDamage = theSpecialSkillDamage;
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