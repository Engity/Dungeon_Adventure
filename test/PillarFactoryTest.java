import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PillarFactoryTest {
    /**
     * Test method for {@link MonsterFactory# createOgre ()}.
     * Test the value of the Monster Ogre
     */
    @Test
    void testCreatOgre() {
        Monster myMonster = new Monster("Orge", 200, 2, .6, 30, 60, 0.1, 30, 60);
        Monster myMonster2 = MonsterFactory.createMonster(1);
        assertEquals(myMonster.toString(), myMonster2.toString());
    }

}