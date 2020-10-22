public class Building implements Entity {

    private final String name;
    private int health;

    public Building(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

}

