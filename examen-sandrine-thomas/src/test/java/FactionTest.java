import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactionTest extends Data{

    @BeforeEach
    void initData() {
        super.init();
    }

    @Test
    @DisplayName("A faction have a name and a list of members")
    void factionExists() {
        List<Character> emptyList = new ArrayList<>();
        Assert.assertEquals("Orgrimmar", orgrimmarFaction.getName());
        Assert.assertEquals(emptyList, orgrimmarFaction.getMembers());
    }

    @Test
    @DisplayName("A character can be added to a faction")
    void factionAddMember() {
        List<Character> expectedList = new ArrayList<>();
        expectedList.add(warriorOrgrimmar);

        orgrimmarFaction.addMember(warriorOrgrimmar);
        Assert.assertEquals(expectedList,orgrimmarFaction.getMembers());
    }

    @Test
    @DisplayName("A character can be removed from a faction")
    void factionRemoveMember() {
        List<Character> expectedList = new ArrayList<>();
        expectedList.add(warriorOrgrimmar);

        orgrimmarFaction.addMember(warriorOrgrimmar);
        orgrimmarFaction.addMember(priestOrgrimmar);
        orgrimmarFaction.removeMember(priestOrgrimmar);
        Assert.assertEquals(expectedList,orgrimmarFaction.getMembers());
    }

    @Test
    @DisplayName("A character can't leave a faction that it don't belong")
    void characterCantLeaveANotJoinedFaction(){
        try {
            warriorOrgrimmar.joinFaction(orgrimmarFaction);
            warriorOrgrimmar.leaveFaction(darnassusFaction);
        } catch (RuntimeException re){
            String expectedException = "This character doesn't belong to this faction";
            Assert.assertEquals(expectedException, re.getMessage());
        }

    }

    @Test
    @DisplayName("A Faction can declare one friend")
    void factionAddOneFriend(){
        List<Faction> expectedList = new ArrayList<>();
        expectedList.add(darnassusFaction);

        stormwindFaction.addFriend(darnassusFaction);
        Assert.assertEquals(expectedList, stormwindFaction.getFriends());
    }

    @Test
    @DisplayName("A Faction can declare multiple friends")
    void factionAddMultipleFriend(){
        List<Faction> expectedList = new ArrayList<>();
        expectedList.add(darnassusFaction);
        expectedList.add(ironforgeFaction);

        stormwindFaction.addFriend(darnassusFaction);
        stormwindFaction.addFriend(ironforgeFaction);
        Assert.assertEquals(expectedList, stormwindFaction.getFriends());
    }

    @Test
    @DisplayName("A Faction can't declare itself as friend")
    void factionAddItselfAsFriend(){
        List<Faction> expectedList = new ArrayList<>();
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
        List<Faction> expectedList = new ArrayList<>();
        expectedList.add(darnassusFaction);

        stormwindFaction.addFriend(darnassusFaction);
        stormwindFaction.addFriend(ironforgeFaction);
        stormwindFaction.removeFriend(ironforgeFaction);
        Assert.assertEquals(expectedList, stormwindFaction.getFriends());
    }

    @Test
    @DisplayName("A character may belong to one or more factions")
    void belongToMultipleFactions(){
        List<Object> expectedList = new ArrayList<>();
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
