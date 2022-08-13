import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PillarFactoryTest {
    /**
     * Test method for {@link PillarFactory# createPillarOfAbstraction ()}.
     * Test the value of the Pillar Abstraction
     */
    @Test
    void testCreatePillarOfAbstraction() {
        Pillar myPillar = new Pillar("Abstraction");
        Pillar myPillar2 = PillarFactory.createPillar(1);
        assertEquals(myPillar.toString(), myPillar2.toString());
    }
    /**
     * Test method for {@link PillarFactory# createPillarOfEncapsulation ()}.
     * Test the value of the Pillar Encapsulation
     */
    @Test
    void testCreatePillarOfEnscapsulation() {
        Pillar myPillar = new Pillar("Encapsulation");
        Pillar myPillar2 = PillarFactory.createPillar(2);
        assertEquals(myPillar.toString(), myPillar2.toString());
    }
    /**
     * Test method for {@link PillarFactory# createPillarOfInheritance ()}.
     * Test the value of the Pillar Inheritance
     */
    @Test
    void testCreatePillarOfInheritance() {
        Pillar myPillar = new Pillar("Inheritance");
        Pillar myPillar2 = PillarFactory.createPillar(3);
        assertEquals(myPillar.toString(), myPillar2.toString());
    }
    /**
     * Test method for {@link PillarFactory# createPillarOfpolymorphism ()}.
     * Test the value of the Pillar Polymorphism
     */
    @Test
    void testCreatePillarOfPolymorphism() {
        Pillar myPillar = new Pillar("Polymorphism");
        Pillar myPillar2 = PillarFactory.createPillar(4);
        assertEquals(myPillar.toString(), myPillar2.toString());
    }

}