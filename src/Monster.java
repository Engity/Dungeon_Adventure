public abstract class Monster extends DungeonCharacter {
    private double myChangeToHeal;
    private int myMinimumHealPoints;
    private int myMaximumHealPoints;

    public double getMyChangeToHeal() {
        return myChangeToHeal;
    }

    public void setMyChangeToHeal(double myChangeToHeal) {
        this.myChangeToHeal = myChangeToHeal;
    }

    public int getMyMinimumHealPoints() {
        return myMinimumHealPoints;
    }

    public void setMyMinimumHealPoints(int myMinimumHealPoints) {
        this.myMinimumHealPoints = myMinimumHealPoints;
    }

    public int getMyMaximumHealPoints() {
        return myMaximumHealPoints;
    }

    public void setMyMaximumHealPoints(int myMaximumHealPoints) {
        this.myMaximumHealPoints = myMaximumHealPoints;
    }

    protected Monster(String name) {
        super(name);
    }
}
