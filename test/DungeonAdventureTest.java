/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the main controller DungeonAdventure
 * {@code @author:} Toan Nguyen
 *  @version 07 29 2022
 */
public class DungeonAdventureTest {
    /**
     * Instance of Dungeon Adventure
     */
    private static final DungeonAdventure GAME_CONTROLLER = DungeonAdventure.getInstance();;
    private static final int [] DX = new int[]{-1, 0, 1, 0};
    private static final int [] DY = new int[]{ 0, 1, 0, -1};

    private int myStartingPositionX, myStartingPositionY;
    private Room myPlayerCurrentRoom;

    @BeforeAll
    public static void setupForAll(){
        GAME_CONTROLLER.init();
    }

    @BeforeEach
    public void setup(){

        //Set the starting position in the middle
        myStartingPositionY = GAME_CONTROLLER.getMapSizeWidth() / 2;
        myStartingPositionX = GAME_CONTROLLER.getMapSizeHeight() / 2;
        GAME_CONTROLLER.setPlayerPosition(myStartingPositionX, myStartingPositionY);
        myPlayerCurrentRoom = GAME_CONTROLLER.getMyCurrentRoom();
    }

    /**
     * Test method for {@link DungeonAdventure#movePlayer(int)}.
     * Move the player in all 4 directions
     */
    @Test
    void testMovingInAllDirections() {
        myPlayerCurrentRoom = GAME_CONTROLLER.getMyCurrentRoom();
        int currentPosX = myStartingPositionX;
        int currentPosY = myStartingPositionY;

        for (int direction = 0 ; direction < 4; direction++) {
            int mySupposedX = currentPosX + DX[direction];
            int mySupposedY = currentPosY + DY[direction];

            //Check the visited status
            assertEquals(false, GAME_CONTROLLER.getRoomVisitedStatus(mySupposedX, mySupposedY));

            //Move the player
            GAME_CONTROLLER.movePlayer(direction);
            int myActuallyX = GAME_CONTROLLER.getMyCurrentRoom().getID() / GAME_CONTROLLER.getMapSizeWidth();
            int myActuallyY = GAME_CONTROLLER.getMyCurrentRoom().getID() % GAME_CONTROLLER.getMapSizeWidth();

            //Check the location
            assertEquals(mySupposedX, myActuallyX);
            assertEquals(mySupposedY, myActuallyY);



            //Check the visited status
            assertEquals(true, GAME_CONTROLLER.getRoomVisitedStatus(mySupposedX, mySupposedY));

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
            int mySupposedX = currentPosX;
            int mySupposedY = currentPosY;

            //Move the player
            GAME_CONTROLLER.movePlayer(direction);
            int myActuallyX = GAME_CONTROLLER.getMyCurrentRoom().getID() / GAME_CONTROLLER.getMapSizeWidth();
            int myActuallyY = GAME_CONTROLLER.getMyCurrentRoom().getID() % GAME_CONTROLLER.getMapSizeWidth();

            //Check the location, it should remain the same
            assertEquals(mySupposedX, myActuallyX);
            assertEquals(mySupposedY, myActuallyY);

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
            int mySupposedX = currentPosX;
            int mySupposedY = currentPosY;

            //Move the player
            GAME_CONTROLLER.movePlayer(direction);
            int myActuallyX = GAME_CONTROLLER.getMyCurrentRoom().getID() / GAME_CONTROLLER.getMapSizeWidth();
            int myActuallyY = GAME_CONTROLLER.getMyCurrentRoom().getID() % GAME_CONTROLLER.getMapSizeWidth();

            //Check the location, it should remain the same
            assertEquals(mySupposedX, myActuallyX);
            assertEquals(mySupposedY, myActuallyY);

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
            int mySupposedX = currentPosX;
            int mySupposedY = currentPosY;

            //Move the player
            GAME_CONTROLLER.movePlayer(direction);
            int myActuallyX = GAME_CONTROLLER.getMyCurrentRoom().getID() / GAME_CONTROLLER.getMapSizeWidth();
            int myActuallyY = GAME_CONTROLLER.getMyCurrentRoom().getID() % GAME_CONTROLLER.getMapSizeWidth();

            //Check the location, it should remain the same
            assertEquals(mySupposedX, myActuallyX);
            assertEquals(mySupposedY, myActuallyY);

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
            int mySupposedX = currentPosX;
            int mySupposedY = currentPosY;

            //Move the player
            GAME_CONTROLLER.movePlayer(direction);
            int myActuallyX = GAME_CONTROLLER.getMyCurrentRoom().getID() / GAME_CONTROLLER.getMapSizeWidth();
            int myActuallyY = GAME_CONTROLLER.getMyCurrentRoom().getID() % GAME_CONTROLLER.getMapSizeWidth();

            //Check the location, it should remain the same
            assertEquals(mySupposedX, myActuallyX);
            assertEquals(mySupposedY, myActuallyY);

            //Reset player position
            GAME_CONTROLLER.setPlayerPosition(currentPosX, currentPosY);
        }
    }
}