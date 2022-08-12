/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the controller Population
 * {@code @author:} Toan Nguyen
 *  @version 08 12 2022
 */

class PopulationControllerTest {
    /**
     * Instance of Dungeon Adventure
     */
    private static final PopulationController POPULATION_CONTROLLER = PopulationController.getInstance();

    private Room[][] myMap;
    private final static int MAP_WIDTH = 4, MAP_HEIGHT = 4;
    private final static int ENTRANCE = Room.convertCoordinateToID(MAP_HEIGHT / 2, MAP_WIDTH / 2);//Set the entrance at the center

    @BeforeEach
    public void setup(){
        myMap = new Room [MAP_HEIGHT][MAP_WIDTH];

        int id = 0;
        for (int i = 0 ; i < MAP_HEIGHT; i++){
            myMap[i] = new Room[MAP_WIDTH];

            for (int j = 0 ; j < MAP_WIDTH; ++j){
                myMap[i][j] = new Room(id);
                ++id;
            }
        }

        POPULATION_CONTROLLER.loadTheMaze(MAP_HEIGHT, MAP_WIDTH, ENTRANCE, myMap);
    }

    /**
     * Test method for {@link PopulationController#populaceMaze(int)}.
     * Test to see if the map has exactly 4 pillars
     */
    @Test
    void testPopulaceMaze_FourPillars() {
        myMap = PopulationController.getInstance().populaceMaze(3);
        int amountOfPillars = 0;
        for (int i = 0 ;  i < MAP_HEIGHT; i++){
            for (int j = 0 ;  j < MAP_WIDTH; j++){
                var lootBag = myMap[i][j].retrieveLoot();
                for (var item:lootBag){
                    if (item.getClass() == Pillar.class){
                        amountOfPillars++;
                    }
                }

            }
        }

        assertEquals(4, amountOfPillars);
    }

    /**
     * Test method for {@link PopulationController#populaceMaze(int)}.
     * Test to see if the map contains 60% level 1 Monster (Gremlin), 40% Level 2 Monster (Skeleton), 10% level 3 Monster (Ogre); Total monster is nearly 50% the size of the map
     */
    @Test
    void testPopulaceMaze_MonsterDifficultyEasy() {
        myMap = PopulationController.getInstance().populaceMaze(1);
        int totalAmountOfMonster = 0;
        int[] amountOfMonster = new int[3];
        int [] supposedAmountOfMonster = {4,2,0};

        //Loop the map and count
        for (int i = 0 ;  i < MAP_HEIGHT; i++){
            for (int j = 0 ;  j < MAP_WIDTH; j++){
                Monster guardian = myMap[i][j].getMyGuardian();
                //Check to see if there is a monster in the room
                if (guardian != null){
                    switch (guardian.getMyCharacterName()){
                        case "Gremlin"->{
                            amountOfMonster[0]++;
                        }
                        case "Skeleton"->{
                            amountOfMonster[1]++;
                        }
                        case "Ogre"->{
                            amountOfMonster[2]++;
                        }
                    }
                    ++totalAmountOfMonster;
                }
            }
        }

        assertEquals(6, totalAmountOfMonster);
        assertArrayEquals(supposedAmountOfMonster, amountOfMonster);
    }

    /**
     * Test method for {@link PopulationController#populaceMaze(int)}.
     * Test to see if the map contains 50% level 1 Monster (Gremlin), 30% Level 2 Monster (Skeleton), 20% level 3 Monster (Ogre); Total monster is nearly 75% the size of the map
     */
    @Test
    void testPopulaceMaze_MonsterDifficultyMedium() {
        myMap = PopulationController.getInstance().populaceMaze(2);
        int totalAmountOfMonster = 0;
        int[] amountOfMonster = new int[3];
        int [] supposedAmountOfMonster = {5,3,2};

        //Loop the map and count
        for (int i = 0 ;  i < MAP_HEIGHT; i++){
            for (int j = 0 ;  j < MAP_WIDTH; j++){
                Monster guardian = myMap[i][j].getMyGuardian();
                //Check to see if there is a monster in the room
                if (guardian != null){
                    switch (guardian.getMyCharacterName()){
                        case "Gremlin"->{
                            amountOfMonster[0]++;
                        }
                        case "Skeleton"->{
                            amountOfMonster[1]++;
                        }
                        case "Ogre"->{
                            amountOfMonster[2]++;
                        }
                    }
                    ++totalAmountOfMonster;
                }
            }
        }

        assertEquals(10, totalAmountOfMonster);
        assertArrayEquals(supposedAmountOfMonster, amountOfMonster);
    }

    /**
     * Test method for {@link PopulationController#populaceMaze(int)}.
     * Test to see if the map contains 25% level 1 Monster (Gremlin), 35% Level 2 Monster (Skeleton), 40% level 3 Monster (Ogre); Total monster is nearly 100% the size of the map
     */
    @Test
    void testPopulaceMaze_MonsterDifficultyHard() {
        myMap = PopulationController.getInstance().populaceMaze(3);
        int totalAmountOfMonster = 0;
        int[] amountOfMonster = new int[3];
        int [] supposedAmountOfMonster = {3,5,6};

        //Loop the map and count
        for (int i = 0 ;  i < MAP_HEIGHT; i++){
            for (int j = 0 ;  j < MAP_WIDTH; j++){
                Monster guardian = myMap[i][j].getMyGuardian();
                //Check to see if there is a monster in the room
                if (guardian != null){
                    switch (guardian.getMyCharacterName()){
                        case "Gremlin"->{
                            amountOfMonster[0]++;
                        }
                        case "Skeleton"->{
                            amountOfMonster[1]++;
                        }
                        case "Ogre"->{
                            amountOfMonster[2]++;
                        }
                    }
                    ++totalAmountOfMonster;
                }
            }
        }

        assertEquals(14, totalAmountOfMonster);
        assertArrayEquals(supposedAmountOfMonster, amountOfMonster);
    }
}