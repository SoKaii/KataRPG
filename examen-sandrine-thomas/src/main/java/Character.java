public abstract class Character extends Entity {
    private Faction faction;

    abstract void attack(Character characterToAttack);
    abstract void heal(Character characterToHeal);

    public Character(String name) {
        super(name);
        this.setHealth(100);
    }

    void joinFaction(Faction factionJoined) {
        if (this.faction == null) {
            factionJoined.addMember(this);
            this.faction = factionJoined;
        } else {
            throw new UnsupportedOperationException("This character already have a faction");
        }
    }

    void leaveFaction() {
        if (this.faction == null) {
            throw new UnsupportedOperationException("This character doesn't have a faction to leave");
        } else {
            this.faction.removeMember(this);
            this.faction = null;
        }
    }

    public Faction getFaction() {
        return faction;
    }
}
