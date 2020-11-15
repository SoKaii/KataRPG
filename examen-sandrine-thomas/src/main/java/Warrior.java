import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name);
    }

    @Override
    void attack(Entity entityToAttack) {
        if(entityToAttack instanceof Character) {
            Character characterToAttack = (Character)entityToAttack;
            List<Faction> factionsToNotAttack = new ArrayList<>(this.getFactions());
            for (Faction faction : this.getFactions()) {
                factionsToNotAttack.addAll(faction.getFriends());
            }
            if (this.getFactions() != null && factionsToNotAttack.contains(characterToAttack.getFactions())) {
                throw new UnsupportedOperationException("A character can't attack another character of his faction or friend faction");
            }
        }
        if (entityToAttack.isAlive()) {
            afflictDammage(entityToAttack);
        } else {
            throw new UnsupportedOperationException("A character can't attack a dead entity");

        }
    }

    private void afflictDammage(Entity entityToDammage) {
        Random random = new Random();
        entityToDammage.setHealth(Math.max(entityToDammage.getHealth() - (random.nextInt(8) + 1), 0));
    }

    void heal(Character characterToHeal) {
        if (characterToHeal == this) {
            characterToHeal.setHealth(Math.min(this.getHealth() + 1, 100));
        } else {
            throw new UnsupportedOperationException("A warrior can only heals himself");
        }
    }
}
