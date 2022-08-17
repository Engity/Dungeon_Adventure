import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import static org.junit.jupiter.api.Assertions.*;
/**
 * Pillar Factory Test
 * {@code @author:} Toan Nguyen, Thao Nguyen
 * @version 08 14 2022
 */
class PillarFactoryTest {
    /**
     * Test method for {@link PillarFactory# createPillarOfAbstraction ()}.
     * Test the value of the Pillar Abstraction
     */
    @Test
    void testCreatePillarOfAbstraction() {
        Pillar myPillar = new Pillar("Abstraction","This item is 1 in 4 object you need to collect");
        Pillar myPillar2 = PillarFactory.createPillar(1);
        assertEquals(myPillar.toString(), Objects.requireNonNull(myPillar2).toString());
    }
    /**
     * Test method for {@link PillarFactory# createPillarOfEncapsulation ()}.
     * Test the value of the Pillar Encapsulation
     */
    @Test
    void testCreatePillarOfEncapsulation() {
        Pillar myPillar = new Pillar("Encapsulation","Remember to collect this item");
        Pillar myPillar2 = PillarFactory.createPillar(2);
        assertEquals(myPillar.toString(), Objects.requireNonNull(myPillar2).toString());
    }
    /**
     * Test method for {@link PillarFactory# createPillarOfInheritance ()}.
     * Test the value of the Pillar Inheritance
     */
    @Test
    void testCreatePillarOfInheritance() {
        Pillar myPillar = new Pillar("Inheritance","Bring this item with you to win the game");
        Pillar myPillar2 = PillarFactory.createPillar(3);
        assertEquals(myPillar.toString(), Objects.requireNonNull(myPillar2).toString());
    }
    /**
     * Test method for {@link PillarFactory# createPillarOfpolymorphism ()}.
     * Test the value of the Pillar Polymorphism
     */
    @Test
    void testCreatePillarOfPolymorphism() {
        Pillar myPillar = new Pillar("Polymorphism","You are so close to win the game, take this");
        Pillar myPillar2 = PillarFactory.createPillar(4);
        assertEquals(myPillar.toString(), Objects.requireNonNull(myPillar2).toString());
    }

}