public class MonsterFactory {
    /**
     *
     * @param theChoice
     *          if 1 create the Gremlin,
     *          if 2 create the Skeleton,
     *          if 3 create the Ogre
     */
    public static Monster createMonster(final int theChoice) {
        ConnectionDB sql;
        Monster theMonster = null;

        try {
            sql = new ConnectionDB();
            //Default choice is Warrior
            theMonster = sql.getMonster("GREMLIN");

            switch (theChoice) {
                case 2 -> {
                    theMonster = sql.getMonster("SKELETON");
                }
                case 3 -> {
                    theMonster = sql.getMonster("OGRE");
                }
            }
        } catch(Exception e) {
        }
        return theMonster;
    }

}




