/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
/**
 * Vision Buff
 * Grant the player the ability to see through walls
 * Letting them know which access is locked for the 8 adjacent cells
 * {@code @author:} Toan Nguyen
 * @version 08 14 2022
 */
class VisionBuff extends GameObjects{
    private int myDuration;
    private static int DEFAULT_VISION_DURATION = 3;

    VisionBuff(){
        super("The Eye of a jealous spouse",
                "Grant the player the ability to see through walls. " +
                        "Letting them know which access is locked for the 8 adjacent cells");
        refreshDuration();
    }
    int getDuration() {
        return myDuration;
    }

    void setDuration(final int theDuration){
        myDuration = theDuration;
    }

    void refreshDuration(){
        myDuration = DEFAULT_VISION_DURATION;
    }

    void increaseDuration(){
        myDuration = Math.max(DEFAULT_VISION_DURATION, 1 + myDuration);
    }

    boolean useVisionBuff(){
        if (myDuration > 0){
            myDuration--;
            return true;
        }
        return false;
    }



}
