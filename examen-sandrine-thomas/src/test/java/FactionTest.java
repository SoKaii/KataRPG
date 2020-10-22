import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class FactionTest {
    Faction orgrimmarFaction;
    Faction stormwindFaction;
    Faction darkspearFaction;
    Faction darnassusFaction;
    Faction ironforgeFaction;

    Character priestDarkspear;
    Character warriorDarkspear;
    Character priestOrgrimmar;
    Character warriorOrgrimmar;

    Character priestStormwind;
    Character warriorStormwind;
    Character priestDarnassus;
    Character warriorDarnassus;
    Character priestIronforge;
    Character warriorIronforge;

    @BeforeEach
    void init() {
        // Horde factions
        orgrimmarFaction = new Faction("Orgrimmar");
        darkspearFaction = new Faction("Darkspear Trolls");

        // Alliance factions
        stormwindFaction = new Faction("Stormwind");
        darnassusFaction = new Faction("Darnassus");
        ironforgeFaction = new Faction("Ironforge");

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
        warriorIronforge = new Warrior("Muradin Bronzebeard");
        priestIronforge = new Priest("Moira Thaurissan");
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

    @Test
    @DisplayName("A Faction can declare one friend")
    void factionAddOneFriend(){
        ArrayList<Faction> expectedList = new ArrayList<>();
        expectedList.add(darnassusFaction);

        stormwindFaction.addFriend(darnassusFaction);
        Assert.assertEquals(expectedList, stormwindFaction.getFriends());
    }

    @Test
    @DisplayName("A Faction can declare multiple friends")
    void factionAddMultipleFriend(){
        ArrayList<Faction> expectedList = new ArrayList<>();
        expectedList.add(darnassusFaction);
        expectedList.add(ironforgeFaction);

        stormwindFaction.addFriend(darnassusFaction);
        stormwindFaction.addFriend(ironforgeFaction);
        Assert.assertEquals(expectedList, stormwindFaction.getFriends());
    }

    @Test
    @DisplayName("A Faction can't declare itself as friend")
    void factionAddItselfAsFriend(){
        ArrayList<Faction> expectedList = new ArrayList<>();
        expectedList.add(stormwindFaction);

        try {
            stormwindFaction.addFriend(stormwindFaction);
            Assert.assertEquals(expectedList, stormwindFaction.getFriends());
        }
        catch (RuntimeException re) {
            String expectedException = "A faction can't add itself as friend faction";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("A Faction can remove one friend")
    void factionRemoveOneFriend(){
        ArrayList<Faction> expectedList = new ArrayList<>();
        expectedList.add(darnassusFaction);

        stormwindFaction.addFriend(darnassusFaction);
        stormwindFaction.addFriend(ironforgeFaction);
        stormwindFaction.removeFriend(ironforgeFaction);
        Assert.assertEquals(expectedList, stormwindFaction.getFriends());
    }

    @Test
    @DisplayName("A character may belong to one or more factions")
    void belongToMultipleFactions(){
        ArrayList<Object> expectedList = new ArrayList<>();
        expectedList.add(warriorOrgrimmar);

        warriorOrgrimmar.joinFaction(orgrimmarFaction);
        Assert.assertEquals(orgrimmarFaction.getMembers(), expectedList);

        warriorOrgrimmar.joinFaction(darkspearFaction);
        Assert.assertEquals(darkspearFaction.getMembers(), expectedList);
        warriorOrgrimmar.joinFaction(darnassusFaction);
        Assert.assertEquals(darnassusFaction.getMembers(), expectedList);

        expectedList.clear();
        expectedList.addAll(Arrays.asList(orgrimmarFaction, darkspearFaction, darnassusFaction));
        Assert.assertEquals(warriorOrgrimmar.getFactions(), expectedList);
    }
}
