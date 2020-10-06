import java.util.Random;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name);
    }

    @Override
    void attack(Character characterToAttack) {
        Random random = new Random();
        if (characterToAttack.getIsAlive()) {
            characterToAttack.setHealth(characterToAttack.getHealth() - random.nextInt(9));
            if (characterToAttack.getHealth() <= 0) {
                characterToAttack.setAlive(false);
                characterToAttack.setHealth(0);
            }
        } else {
           throw new UnsupportedOperationException("It seems useless to attack a dead character");
        }
    }

    void heal(Character characterToHeal) {
        if (characterToHeal == this) {
            characterToHeal.setHealth(Math.min(this.getHealth() + 1, 100));
        } else {
            throw new UnsupportedOperationException("A warrior can only heals himself");
        }
    }
}
