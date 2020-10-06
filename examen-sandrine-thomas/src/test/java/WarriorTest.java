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

    @Test
    @DisplayName("A warrior can kill another character")
    void warriorKills() {
        warrior2.setHealth(1);
        Assert.assertTrue(warrior2.getIsAlive());
        warrior.attack(warrior2);
        Assert.assertFalse(warrior2.getIsAlive());
    }

    @Test
    @DisplayName("A warrior can heals himself ")
    void warriorHealsHimself() {
        warrior.setHealth(50);
        warrior.heal(warrior);
        Assert.assertEquals(51,warrior.getHealth());
    }

    @Test
    @DisplayName("A warrior can't heals another character ")
    void warriorHealsAnotherCharacter() {
        try
        {
            warrior2.setHealth(50);
            warrior.heal(warrior2);
        }
        catch(RuntimeException re)
        {
            String expectedException = "A warrior can only heals himself";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("A warrior deals between 0 and 9 dammage ")
    void warriorDammages() {
        for(int i = 0; i < 50; i++) {
            warrior2.setHealth(50);
            warrior.attack(warrior2);
            Assert.assertTrue((50 - warrior2.getHealth() <= 9) && (50 - warrior2.getHealth() >= 0));
        }
    }
}
