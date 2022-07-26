public class Gremlin extends Monster{

    protected Gremlin(String name) {
        super(name);
        this.setMyHitPoints(80);
        this.setMyAttackSpeed(5);
        this.setMyChangeToHit(0.8);
        this.setMyMinimumDamage(10);
        this.setMyMaximumDamage(25);
        this.setMyChangeToHeal(0.4);
        this.setMyMinimumHealPoints(20);
        this.setMyMaximumHealPoints(40);
    }
}
