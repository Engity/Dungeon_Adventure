/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import org.junit.jupiter.api.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the main controller DungeonAdventure
 * {@code @author:} Toan Nguyen
 *  @version 07 29 2022
 */
public class DungeonAdventureTest {
    /**
     * Instance of Dungeon Adventure
     */
    private static final DungeonAdventure GAME_CONTROLLER = DungeonAdventure.getInstance();
    private static final int [] DX = new int[]{-1, 0, 1, 0};
    private static final int [] DY = new int[]{ 0, 1, 0, -1};

    private Room myPlayerCurrentRoom;

    @BeforeAll
    public static void setupForAll(){
        GAME_CONTROLLER.init();
    }

    @BeforeEach
    public void setup(){
        myPlayerCurrentRoom = GAME_CONTROLLER.getMyCurrentRoom();
    }

    /**
     * Test method for {@link DungeonAdventure#movePlayer(int)}.
     * Move the player in all 4 directions
     */
    @Test
    void testMovingInAllDirections() {
        //Set the starting position in the middle
        int myStartingPositionY = GAME_CONTROLLER.getMapSizeWidth() / 2;
        int myStartingPositionX = GAME_CONTROLLER.getMapSizeHeight() / 2;
        GAME_CONTROLLER.setPlayerPosition(myStartingPositionX, myStartingPositionY);
        myPlayerCurrentRoom = GAME_CONTROLLER.getMyCurrentRoom();

        for (int direction = 0 ; direction < 4; direction++) {
            int mySupposedX = myStartingPositionX + DX[direction];
            int mySupposedY = myStartingPositionY + DY[direction];

            //Check the visited status
            assertFalse(GAME_CONTROLLER.getRoomVisitedStatus(mySupposedX, mySupposedY));

            //Move the player
            GAME_CONTROLLER.movePlayer(direction);
            int[] actualCoordinate = Room.convertIDtoCoordinate(GAME_CONTROLLER.getMyCurrentRoom().getID());

            //Check the location
            assertEquals(mySupposedX, actualCoordinate[0]);
            assertEquals(mySupposedY, actualCoordinate[1]);

            //Check the visited status
            assertTrue(GAME_CONTROLLER.getRoomVisitedStatus(mySupposedX, mySupposedY));

            //Reset player position
            GAME_CONTROLLER.setPlayerPosition(myStartingPositionX, myStartingPositionY);
        }
    }

    /**
     * Test method for {@link DungeonAdventure#movePlayer(int)}.
     * Move the player at NorthWest corner
     */
    @Test
    void testMovingNorthWestCorner() {
        int currentPosX = 0;
        int currentPosY = 0;
        GAME_CONTROLLER.setPlayerPosition(currentPosX, currentPosY);

        for (int direction = 0 ; direction < 4; direction += 3) {

            //Move the player
            GAME_CONTROLLER.movePlayer(direction);
            int[] actualCoordinate = Room.convertIDtoCoordinate(GAME_CONTROLLER.getMyCurrentRoom().getID());

            //Check the location, it should remain the same
            assertEquals(currentPosX, actualCoordinate[0]);
            assertEquals(currentPosY, actualCoordinate[1]);

            //Reset player position
            GAME_CONTROLLER.setPlayerPosition(currentPosX, currentPosY);
        }
    }

    /**
     * Test method for {@link DungeonAdventure#movePlayer(int)}.
     * Move the player at SouthWest corner
     */
    @Test
    void testMovingSouthWestCorner() {
        int currentPosX = GAME_CONTROLLER.getMapSizeHeight() - 1;
        int currentPosY = 0;
        GAME_CONTROLLER.setPlayerPosition(currentPosX, currentPosY);

        for (int direction = 2 ; direction < 4; direction ++) {

            //Move the player
            GAME_CONTROLLER.movePlayer(direction);
            int[] actualCoordinate = Room.convertIDtoCoordinate(GAME_CONTROLLER.getMyCurrentRoom().getID());

            //Check the location, it should remain the same
            assertEquals(currentPosX, actualCoordinate[0]);
            assertEquals(currentPosY, actualCoordinate[1]);

            //Reset player position
            GAME_CONTROLLER.setPlayerPosition(currentPosX, currentPosY);
        }
    }

    /**
     * Test method for {@link DungeonAdventure#movePlayer(int)}.
     * Move the player at NorthEast corner
     */
    @Test
    void testMovingNorthEastCorner() {
        int currentPosX = 0;
        int currentPosY = GAME_CONTROLLER.getMapSizeWidth() - 1;
        GAME_CONTROLLER.setPlayerPosition(currentPosX, currentPosY);

        for (int direction = 0 ; direction < 2; direction++) {

            //Move the player
            GAME_CONTROLLER.movePlayer(direction);
            int[] actualCoordinate = Room.convertIDtoCoordinate(GAME_CONTROLLER.getMyCurrentRoom().getID());

            //Check the location, it should remain the same
            assertEquals(currentPosX, actualCoordinate[0]);
            assertEquals(currentPosY, actualCoordinate[1]);

            //Reset player position
            GAME_CONTROLLER.setPlayerPosition(currentPosX, currentPosY);
        }
    }

    /**
     * Test method for {@link DungeonAdventure#movePlayer(int)}.
     * Move the player at SouthEast corner
     */
    @Test
    void testMovingSouthEastCorner() {
        int currentPosX = GAME_CONTROLLER.getMapSizeHeight() - 1;
        int currentPosY = GAME_CONTROLLER.getMapSizeWidth() - 1;
        GAME_CONTROLLER.setPlayerPosition(currentPosX, currentPosY);

        for (int direction = 1 ; direction < 3; direction ++) {

            //Move the player
            GAME_CONTROLLER.movePlayer(direction);
            int[] actualCoordinate = Room.convertIDtoCoordinate(GAME_CONTROLLER.getMyCurrentRoom().getID());

            //Check the location, it should remain the same
            assertEquals(currentPosX, actualCoordinate[0]);
            assertEquals(currentPosY, actualCoordinate[1]);

            //Reset player position
            GAME_CONTROLLER.setPlayerPosition(currentPosX, currentPosY);
        }
    }

    /**
     * Test method for {@link DungeonAdventure#randomizeMap()} .
     * Test to see whether the algorithm can produce a maze  where any room is accessible
     * Basically testing whether the maze is a strongly connected graph
     * Use BFS to traversal the map and keep tracks of how many room it has visited
     */
    @Test
    void testRandomize_AccessAllRoomFromStartingPosition() {
        int numberOfVisitedRoom = 0;
        //Init and randomize the map
        GAME_CONTROLLER.init();
        GAME_CONTROLLER.randomizeMap();

        //Init
        LinkedList<Integer> queue = new LinkedList<>();
        boolean [][]visited = new boolean[GAME_CONTROLLER.getMapSizeHeight()][GAME_CONTROLLER.getMapSizeWidth()];
        int currentRoomID = GAME_CONTROLLER.getMyCurrentRoom().getID();

        //Push the starting room to
        queue.push(currentRoomID);
        int[] currentLocation = Room.convertIDtoCoordinate(currentRoomID);
        visited[currentLocation[0]][currentLocation[1]] = true;
        numberOfVisitedRoom++;

        //Start traversing
        while (!queue.isEmpty()){
            currentRoomID = queue.pop();
            currentLocation = Room.convertIDtoCoordinate(currentRoomID);
            int currentAccessCode = GAME_CONTROLLER.getRoomAccessCode(currentLocation[0], currentLocation[1]);

            for (int direction = 0; direction < 4; direction++){
                //Checking whether the direction can be accessed
                if (((currentAccessCode >> direction) & 1) == 1){
                    int newLocationX = currentLocation[0] + DX[direction];
                    int newLocationY = currentLocation[1] + DY[direction];

                    if (!visited[newLocationX][newLocationY]){
                        numberOfVisitedRoom++;
                        visited[newLocationX][newLocationY] = true;
                        queue.push(Room.convertCoordinateToID(newLocationX, newLocationY));
                    }
                }
            }
        }
        //Check to see if we have visited all the rooms by checking whether the number visited room equals the size of the map
        assertEquals(numberOfVisitedRoom, GAME_CONTROLLER.getMapSizeWidth() * GAME_CONTROLLER.getMapSizeHeight());
    }


}