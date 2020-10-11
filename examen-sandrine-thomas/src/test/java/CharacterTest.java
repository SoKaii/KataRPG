import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CharacterTest {
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
    @DisplayName("A character have a name, a health and is alive")
    void characterExist() {
        Assert.assertEquals("Garrosh Hellscream", warriorOrgrimmar.getName());
        Assert.assertEquals(100, warriorOrgrimmar.getHealth());
        Assert.assertTrue(warriorOrgrimmar.getIsAlive());
        Assert.assertEquals("Anduin Wrynn", priestStormwind.getName());
        Assert.assertEquals(100, priestStormwind.getHealth());
        Assert.assertTrue(priestStormwind.getIsAlive());
    }

    @Test
    @DisplayName("If a character have 0 hp, he's dead")
    void characterIsDead() {
        priestDarkspear.setHealth(0);
        Assert.assertFalse(priestDarkspear.getIsAlive());
        warriorOrgrimmar.setHealth(0);
        Assert.assertFalse(warriorOrgrimmar.getIsAlive());
    }

    @Test
    @DisplayName("A character can join a faction")
    void characterJoinFaction() {
        ArrayList<Character> expectedList = new ArrayList<>();
        Assert.assertEquals(expectedList, orgrimmarFaction.getMembers());
        priestDarkspear.joinFaction(orgrimmarFaction);
        expectedList.add(priestDarkspear);
        Assert.assertEquals(expectedList, orgrimmarFaction.getMembers());
    }

    @Test
    @DisplayName("A character can't join a faction if he's already in an other")
    void characterJoinTwoFaction() {
        try
        {
            priestDarkspear.joinFaction(darkspearFaction);
            priestDarkspear.joinFaction(orgrimmarFaction);
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
        expectedList.add(priestDarkspear);
        priestDarkspear.joinFaction(darkspearFaction);
        Assert.assertEquals(expectedList, darkspearFaction.getMembers());

        priestDarkspear.leaveFaction();
        expectedList.remove(priestDarkspear);
        Assert.assertEquals(expectedList, orgrimmarFaction.getMembers());
    }

    @Test
    @DisplayName("A character can't leave a faction if he's not in it")
    void characterLeaveNullFaction() {
        try
        {
           priestDarkspear.leaveFaction();
        }
        catch(RuntimeException re)
        {
            String expectedException = "This character doesn't have a faction to leave";
            Assert.assertEquals(expectedException, re.getMessage());
        }

    }
}
