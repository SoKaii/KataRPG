import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Priest extends Character {

    public Priest(String name) {
        super(name);
    }

    void attack(Entity entityToAttack) {
        throw new UnsupportedOperationException("A priest can't attack");
    }

    void heal(Character characterToHeal) {
        List<Faction> factionsToHeal = new ArrayList<>(this.getFactions());
        for (Faction faction : this.getFactions()) {
            factionsToHeal.addAll(faction.getFriends());
        }
        if (factionsToHeal.stream().noneMatch(faction -> characterToHeal.getFactions().contains(faction)) && characterToHeal != this) {
            throw new UnsupportedOperationException("A character can only heal another character of his faction or friend faction");
        }
        Random random = new Random();
        characterToHeal.setHealth(Math.min(characterToHeal.getHealth() + random.nextInt(5) + 5, 100));
    }
}
