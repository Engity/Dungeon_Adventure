import java.util.Random;

public class Warrior extends Hero{
    String mySpecialSkill;
    double mySpecialDamage;
    double mySpecialSkillChance;

    public Warrior(String theSpecialSkill, double theSpecialSkillChance, double theSpecialSkillDamage) {
        super(180, 4, "Warrior", 60, 80, 0.6, 0.5);
        mySpecialSkill = theSpecialSkill;
        mySpecialSkillChance = theSpecialSkillChance;
        mySpecialDamage = theSpecialSkillDamage;
    }

    public double specialSkill() {
        Random rand = new Random();
        double attackChance = super.getMyDamageMin() + (super.getMyDamageMax() - super.getMyDamageMin()) * rand.nextDouble();
        mySpecialDamage = 100 + (200-100) * rand.nextDouble();
        if(attackChance > mySpecialSkillChance) {
            return mySpecialDamage;
        }
        return 0;
    }

}
