public class MonsterFactory {
    /**
     *
     * @param theChoice if 1 create the ogre, if 2 create the gremlin, if 3 create the skeleton
     * @return
     */
    public static Monster createMonster(final int theChoice) {

        switch (theChoice) {
            case 1:
                return createOgre();

            case 2:
                return createGremlin();

            case 3:
                return createSkeleton();

        }//end switch

        return null;
    }//end createMonster()



    private static Monster createOgre() {

        return new Monster("Orge", 200, 2, .6, 30, 60,5, 30, 60);
    }//end createOgre()

    private static Monster createGremlin() {

        return new Monster("Gremlin", 70, 5, .8, 15, 30,1, 20, 40);
    }//end createGremlin()

    private static Monster createSkeleton() {

        return new Monster("Skeleton", 100, 3, .8, 30, 50,3, 30, 50);
    }//end createSkeleton
}


