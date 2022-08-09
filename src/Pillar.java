
public class Pillar extends GameObjects
{
    public String name;
    public Pillar (String myItem) {
        name = myItem;
    }
// Create 4 object to collect by the hero
    public static void main(String args[]) {
        Pillar obj1 = new Pillar("Abstraction");
        Pillar obj2 = new Pillar("Encapsulation");
        Pillar obj3 = new Pillar("Inheritance");
        Pillar obj4 = new Pillar("Polymorphism");


    }
}


