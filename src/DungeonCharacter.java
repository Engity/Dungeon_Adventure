/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */

import java.io.Serializable;

/**
 * Warrior class controller that extends Hero class
 * {@code @author:} Toan Nguyen, Justin Noel
 * @version 08 14 2022
 */

public class DungeonCharacter implements Serializable {
    private String myCharacterName;
    private double myHitPoints;
    private double myMaxHitPoints;
    private int myAttackSpeed;
    private int myOriginalAttackSpeed;
    private double myOriginalHitChance;
    private double myHitChance;

    private double myBlockChance;
    private int myDamageMin;
    private int myDamageMax;
    private String myClassName;

    /**
     *
     * @param theName
     * @param theHit
     * @param theAttack
     * @param theChance
     * @param theMin
     * @param theMax
     */

    public DungeonCharacter(final String theName, final double theHit, final int theAttack,
                            final double theChance, final int theMin, final int theMax) {
        this.setName(theName);

        this.setAttackSpeed(theAttack);
        this.setChanceToHit(theChance);
        this.setDamageMin(theMin);
        this.setDamageMax(theMax);

        setMaxHitPoints(theHit);
        setHitPoint(theHit);

        myOriginalHitChance = myHitChance;
        myOriginalAttackSpeed = myAttackSpeed;
    }

    /**
     * Getter for myClassName
     */
    String getClassName(){
        return myClassName;
    }

    /**
     * Setter for myClassName
     */

    void setClassName(final String theClassName){
        myClassName = theClassName;
    }

    void setDamageMax(final int theMax) {
        this.myDamageMax = theMax;
    }


    protected void setDamageMin(final int theMin) {
        this.myDamageMin = theMin;
    }


    protected void setChanceToHit(final double theChance) {
        this.myHitChance = theChance;
    }


    protected void setAttackSpeed(final int theAttack) {
        this.myAttackSpeed = theAttack;
    }


    protected void setHitPoint(final double theHit) {
        if(theHit < 0) {
            this.myHitPoints = 0;
        } else if(theHit > myMaxHitPoints) {
            this.myHitPoints = myMaxHitPoints;
        } else {
            myHitPoints = theHit;
        }

    }

    protected void setName(final String theName) {
        this.myCharacterName = theName;
    }


    protected String getCharacterName() {
        return myCharacterName;
    }

    protected double getHitPoints() {
        return myHitPoints;
    }

    protected int getAttackSpeed() {
        return myAttackSpeed;
    }

    protected double getMyHitChance() {
        return myHitChance;
    }

    protected int getDamageMin() {
        return myDamageMin;
    }

    protected int getMyDamageMax() {
        return myDamageMax;
    }

    protected double getMaxHitPoints() {
        return myMaxHitPoints;
    }

    protected void setMaxHitPoints(final double theMaxHitPoints) {
        this.myMaxHitPoints = theMaxHitPoints;
    }

    protected double getBlockChance() {
        return myBlockChance;
    }

    protected void setBlockChance(final double theBlockChance) {
        this.myBlockChance = theBlockChance;
    }

    void resetAttackSpeed(){
        myAttackSpeed = myOriginalAttackSpeed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder ();
        sb.append ("Name: ").append(myCharacterName).append("\n");

        if (myClassName != null) {
            sb.append("Class: ").append(myClassName).append("\n");
        }

        sb.append ("Hit Points: ").append((String.format("%.2f", myHitPoints))).append("\n");
        sb.append ("Attack Speed: ").append(myAttackSpeed).append("\n");
        sb.append ("Chance to hit: ").append(String.format("%.2f", myHitChance)).append("\n");

        if (myBlockChance > 0) {
            sb.append("Chance to block: ").append(String.format("%.2f", myBlockChance)).append("\n");
        }

        sb.append ("Minimum Damage: ").append(myDamageMin).append("\n");
        sb.append ("Maximum Damage: ").append(myDamageMax).append("\n");
        return sb.toString();
    }

    /**
     * Check to see if this character is still alive
     * @return true if hit point > 0, false otherwise
     */

    boolean isDead(){
        return (myHitPoints <= 0);
    }

    /**
     * Apply damage to this character, but also gave this character a chance to block or mitigate the damage
     * Won't go over 0
     * @param theDamage Damage this character should receive
     * @return the damage it actually applied
     */
    double applyDamage(final double theDamage){
        double rollTheDice = DungeonAdventure.RANDOM_SEED.nextDouble();
        double theActualDamage = theDamage;

        //Success to block case, minimize the damage
        if (rollTheDice <= myBlockChance){
            //Reduce damage if managed to block
            theActualDamage = 0;
        }

//        if (myHitPoints - theActualDamage < 0){
//            theActualDamage = myHitPoints;
//        }
//
//        myHitPoints -= theActualDamage;
        return applyTrueDamage(theActualDamage);

    }
    /**
     * Apply true damage to this character
     * Won't go over 0
     * @param theDamage Damage this character should receive
     * @return the damage it actually applied
     */
    double applyTrueDamage(final double theDamage){
        //The actual damage
        double theActualDamage = theDamage;
        if (myHitPoints - theDamage < 0){
            theActualDamage = myHitPoints;
        }

        myHitPoints -= theActualDamage;
        return theActualDamage;
    }

    /**
     * Increase health to this character
     * Won't go over myMaxHitPoints
     * @param theHealingAmount Health this character should receive
     */
    void increaseHP(final double theHealingAmount){
        myHitPoints = Math.min(myMaxHitPoints, myHitPoints + theHealingAmount);
    }

    /**
     * Return a random number between the range [myDamageMin, myDamageMax)
     * Will based on myHitChance if fails, return 0
     */
    double normalAttackStrike(){
        double rollTheDice = DungeonAdventure.RANDOM_SEED.nextDouble();
        //Randomize based on hit chance
        if (rollTheDice > myHitChance){
            return 0;
        }
        //Random the damage this character would inflict
        return DungeonAdventure.RANDOM_SEED.nextDouble(myDamageMin, myDamageMax);
    }

    /**
     * Loop reflecting attack speed (how many times this character can strike in a turn)
     * Call the singular attack methods to represent a strike
     * @return an array, showing the damage for each strike
     */
    double[] normalAttackMove(){
        double[] res = new double[myAttackSpeed];
        for (int i = 0 ; i < myAttackSpeed; i++){
            res[i] = Math.abs(Math.round(normalAttackStrike() * 100.0) / 100.0);
        }
        return res;
    }

}


