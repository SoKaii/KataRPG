import java.util.Random;

public class Priest extends Character {

    public Priest(String name) {
        super(name);
    }

    void attack(Character characterToAttack) {
        throw new UnsupportedOperationException("A priest can't attack");
    }

    void heal(Character characterToHeal) {
        if (characterToHeal.getFaction() != this.getFaction() && (!characterToHeal.getFaction().getFriends().contains(characterToHeal.getFaction()))) {
            throw new UnsupportedOperationException("A character can only heal another character of his faction or friend faction");
        }
        Random random = new Random();
        characterToHeal.setHealth(Math.min(characterToHeal.getHealth() + random.nextInt(5) + 5, 100));
    }

    @Override
    public String toString() {
        String factionName = this.getFaction() == null ? "None" : this.getFaction().getName();
        return this.getName() + " : Priest\n"
                + "Health : " + this.getHealth() + "/100\n"
                + "Faction : " + factionName;
    }
}
