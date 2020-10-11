import java.util.ArrayList;

public abstract class Character extends Entity {
    private ArrayList<Faction> factions;

    abstract void attack(Entity entityToAttack);
    abstract void heal(Character characterToHeal);

    public Character(String name) {
        super(name);
        this.setHealth(100);
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

    public ArrayList<Faction> getFactions() {
        return factions;
    }
}
