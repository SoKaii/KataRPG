import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FactionTest {
    Faction orgrimmarFaction;
    Faction stormwindFaction;
    Faction darkspearFaction;
    Faction darnassusFaction;

    Character priestDarkspear;
    Character warriorDarkspear;
    Character priestOrgrimmar;
    Character warriorOrgrimmar;

    Character priestStormwind;
    Character warriorStormwind;
    Character priestDarnassus;
    Character warriorDarnassus;

    @BeforeEach
    void init() {
        // Horde factions
        orgrimmarFaction = new Faction("Orgrimmar");
        darkspearFaction = new Faction("Darkspear Trolls");

        // Alliance factions
        stormwindFaction = new Faction("Stormwind");
        darnassusFaction = new Faction("Darnassus");

        // Horde characters
        warriorOrgrimmar = new Warrior("Garrosh Hellscream");
        priestOrgrimmar = new Priest("Tyelis");
        warriorDarkspear = new Warrior("Paal'gajuk");
        priestDarkspear = new Priest("Talanji");

        // Alliance characters
        warriorStormwind = new Warrior("Varian Wrynn");
        priestStormwind = new Priest("Anduin Wrynn");
        warriorDarnassus = new Warrior("Sildanair");
        priestDarnassus = new Priest("Tyrande Whisperwind");
    }

    @Test
    @DisplayName("A faction have a name and a list of members")
    void factionExists() {
        ArrayList<Character> emptyList = new ArrayList<>();
        Assert.assertEquals("Orgrimmar", orgrimmarFaction.getName());
        Assert.assertEquals(emptyList, orgrimmarFaction.getMembers());
    }

    @Test
    @DisplayName("A character can be added to a faction")
    void factionAddMember() {
        ArrayList<Character> expectedList = new ArrayList<>();
        expectedList.add(warriorOrgrimmar);

        orgrimmarFaction.addMember(warriorOrgrimmar);
        Assert.assertEquals(expectedList,orgrimmarFaction.getMembers());
    }

    @Test
    @DisplayName("A character can be removed from a faction")
    void factionRemoveMember() {
        ArrayList<Character> expectedList = new ArrayList<>();
        expectedList.add(warriorOrgrimmar);

        orgrimmarFaction.addMember(warriorOrgrimmar);
        orgrimmarFaction.addMember(priestOrgrimmar);
        orgrimmarFaction.removeMember(priestOrgrimmar);
        Assert.assertEquals(expectedList,orgrimmarFaction.getMembers());
    }
}
