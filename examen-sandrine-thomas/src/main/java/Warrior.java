import java.util.Random;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name);
    }

    @Override
    void attack(Character characterToAttack) {
        if (this.getFaction() != null &&
                (characterToAttack.getFaction() == this.getFaction() ||
                        this.getFaction().getFriends().contains(characterToAttack.getFaction()))) {
            throw new UnsupportedOperationException("A character can't attack another character of his faction or friend faction");
        }
        if (characterToAttack.getIsAlive()) {
            Random random = new Random();
            characterToAttack.setHealth(characterToAttack.getHealth() - (random.nextInt(8) + 1));
            if (characterToAttack.getHealth() <= 0) {
                characterToAttack.setHealth(0);
            }
        } else {
           throw new UnsupportedOperationException("A character can't attack a dead character");
        }
    }

    void heal(Character characterToHeal) {
        if (characterToHeal == this) {
            characterToHeal.setHealth(Math.min(this.getHealth() + 1, 100));
        } else {
            throw new UnsupportedOperationException("A warrior can only heals himself");
        }
    }

    @Override
    public String toString() {
        String factionName = this.getFaction() == null ? "None" : this.getFaction().getName();
        return this.getName() + " : Warrior\n"
                + "Health : " + this.getHealth() + "/100\n"
                + "Faction : " + factionName;
    }
}
