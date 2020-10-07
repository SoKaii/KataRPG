import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WarriorTest {
    Faction hordeFaction;
    Faction allianceFaction;
    Character priestHorde;
    Character warriorHorde;
    Character priestAlliance;
    Character warriorAlliance;

    @BeforeEach
    void init() {
        hordeFaction = new Faction("Horde");
        warriorHorde = new Warrior("Garrosh Hellscream");
        priestHorde = new Priest("Talanji");
        allianceFaction = new Faction("Alliance");
        warriorAlliance = new Warrior("Varian Wrynn");
        priestAlliance = new Priest("Anduin Wrynn");
    }

    @Test
    @DisplayName("A warrior can attack a character of another faction")
    void warriorAttacks() {
        warriorHorde.joinFaction(hordeFaction);
        priestAlliance.joinFaction(allianceFaction);
        warriorHorde.attack(priestAlliance);
        Assert.assertTrue(priestAlliance.getHealth() < 100);
    }

    @Test
    @DisplayName("A warrior can't attack a character of his faction")
    void warriorAttacksFaction() {
        warriorHorde.joinFaction(hordeFaction);
        priestHorde.joinFaction(hordeFaction);
        try {
            warriorHorde.attack(priestHorde);
            Assert.assertTrue(priestHorde.getHealth() < 100);
        }
        catch (RuntimeException re) {
            String expectedException = "A character can't attack another character of the same faction";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("A warrior can kill a character of another faction")
    void warriorKills() {
        warriorHorde.joinFaction(hordeFaction);
        priestAlliance.joinFaction(allianceFaction);
        priestAlliance.setHealth(1);
        Assert.assertTrue(priestAlliance.getIsAlive());
        warriorHorde.attack(priestAlliance);
        Assert.assertFalse(priestAlliance.getIsAlive());
    }

    @Test
    @DisplayName("A warrior can heals himself ")
    void warriorHealsHimself() {
        warriorHorde.setHealth(50);
        warriorHorde.heal(warriorHorde);
        Assert.assertEquals(51,warriorHorde.getHealth());
    }

    @Test
    @DisplayName("A warrior can't heals another character ")
    void warriorHealsAnotherCharacter() {
        try
        {
            priestHorde.setHealth(50);
            warriorHorde.heal(priestHorde);
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
            priestAlliance.setHealth(50);
            warriorHorde.attack(priestAlliance);
            Assert.assertTrue((50 - priestAlliance.getHealth() <= 9) && (50 - priestAlliance.getHealth() >= 0));
        }
    }
}
