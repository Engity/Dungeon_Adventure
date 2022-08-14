import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.Objects;


class MonsterFactoryTest {
    /**
     * Test method for {@link MonsterFactory# createOgre ()}.
     * Test the value of the Monster Ogre
     */
    @Test
    void testCreatOgre() {
        Monster myMonster = new Monster("Ogre", 200, 2, .6, 30, 60, 0.1, 30, 60);
        Monster myMonster2 = MonsterFactory.createMonster(3);
        assertEquals(myMonster.toString(), Objects.requireNonNull(myMonster2).toString());
    }
    /**
     * Test method for {@link MonsterFactory# createGremlin ()}.
     * Test the value of the Monster Gremlin
     */
    @Test
    void testCreatGremlin() {
        Monster myMonster = new Monster("Gremlin", 70, 5, .8, 15, 30,0.4, 20, 40);
        Monster myMonster2 = MonsterFactory.createMonster(1);
        assertEquals(myMonster.toString(), Objects.requireNonNull(myMonster2).toString());
    }
    /**
     * Test method for {@link MonsterFactory# createSkeleton ()}.
     * Test the value of the Monster Skeleton
     */
    @Test
    void testCreatSkeleton() {
        Monster myMonster = new Monster("Skeleton", 100, 3, .8, 30, 50,0.3, 30, 50);
        Monster myMonster2 = MonsterFactory.createMonster(2);
        assertEquals(myMonster.toString(), Objects.requireNonNull(myMonster2).toString());
    }
}







