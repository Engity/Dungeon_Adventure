/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
/**
 * Pillar Factory
 * {@code @author:} Toan Nguyen, Thao Nguyen
 * @version 08 14 2022
 */
public class PillarFactory {
    /**
     *
     * @param theChoice if 1 create the pillar abstraction, if 2 create the pillar encapsulation, if 3 create the pillar inheritance, if 4 create the pillar Polymorphism
     */
    public static Pillar createPillar (final int theChoice) {

        return switch (theChoice) {
            case 1 -> createPillarOfAbstraction();
            case 2 -> createPillarOfEncapsulation();
            case 3 -> createPillarOfInheritance();
            case 4 -> createPillarOfPolymorphism();
            default ->//end switch

                    null;
        };

    }//end createPillar()



    /**
     *
     * @return
     */
    private static Pillar createPillarOfAbstraction() {

        return new Pillar("Abstraction","This item is 1 in 4 object you need to collect");
    }
    private static Pillar createPillarOfEncapsulation() {

        return new Pillar("Encapsulation","Remember to collect this item");

    }

    private static Pillar createPillarOfInheritance() {


        return new Pillar("Inheritance","Bring this item with you to win the game");
    }
    private static Pillar createPillarOfPolymorphism() {

        return new Pillar("Polymorphism","You are so close to win the game, take this");
    }
}

