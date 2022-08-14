import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;



class MonsterFactoryTest {
    /**
     * Test method for {@link MonsterFactory# createOgre ()}.
     * Test the value of the Monster Ogre
     */
    @Test
    void testCreatOgre() {
        Monster myMonster = new Monster("Orge", 200, 2, .6, 30, 60, 5, 30, 60);
        Monster myMonster2 = MonsterFactory.createMonster(1);
        assertEquals(myMonster.toString(), myMonster2.toString());
    }
    /**
     * Test method for {@link MonsterFactory# createGremlin ()}.
     * Test the value of the Monster Ogre
     */
    @Test
    void testCreatGremlin() {
        Monster myMonster = new Monster("Gremlin", 70, 5, .8, 15, 30,1, 20, 40);
        Monster myMonster2 = MonsterFactory.createMonster(2);
        assertEquals(myMonster.toString(), myMonster2.toString());
    }
    /**
     * Test method for {@link MonsterFactory# createSkeleton ()}.
     * Test the value of the Monster Ogre
     */
    @Test
    void testCreatSkeleton() {
        Monster myMonster = new Monster("Skeleton", 100, 3, .8, 30, 50,3, 30, 50);
        Monster myMonster2 = MonsterFactory.createMonster(3);
        assertEquals(myMonster.toString(), myMonster2.toString());
    }
}







