public abstract class Character {
    private final String name;
    private int health;
    private boolean isAlive;

    abstract void attack(Character characterToAttack);
    abstract void heal(Character characterToHeal);

    public Character(String name) {
        this.name = name;
        this.health = 100;
        this.isAlive = true;
    }

    public String getName() {
        return name;
    }

    public void setHealth(int health) {
        this.health = health;
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
}
