/**
 *
 */
public class HeroFactory {

    /**
     *
     * @param theHero
     * @param theSpecialSkill
     * @param theSpecialSkillChance
     * @param theSpecialSkillDamage
     * @param theHeal
     * @return
     */
    public Hero createHero( String theHero, String theSpecialSkill, double theSpecialSkillChance, double theSpecialSkillDamage, double theHeal) {
        if(theHero == null || theHero.isEmpty()) {
            return null;
        }
        switch (theHero) {
            case "Warrior":
                return new Warrior(theSpecialSkill, theSpecialSkillChance, theSpecialSkillDamage);
            case "Priestess":
                return new Priestess(theSpecialSkill, theHeal);
            case "Thief":
                return new Thief(theSpecialSkill, theSpecialSkillChance, theSpecialSkillDamage);
            default:
                throw new IllegalArgumentException("Unknown Hero " + theHero);
        }
    }
}