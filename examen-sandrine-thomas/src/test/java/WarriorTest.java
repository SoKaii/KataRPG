import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WarriorTest {
    Character warrior;
    Character warrior2;

    @BeforeEach
    void init() {
        warrior = new Warrior("Warrior");
        warrior2 = new Warrior("Warrior2");
    }

    @Test
    @DisplayName("A warrior have a name, a health and is alive")
    void warriorExists() {
        Assert.assertEquals("Warrior", warrior.getName());
        Assert.assertEquals(100, warrior.getHealth());
        Assert.assertTrue(warrior.getIsAlive());
    }

    @Test
    @DisplayName("A warrior can attack another character")
    void warriorAttacks() {
        warrior.attack(warrior2);
        Assert.assertTrue(warrior2.getHealth() <= 100);
    }
}
