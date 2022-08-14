import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import java.util.Random;

import static org.junit.Assert.*;
/**
 * this is all jUnit tests
 */
public class CharacterTests {

    @Test
    public void testHitPointsWarrior() {
        Warrior warrior = new Warrior(50.0, 99, "Warrior", 5,99, 20.0, 20);
        assertEquals("testHitPointsWarrior failed", 50, warrior.getMyHitPoints(), 0);
    }

    @Test
    public void testHitPointsThief() {
        Thief thief = new Thief(50.0, 99, "Thief", 5,99, 20.0, 20);
        assertEquals("testHitPoints failed", 50, thief.getMyHitPoints(), 0);
    }

    @Test
    public void testHitPointsPriestess() {
        Priestess priestess = new Priestess(50.0, 99, "priestess", 5,99, 20.0, 20);

        assertEquals("testHitPoints failed", 50, priestess.getMyHitPoints(), 0);
    }

    @Test
    public void testNameWarrior() {
        Warrior warrior = new Warrior(50.0, 99, "Warrior", 5,99, 20.0, 20);
        assertEquals("testNameWarrior failed", "Warrior", warrior.getCharacterName());

    }

    @Test
    public void testHitNameThief() {
        Thief thief = new Thief(50.0, 99, "Thief", 5,99, 20.0, 20);
        assertEquals("testHitNameThief failed", "Thief", thief.getCharacterName());
    }

    @Test
    public void testHitNamePriestess() {
        Priestess priestess = new Priestess(50.0, 99, "priestess", 5,99, 20.0, 20);
        assertEquals("testHitNameThief failed", "priestess", priestess.getCharacterName());
    }

    @Test
    public void testAttackWarrior() {
        Warrior warrior = new Warrior(50.0, 99, "Warrior", 5,99, 20.0, 20);
        assertEquals("testAttackWarrior failed", 99, warrior.getMyAttackSpeed(), 0);

    }

    @Test
    public void testHitAttackThief() {
        Thief thief = new Thief(50.0, 99, "Thief", 5,99, 20.0, 20);
        assertEquals("testHitAttackThief failed", 99, thief.getMyAttackSpeed(), 0);
    }

    @Test
    public void testHitNAttackPriestess() {
        Priestess priestess = new Priestess(50.0, 99, "priestess", 5,99, 20.0, 20);
        assertEquals("testHitNAttackPriestess failed", 99, priestess.getMyAttackSpeed(), 0);
    }

    @Test
    public void testHitWarrior() {
        Warrior warrior = new Warrior(50.0, 99, "Warrior", 5,99, 20.0, 20);
        assertEquals("testHitWarrior failed", 20, warrior.getMyHitChance(), 0);

    }

    @Test
    public void testHitHitThief() {
        Thief thief = new Thief(50.0, 99, "Thief", 5,99, 20.0, 20);
        assertEquals("testHitHitThief failed", 20, thief.getMyHitChance(), 0);
    }

    @Test
    public void testHitPriestess() {
        Priestess priestess = new Priestess(50.0, 99, "priestess", 5,99, 20.0, 20);
        assertEquals("testHitPriestess failed", 20, priestess.getMyHitChance(), 0);
    }

//    @Test
//    public void testMaxWarrior() {
//        Warrior warrior = new Warrior(50.0, 99, "Warrior", 5,99, 20, 20);
//        assertEquals("testMaxWarrior failed", 99, warrior.getMyDamageMax(), 0);
//
//    }
//
//    @Test
//    public void testMaxThief() {
//        Thief thief = new Thief(50.0, 99, "Thief", 5,99, 20, 20);
//        assertEquals("testMaxThief failed", 99, thief.getMyDamageMax(), 0);
//    }
//
//    @Test
//    public void  testMaxPriestess() {
//        Priestess priestess = new Priestess(50.0, 99, "priestess", 5,99, 20, 20);
//        assertEquals("testMaxPriestess failed", 99, priestess.getMyDamageMax(), 0);
//    }
//
//    @Test
//    public void testMinWarrior() {
//        Warrior warrior = new Warrior(50.0, 99, "Warrior", 5,99, 20, 20);
//        assertEquals("testMinWarrior failed", 5, warrior.getMyDamageMin(), 0);
//
//    }

//    @Test
//    public void testMInThief() {
//        Thief thief = new Thief(50.0, 99, "Thief", 5,99, 20, 20);
//        assertEquals("testMInThief failed", 5, thief.getMyDamageMin(), 0);
//    }
//
//    @Test
//    public void testMInPriestess() {
//        Priestess priestess = new Priestess(50.0, 99, "priestess", 5,99, 20, 20);
//        assertEquals("testMInPriestess failed", 5, priestess.getMyDamageMin(), 0);
//    }
//
//    @Test
//    public void testMaxHealthWarrior() {
//        Warrior warrior = new Warrior(50.0, 99, "Warrior", 5,99, 20, 20);
//        assertEquals("testMaxHealthWarrior failed", 50, warrior.getMyMaxHitPoints(), 0);
//
//    }
//
//    @Test
//    public void testMaxHealthThief() {
//        Thief thief = new Thief(50.0, 99, "Thief", 5,99, 20, 20);
//        assertEquals("testMaxHealthThief failed", 50, thief.getMyMaxHitPoints(), 0);
//    }

//    @Test
//    public void testMaxHealthPriestess() {
//        Priestess priestess = new Priestess(50.0, 99, "priestess", 5,99, 20, 20);
//        assertEquals("testMaxHealthPriestess failed", 50, priestess.getMyMaxHitPoints(), 0);
//    }
//
//    @Test
//    public void testMaxBlockWarrior() {
//        Warrior warrior = new Warrior(50.0, 99, "Warrior", 5,99, 20, 20);
//        assertEquals("testMaxBlockWarrior failed", 0, warrior.getBlockChance(), 0);
//
//    }
//
//    @Test
//    public void testMaxBlockThief() {
//        Thief thief = new Thief(50.0, 99, "Thief", 5,99, 20, 20);
//        assertEquals("testMaxBlockThief failed", 0, thief.getBlockChance(), 0);
//    }
//
//    @Test
//    public void testMaxBlockPriestess() {
//        Priestess priestess = new Priestess(50.0, 99, "priestess", 5,99, 20, 20);
//        assertEquals("testMaxBlockPriestess failed", 0, priestess.getBlockChance(), 0);
//    }
//
//    @Test
//    public void testDeadWarrior() {
//        Warrior warrior = new Warrior(0.0, 99, "Warrior", 5,99, 20, 20);
//        assertTrue("testDeadWarrior failed",warrior.isDead());
//
//    }
//
//    @Test
//    public void testDeadThief() {
//        Thief thief = new Thief(0.0, 99, "Thief", 5,99, 20, 20);
//        assertTrue("testDeadThief failed",thief.isDead());
//    }

    @Test
    public void testDeadPriestess() {
        Priestess priestess = new Priestess(0.0, 99, "priestess", 5,99, 20.0, 20);
        assertTrue("testDeadPriestess failed",priestess.isDead());
    }

}








