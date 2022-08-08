import java.util.Random;

public class Warrior extends Hero{
    private String mySpecialSkill;
    private double mySpecialDamage;
    private double mySpecialSkillChance;

    public Warrior(String theSpecialSkill, double theSpecialSkillChance, double theSpecialSkillDamage) {
        super(180, 4, "Warrior", 60, 80, 0.6, 0.5);
        mySpecialSkill = theSpecialSkill;
        mySpecialSkillChance = theSpecialSkillChance;
        mySpecialDamage = theSpecialSkillDamage;
    }

    @Override
    protected void specialSkill(DungeonCharacter theEnemy) {

        Random rand = new Random();
        double attackChance = super.getMyDamageMin() + (super.getMyDamageMax() - super.getMyDamageMin()) * rand.nextDouble();
        mySpecialDamage = 100 + (200-100) * rand.nextDouble();
        if(attackChance > mySpecialSkillChance) {
            theEnemy.setHealth(theEnemy.getHealth() - mySpecialDamage);
        }
    }

}
