import java.util.ArrayList;
import java.util.Collections;

/**
 * Handling populating the maze with level difficulty
 * {@code @author:} Toan Nguyen
 * @version 08 11 2022
 */
class PopulationController {
    private Room[][] myMap;
    private int myMapWidth;
    private int myMapHeight;
    private int myEntranceID;

    ArrayList<Monster> myMonsterPool;//Monster spawn pool
    private static final PopulationController myPopulationControllerInstance = new PopulationController();

    /**
     * Private for Singleton design
     */
    private PopulationController(){

    }

    /**
     * Get instance for singleton design
     */
    public static PopulationController getInstance(){
        return myPopulationControllerInstance;
    }

    /**
     * Load the maze to populate
     * @param theHeight height of the maze
     * @param theWidth width of the maze
     * @param theEntranceID ID of the entrance
     * @param theMap the maze
     */
    void loadTheMaze(final int theHeight, final int theWidth, final int theEntranceID, final Room[][] theMap){
        myMap = theMap;
        myMapHeight = theHeight;
        myMapWidth = theWidth;
        myEntranceID = theEntranceID;
        myMonsterPool = new ArrayList<>();
    }

    /**
     * Fill the monsters spawn pool
     * @param theDifficultyLevel the level of difficulty
     * Level 1 (Easy): the pool will contain 60% level 1 Monster (Gremlin), 40% Level 2 Monster (Skeleton), 10% level 3 Monster (Ogre); Total monster is nearly 50% the size of the map
     * Level 2 (Medium): the pool will contain 50% level 1 Monster (Gremlin), 30% Level 2 Monster (Skeleton), 20% level 3 Monster (Ogre); Total monster is nearly 75% the size of the map
     * Level 3 (Hard): the pool will contain 25% level 1 Monster (Gremlin), 35% Level 2 Monster (Skeleton), 40% level 3 Monster (Ogre); Total monster is nearly 100% the size of the map
     */

    private void loadTheMonster(final int theDifficultyLevel){
        int totalRoom = myMapHeight * myMapWidth - 1;//Minus entrance
        int totalMonster = 0;
        //Spawn rate of each type of monster
        double[] spawnRate = new double[3];
        switch (theDifficultyLevel){
            case 1 ->{
                spawnRate[0] = 0.6;
                spawnRate[1] = 0.4;
                spawnRate[2] = 0.1;
                totalMonster = totalRoom / 2;
            }
            case 2 ->{
                spawnRate[0] = 0.5;
                spawnRate[1] = 0.3;
                spawnRate[2] = 0.2;
                totalMonster = totalRoom * 3 / 4;
            }
            case 3 ->{
                spawnRate[0] = 0.25;
                spawnRate[1] = 0.35;
                spawnRate[2] = 0.4;
                totalMonster = totalRoom;
            }
        }

        int[] numberOfMonster = new int[spawnRate.length];
        //Fill the spawn pool
        for (int i = 0 ; i < numberOfMonster.length; i++){
            numberOfMonster[i] = (int) (spawnRate[i] * totalMonster);
            //Add the monster in
            for (int j = 0; j < numberOfMonster[i]; j++){
                myMonsterPool.add(MonsterFactory.createMonster(i + 1));
            }
        }

        //Shuffle the pool
        Collections.shuffle(myMonsterPool, DungeonAdventure.RANDOM_SEED);
    }

    /**
     * Populace the maze
     * Monsters won't be spawned in the entrance
     * Pillar can only be spawn in a room where there is a monster, there will always be 4 pillars
     * @return theMap with all the monsters and pillars populated
     */

    Room[][] populaceMaze(final int theDifficultyLevel){
        //Map is too small to generate anything
        if (myMapHeight * myMapWidth - 1 < 6){
            return null;
        }

        loadTheMonster(theDifficultyLevel);

        ArrayList<Integer> randomRoomPool = new ArrayList<>();

        //Push room in the entire maze to the pool
        for (int i = 0 ; i < myMapHeight; i++){
            for (int j = 0 ; j < myMapWidth; ++j){
                //Push if it is not the entrance
                if (myMap[i][j].getID() != myEntranceID)
                    randomRoomPool.add(myMap[i][j].getID());
            }
        }

        //Shuffle the pool
        Collections.shuffle(randomRoomPool, DungeonAdventure.RANDOM_SEED);

        //Add the pillar in the first 4 rooms
        for (int i = 0 ; i < 4; i++){
            int[] coordinate = Room.convertIDtoCoordinate(randomRoomPool.get(i));
            //Add a pillar here
            myMap[coordinate[0]][coordinate[1]].addRoomContent(PillarFactory.createPillar(i + 1));
        }

        //PLug the monster into the room
        for (int i = 0 ; i < myMonsterPool.size(); i++){
            int[] coordinate = Room.convertIDtoCoordinate(randomRoomPool.get(i));
            myMap[coordinate[0]][coordinate[1]].setMyGuardian(myMonsterPool.get(i));
        }

        return myMap;
    }
}