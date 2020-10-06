import java.util.Random;

public class Priest extends Character {

    public Priest(String name) {
        super(name);
    }

    void attack(Character characterToAttack) {
        throw new UnsupportedOperationException("A priest can't attack");
    }

    void heal(Character characterToHeal) {
        Random random = new Random();
        characterToHeal.setHealth(Math.min(characterToHeal.getHealth() + random.nextInt(5) + 5, 100));
    }
}
