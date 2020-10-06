public class Main {
    public static void main(String[] args) {
        Character warrior1 = new Warrior("Je suis guerrier");
        Character priest1 = new Priest("Je suis prêtre");
        warrior1.attack(warrior1);
        System.out.println(warrior1.getName() + " -> " + warrior1.getHealth());
        priest1.attack(warrior1);
        System.out.println(warrior1.getName() + " -> " + warrior1.getHealth());
    }
}
