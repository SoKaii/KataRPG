import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CharacterTest {
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
    @DisplayName("A character have a name, a health and is alive")
    void characterExist() {
        Assert.assertEquals("Garrosh Hellscream", warriorHorde.getName());
        Assert.assertEquals(100, warriorHorde.getHealth());
        Assert.assertTrue(warriorHorde.getIsAlive());
        Assert.assertEquals("Anduin Wrynn", priestAlliance.getName());
        Assert.assertEquals(100, priestAlliance.getHealth());
        Assert.assertTrue(priestAlliance.getIsAlive());
    }

    @Test
    @DisplayName("If a character have 0 hp, he's dead")
    void characterIsDead() {
        priestHorde.setHealth(0);
        Assert.assertFalse(priestHorde.getIsAlive());
        warriorHorde.setHealth(0);
        Assert.assertFalse(warriorHorde.getIsAlive());
    }

    @Test
    @DisplayName("A character can join a faction")
    void characterJoinFaction() {
        ArrayList<Character> expectedList = new ArrayList<>();
        Assert.assertEquals(expectedList, hordeFaction.getMembers());
        priestHorde.joinFaction(hordeFaction);
        expectedList.add(priestHorde);
        Assert.assertEquals(expectedList, hordeFaction.getMembers());
    }

    @Test
    @DisplayName("A character can't join a faction if he's already in an other")
    void characterJoinTwoFaction() {
        try
        {
            priestHorde.joinFaction(hordeFaction);
            priestHorde.joinFaction(allianceFaction);
        }
        catch(RuntimeException re)
        {
            String expectedException = "This character already have a faction";
            Assert.assertEquals(expectedException, re.getMessage());
        }

    }

    @Test
    @DisplayName("A character can leave a faction")
    void characterLeaveFaction() {
        ArrayList<Character> expectedList = new ArrayList<>();
        expectedList.add(priestHorde);
        priestHorde.joinFaction(hordeFaction);
        Assert.assertEquals(expectedList, hordeFaction.getMembers());

        priestHorde.leaveFaction();
        expectedList.remove(priestHorde);
        Assert.assertEquals(expectedList, hordeFaction.getMembers());
    }

    @Test
    @DisplayName("A character can't leave a faction if he's not in it")
    void characterLeaveNullFaction() {
        try
        {
           priestHorde.leaveFaction();
        }
        catch(RuntimeException re)
        {
            String expectedException = "This character doesn't have a faction to leave";
            Assert.assertEquals(expectedException, re.getMessage());
        }

    }
}
