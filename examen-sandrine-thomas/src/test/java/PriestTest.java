import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PriestTest {
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
    @DisplayName("A priest can't attack")
    void priestAttack() {
        try
        {
           priestHorde.attack(warriorAlliance);
        }
        catch(RuntimeException re)
        {
            String expectedException = "A priest can't attack";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("A priest can heal another character of his faction between 5 and 10 hp ")
    void priestHealsAnotherCharacter() {
        priestHorde.joinFaction(hordeFaction);
        warriorHorde.joinFaction(hordeFaction);
        for(int i = 0; i < 50; i++) {
            warriorHorde.setHealth(50);
            priestHorde.heal(warriorHorde);
            Assert.assertTrue((warriorHorde.getHealth() - 50 <= 10) && (warriorHorde.getHealth() - 50 >= 5));
        }
    }

    @Test
    @DisplayName("A priest can't heal a character of another faction")
    void priestHealsACharacterOfAnotherFaction() {
        priestHorde.joinFaction(hordeFaction);
        warriorAlliance.joinFaction(allianceFaction);
        try
        {
            warriorAlliance.setHealth(50);
            priestHorde.heal(warriorAlliance);
        }
        catch(RuntimeException re)
        {
            String expectedException = "A character can only heal another character of the same faction";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("A priest can heal himself between 5 and 10 hp ")
    void priestHealsHimself() {
        for(int i = 0; i < 50; i++) {
            priestHorde.setHealth(50);
            priestHorde.heal(priestHorde);
            Assert.assertTrue((priestHorde.getHealth() - 50 <= 10) && (priestHorde.getHealth() - 50 >= 5));
        }
    }
}
