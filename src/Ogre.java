public class Ogre extends Monster{

    protected Ogre(String name) {
        super(name);
        this.setMyHitPoints(200);
        this.setMyAttackSpeed(2);
        this.setMyChangeToHit(0.6);
        this.setMyMinimumDamage(10);
        this.setMyMaximumDamage(25);
        this.setMyChangeToHeal(0.4);
        this.setMyMinimumHealPoints(20);
        this.setMyMaximumHealPoints(40);
    }
}
