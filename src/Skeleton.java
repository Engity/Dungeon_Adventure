public class Skeleton extends Monster{
    protected Skeleton(String name) {
        super(name);
        this.setMyHitPoints(110);
        this.setMyAttackSpeed(3);
        this.setMyChangeToHit(0.8);
        this.setMyMinimumDamage(20);
        this.setMyMaximumDamage(60);
        this.setMyChangeToHeal(0.2);
        this.setMyMinimumHealPoints(20);
        this.setMyMaximumHealPoints(30);
    }
}
