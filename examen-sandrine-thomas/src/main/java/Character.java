public abstract class Character {
    private final String name;
    private int health;
    private boolean isAlive;
    private Faction faction;

    abstract void attack(Character characterToAttack);
    abstract void heal(Character characterToHeal);

    public Character(String name) {
        this.name = name;
        this.health = 100;
        this.isAlive = true;
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

    public String getName() {
        return name;
    }

    public void setHealth(int health) {
        this.health = health;
        if (health == 0) {
            setAlive(false);
        }
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getHealth() {
        return health;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public Faction getFaction() {
        return faction;
    }
}
