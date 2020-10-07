import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FactionTest {
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
    @DisplayName("A faction have a name and a list of members")
    void factionExists() {
        ArrayList<Character> emptyList = new ArrayList<>();
        Assert.assertEquals("Horde", hordeFaction.getName());
        Assert.assertEquals(emptyList, hordeFaction.getMembers());
    }

    @Test
    @DisplayName("A character can be added to a faction")
    void factionAddMember() {
        ArrayList<Character> expectedList = new ArrayList<>();
        expectedList.add(warriorHorde);

        hordeFaction.addMember(warriorHorde);
        Assert.assertEquals(expectedList,hordeFaction.getMembers());
    }

    @Test
    @DisplayName("A character can be removed from a faction")
    void factionRemoveMember() {
        ArrayList<Character> expectedList = new ArrayList<>();
        expectedList.add(warriorHorde);

        hordeFaction.addMember(warriorHorde);
        hordeFaction.addMember(priestHorde);
        hordeFaction.removeMember(priestHorde);
        Assert.assertEquals(expectedList,hordeFaction.getMembers());
    }
}
