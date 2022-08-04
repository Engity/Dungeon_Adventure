import java.util.Random;

public class Thief extends Hero{
    private String mySpecialSkill;
    private double mySpecialDamage;
    private double mySpecialSkillChance;

    public Thief(String theSpecialSkill, double theSpecialSkillChance, double theSpecialSkillDamage) {
        super(60, 7, "Thief", 15, 40, 0.4, 0.9);
        mySpecialSkill = theSpecialSkill;
        mySpecialSkillChance = theSpecialSkillChance;
        mySpecialDamage = theSpecialSkillDamage;
    }

    public double specialSkill() {
        Random rand = new Random();
        double attackChance = super.getMyDamageMin() + (super.getMyDamageMax() - super.getMyDamageMin()) * rand.nextDouble();
        if(attackChance > mySpecialSkillChance) {
            return 2;
        }
        return 1;
    }

}