public class Entity {

    private int health;
    private boolean isAlive;

    public Entity() {
        this.health = 100;
        this.isAlive = true;
    }

    public int getHealth() {
        return health;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
