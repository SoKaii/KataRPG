import java.util.ArrayList;

public abstract class Character implements Entity {
    private final ArrayList<Faction> factions;
    private final String name;
    private int health;

    abstract void attack(Entity entityToAttack);
    abstract void heal(Character characterToHeal);

    public Character(String name) {
        this.name = name;
        this.health = 100;
        this.factions = new ArrayList<>();
    }

    void joinFaction(Faction factionJoined) {
        if(this.factions.contains(factionJoined)) {
            throw new UnsupportedOperationException("Faction already joined");
        }
        factionJoined.addMember(this);
        this.factions.add(factionJoined);
    }

    void leaveFaction(Faction factionToLeave) {
        if (this.factions.isEmpty()) {
            throw new UnsupportedOperationException("This character doesn't have a faction to leave");
        } else {
            this.factions.remove(factionToLeave);
        }
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Faction> getFactions() {
        return factions;
    }
}
