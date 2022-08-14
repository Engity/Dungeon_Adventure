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



    private static Pillar createPillarOfAbstraction() {

        return new Pillar("Abstraction");
    }
    private static Pillar createPillarOfEncapsulation() {

        return new Pillar("Encapsulation");
    }

    private static Pillar createPillarOfInheritance() {

        return new Pillar("Inheritance");
    }
    private static Pillar createPillarOfPolymorphism() {

        return new Pillar("Polymorphism");
    }
}