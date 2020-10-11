import java.util.ArrayList;
import java.util.Random;

public class Priest extends Character {

    public Priest(String name) {
        super(name);
    }

    void attack(Entity entityToAttack) {
        throw new UnsupportedOperationException("A priest can't attack");
    }

    void heal(Character characterToHeal) {
        ArrayList<Faction> factionsToHeal = new ArrayList<>(this.getFactions());
        for (Faction faction : this.getFactions()) {
            for (Faction friendFaction : faction.getFriends()) {
                factionsToHeal.add(friendFaction);
            }
        }
        if ((factionsToHeal.stream().filter(faction -> characterToHeal.getFactions().contains(faction))).count() == 0 && characterToHeal != this) {
            throw new UnsupportedOperationException("A character can only heal another character of his faction or friend faction");
        }
        Random random = new Random();
        characterToHeal.setHealth(Math.min(characterToHeal.getHealth() + random.nextInt(5) + 5, 100));
    }
}
