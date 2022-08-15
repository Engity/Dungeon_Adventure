/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
/**
 * Pillar
 * Need 4 to win the game
 * {@code @author:} Toan Nguyen, Thao Nguyen
 * @version 08 14 2022
 */
public class Pillar extends GameObjects {

    public Pillar(final String theItemName, final String theDescription) {
        super(theItemName, theDescription);

    }

    @Override
    public String toString() {
        return "Pillar " + getMyItemName();
    }
}

