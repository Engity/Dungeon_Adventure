public class MonsterFactory {
    /**
     *
     * @param theChoice if 1 create the ogre, if 2 create the gremlin, if 3 create the skeleton
     */
    public static Monster createMonster(final int theChoice) {

        return switch (theChoice) {
            case 1 -> createGremlin();
            case 2 -> createSkeleton();
            case 3 -> createOgre();
            default ->//end switch

                    null;
        };

    }//end createMonster()



    private static Monster createOgre() {

        return new Monster("Ogre", 200, 2, .6, 30, 60,0.1, 30, 60);
    }//end createOgre()

    private static Monster createGremlin() {

        return new Monster("Gremlin", 70, 5, .8, 15, 30,0.4, 20, 40);
    }//end createGremlin()

    private static Monster createSkeleton() {

        return new Monster("Skeleton", 100, 3, .8, 30, 50,0.3, 30, 50);
    }//end createSkeleton
}


