import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CharacterTest extends Data{

    @BeforeEach
    void initData() {
        super.init();
    }

    @Test
    @DisplayName("A character have a name, a health and is alive")
    void characterExist() {
        Assert.assertEquals("Garrosh Hellscream", warriorOrgrimmar.getName());
        Assert.assertEquals(100, warriorOrgrimmar.getHealth());
        Assert.assertTrue(warriorOrgrimmar.isAlive());
        Assert.assertEquals("Anduin Wrynn", priestStormwind.getName());
        Assert.assertEquals(100, priestStormwind.getHealth());
        Assert.assertTrue(priestStormwind.isAlive());
    }

    @Test
    @DisplayName("If a character have 0 hp, he's dead")
    void characterIsDead() {
        priestDarkspear.setHealth(0);
        Assert.assertFalse(priestDarkspear.isAlive());
        warriorOrgrimmar.setHealth(0);
        Assert.assertFalse(warriorOrgrimmar.isAlive());
    }

    @Test
    @DisplayName("A character can join a faction")
    void characterJoinFaction() {
        List<Character> expectedList = new ArrayList<>();
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
        List<Character> expectedList = new ArrayList<>();
        expectedList.add(priestDarkspear);
        priestDarkspear.joinFaction(darkspearFaction);
        Assert.assertEquals(expectedList, darkspearFaction.getMembers());

        priestDarkspear.leaveFaction(darkspearFaction);
        expectedList.remove(priestDarkspear);
        Assert.assertEquals(expectedList, orgrimmarFaction.getMembers());
    }

    @Test
    @DisplayName("A character can't leave a faction if he's not in it")
    void characterLeaveNullFaction() {
        try
        {
           priestDarkspear.leaveFaction(darkspearFaction);
        }
        catch(RuntimeException re)
        {
            String expectedException = "This character doesn't have a faction to leave";
            Assert.assertEquals(expectedException, re.getMessage());
        }

    }

    @Test
    @DisplayName("A character can attack a character of another faction")
    void characterAttacks() {
        warriorOrgrimmar.joinFaction(orgrimmarFaction);
        priestStormwind.joinFaction(stormwindFaction);
        warriorOrgrimmar.attack(priestStormwind);
        Assert.assertTrue(priestStormwind.getHealth() < 100);
    }

    @Test
    @DisplayName("A character can attack a non-character entity")
    void characterAttacksNonCharacterEntity() {
        molfenderTower.setHealth(100);
        warriorOrgrimmar.attack(molfenderTower);
        Assert.assertTrue(molfenderTower.getHealth() < 100);
    }

    @Test
    @DisplayName("A character can't attack a dead entity")
    void characterAttacksDeadCharacter() {
        warriorDarnassus.setHealth(0);
        try {
            warriorOrgrimmar.attack(warriorDarnassus);
        }
        catch (RuntimeException re) {
            String expectedException = "A character can't attack a dead entity";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("A character can't attack a character of his faction")
    void characterAttacksFaction() {
        warriorOrgrimmar.joinFaction(orgrimmarFaction);
        priestOrgrimmar.joinFaction(orgrimmarFaction);
        try {
            warriorOrgrimmar.attack(priestOrgrimmar);
            Assert.assertTrue(priestOrgrimmar.getHealth() < 100);
        }
        catch (RuntimeException re) {
            String expectedException = "A character can't attack another character of his faction or friend faction";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("A character can't attack a character of a friend faction")
    void characterAttacksFriendFaction() {
        orgrimmarFaction.addFriend(darkspearFaction);
        warriorOrgrimmar.joinFaction(orgrimmarFaction);
        warriorDarkspear.joinFaction(darkspearFaction);

        try {
            warriorOrgrimmar.attack(warriorDarkspear);
            Assert.assertTrue(warriorDarkspear.getHealth() < 100);
        }
        catch (RuntimeException re) {
            String expectedException = "A character can't attack another character of his faction or friend faction";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("A character can kill a character of another faction")
    void characterKills() {
        warriorOrgrimmar.joinFaction(orgrimmarFaction);
        priestStormwind.joinFaction(stormwindFaction);
        priestStormwind.setHealth(1);
        Assert.assertTrue(priestStormwind.isAlive());
        warriorOrgrimmar.attack(priestStormwind);
        Assert.assertFalse(priestStormwind.isAlive());
    }
}
