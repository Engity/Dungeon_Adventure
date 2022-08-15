/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the fighting mechanics of monster
 * {@code @author:} Toan Nguyen
 *  @version 08 14 2022
 */

public class monsterCombatTest {
    private Monster myMonster;
    private Hero myHero;

    @BeforeEach
    public void setup(){
        //Create an ogre
        myMonster = MonsterFactory.createMonster(3);
        //Create a warrior
        myHero = HeroFactory.createHero("", 1);
    }


    /**
     * Test method for {@link Monster#selfHeal()}
     * Test the value of self-healing of the monster
     */
    @Test
    void testSelfHealing_NormalCase() {
        //Reduce the monster to half health
        myMonster.applyTrueDamage(0.5 * myMonster.getMaxHitPoints());
        double theAmountHeal = 0;

        while (true){//Loop till successfully heal
            theAmountHeal = myMonster.selfHeal();
            if (theAmountHeal > 0){
                break;
            }
        }
        //The healing time should increase
        assertEquals(1, myMonster.getMyHealTime());
        assertTrue(theAmountHeal > 0);
    }

    /**
     * Test method for {@link Monster#selfHeal()}
     * Test the value of self-healing of the monster
     * Special case: when the monster had used up all of its heal time
     */
    @Test
    void testSelfHealing_SurpassedHealTime() {
        //Reduce the monster to half health
        myMonster.applyTrueDamage(0.5 * myMonster.getMaxHitPoints());
        myMonster.setMyHealTime(1);
        double theAmountHeal = myMonster.selfHeal();


        assertEquals(-1, theAmountHeal);
        //The healing time should not change
        assertEquals(1, myMonster.getMyHealTime());
    }

    /**
     * Test method for {@link Monster#selfHeal()}
     * Test the value of self-healing of the monster
     * Special case: When the monster is at full health
     */
    @Test
    void testSelfHealing_FullHealth() {
        double theAmountHeal = myMonster.selfHeal();

        //The healing time should increase
        assertEquals(0, myMonster.getMyHealTime());

        assertEquals(0, theAmountHeal);
    }

    /**
     * Test method for {@link Monster#combatChoice(DungeonCharacter)}
     * Test to see whether the monster choose to heal rather than destroy the player on low health
     */
    @Test
    void testCombatDecision_HeroLowHealth() {
        //Reduce the monster to low health
        myMonster.applyTrueDamage(0.75 * myMonster.getMaxHitPoints());
        myMonster.setMyHealTime(1);
        //Reduce hero to low health
        myHero.applyTrueDamage(0.9 * myHero.getMaxHitPoints());

        int combatChoice = myMonster.combatChoice(myHero);
        //The monster will choose to maul the hero
        assertEquals(0, combatChoice);
    }

    /**
     * Test method for {@link Monster#combatChoice(DungeonCharacter)}.
     * Test to see whether the monster choose to go frenzy, increasing it damage or not
     */
    @Test
    void testCombatDecision_MonsterFrenzy() {
        //Reduce the monster to low health
        myMonster.applyTrueDamage(0.75 * myMonster.getMaxHitPoints());
        myMonster.setMyHealTime(1);

        int combatChoice = myMonster.combatChoice(myHero);
        //The monster will choose to go crazy
        assertEquals(2, combatChoice);
    }

    /**
     * Test method for {@link Monster#combatChoice(DungeonCharacter)}
     * Test to see whether the monster choose to heal itself or not
     */
    @Test
    void testCombatDecision_MonsterLowHealth() {
        //Reduce the monster to low health
        myMonster.applyTrueDamage(0.90 * myMonster.getMaxHitPoints());
        myMonster.setMyHealTime(0);

        int combatChoice = myMonster.combatChoice(myHero);
        //The monster will choose to go crazy
        assertEquals(1, combatChoice);
    }

    /**
     * Test method for {@link Monster#combatChoice(DungeonCharacter)}
     * Test to see whether the monster choose to heal itself or not
     *  Special case: The monster ran out of healing time
     */
    @Test
    void testCombatDecision_MonsterLowHealthOutOfHealTime() {
        //Reduce the monster to low health
        myMonster.applyTrueDamage(0.90 * myMonster.getMaxHitPoints());
        myMonster.setMyHealTime(1);

        int combatChoice = myMonster.combatChoice(myHero);
        //The monster will choose to go crazy
        assertEquals(2, combatChoice);
    }
}

