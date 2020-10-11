public class Entity {
    private final String name;
    private int health;

    public Entity(String name) {
        this.name = name;
        this.health = 100;
    }

    public int getHealth() {
        return health;
    }

    public boolean getIsAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
