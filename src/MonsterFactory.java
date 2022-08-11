public class MonsterFactory {

    private static Monster createOgre() {

        return new Ogre();
    }//end createOgre()

    private static Monster createGremlin() {

        return new Gremlin();
    }//end createGremlin()

    private static Monster createSkeleton() {

        return new Skeleton();
    }//end createSkeleton
}


