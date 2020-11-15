import java.util.ArrayList;
import java.util.List;

public abstract class Character implements Entity {
    private final List<Faction> factions;
    private Assembly assembly;
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

    void joinAssembly(Assembly assemblyJoined) {
        if(this.assembly != null) {
            throw new UnsupportedOperationException("This character already belong to an assembly");
        }
        if (!assemblyJoined.getAllowedRoles().contains(this.getClass())) {
            throw new UnsupportedOperationException("This character role is not allowed in this assembly");
        }
        assemblyJoined.addMember(this);
        this.assembly = assemblyJoined;
    }

    void leaveFaction(Faction factionToLeave) {
        if (this.factions.isEmpty()) {
            throw new UnsupportedOperationException("This character doesn't have a faction to leave");
        } else if (!this.factions.contains(factionToLeave)) {
            throw new UnsupportedOperationException("This character doesn't belong to this faction");
        } else {
            this.factions.remove(factionToLeave);
        }
    }

    void leaveAssembly(Assembly assemblyToLeave) {
        if (this.assembly == null) {
            throw new UnsupportedOperationException("This character doesn't have an assembly to leave");
        } else {
            this.assembly.removeMember(this);
            this.assembly = null;
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

    public List<Faction> getFactions() {
        return factions;
    }
}
