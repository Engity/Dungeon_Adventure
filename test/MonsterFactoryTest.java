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
        Monster myMonster = new Monster( "Ogre", 200,2, 0.6,50,75,  0.1,  1);
        Monster myMonster2 = MonsterFactory.createMonster(3);
        assertEquals(myMonster.toString(), Objects.requireNonNull(myMonster2).toString());
    }
    /**
     * Test method for {@link MonsterFactory# craeateGremlin ()}.
     * Test the value of the Monster Gremlin
     */
    @Test
    void testCreatGremlin() {
        Monster myMonster = new Monster( "Gremlin", 80,5, 0.8,10,25,  0.2,  4);
        Monster myMonster2 = MonsterFactory.createMonster(1);
        assertEquals(myMonster.toString(), Objects.requireNonNull(myMonster2).toString());
    }
    /**
     * Test method for {@link MonsterFactory# createSkeleton ()}.
     * Test the value of the Monster Skeleton
     */
    @Test
    void testCreatSkeleton() {
        Monster myMonster = new Monster( "Skeleton", 110,3, 0.8,35,45,  0.4,  1);
        Monster myMonster2 = MonsterFactory.createMonster(2);
        assertEquals(myMonster.toString(), Objects.requireNonNull(myMonster2).toString());
    }
}







