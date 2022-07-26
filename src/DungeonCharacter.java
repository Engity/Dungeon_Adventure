

public abstract class DungeonCharacter {
    private String myName;
    private int myHitPoints;

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public int getMyHitPoints() {
        return myHitPoints;
    }

    public void setMyHitPoints(int myHitPoints) {
        this.myHitPoints = myHitPoints;
    }

    public int getMyAttackSpeed() {
        return myAttackSpeed;
    }

    public void setMyAttackSpeed(int myAttackSpeed) {
        this.myAttackSpeed = myAttackSpeed;
    }

    public double getMyChangeToHit() {
        return myChangeToHit;
    }

    public void setMyChangeToHit(double myChangeToHit) {
        this.myChangeToHit = myChangeToHit;
    }

    public int getMyMinimumDamage() {
        return myMinimumDamage;
    }

    public void setMyMinimumDamage(int myMinimumDamage) {
        this.myMinimumDamage = myMinimumDamage;
    }

    public int getMyMaximumDamage() {
        return myMaximumDamage;
    }

    public void setMyMaximumDamage(int myMaximumDamage) {
        this.myMaximumDamage = myMaximumDamage;
    }

    private int myAttackSpeed;
    private double myChangeToHit;
    private int myMinimumDamage;
    private int myMaximumDamage;

    protected DungeonCharacter(String name) {
        this.myName = name;
    }

    // check hit point to see if character is alive
    public boolean isDeath() {

        if ((myHitPoints <= 0))
            return true;
        else
            return false;
    }
}

