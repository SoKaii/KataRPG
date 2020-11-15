import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PriestTest extends Data{

    @BeforeEach
    void initData() {
        super.init();
    }

    @Test
    @DisplayName("A priest can't attack")
    void priestAttack() {
        try
        {
           priestDarkspear.attack(warriorStormwind);
        }
        catch(RuntimeException re)
        {
            String expectedException = "A priest can't attack";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("A priest can heal another character of his faction between 5 and 10 hp ")
    void priestHealsAnotherCharacterOfFaction() {
        priestOrgrimmar.joinFaction(orgrimmarFaction);
        warriorOrgrimmar.joinFaction(orgrimmarFaction);
        for(int i = 0; i < 50; i++) {
            warriorOrgrimmar.setHealth(50);
            priestOrgrimmar.heal(warriorOrgrimmar);
            Assert.assertTrue((warriorOrgrimmar.getHealth() - 50 <= 10) && (warriorOrgrimmar.getHealth() - 50 >= 5));
        }
    }

    @Test
    @DisplayName("A priest can heal another character of a friend's faction between 5 and 10 hp ")
    void priestHealsAnotherCharacterOfFriendFaction() {
        orgrimmarFaction.addFriend(darkspearFaction);
        darkspearFaction.addFriend(orgrimmarFaction);
        priestDarkspear.joinFaction(darkspearFaction);
        warriorOrgrimmar.joinFaction(orgrimmarFaction);

        for(int i = 0; i < 50; i++) {
            warriorOrgrimmar.setHealth(50);
            priestDarkspear.heal(warriorOrgrimmar);
            Assert.assertTrue((warriorOrgrimmar.getHealth() - 50 <= 10) && (warriorOrgrimmar.getHealth() - 50 >= 5));
        }
    }

    @Test
    @DisplayName("A priest can't heal a character of another faction")
    void priestHealsACharacterOfAnotherFaction() {
        priestDarkspear.joinFaction(darkspearFaction);
        warriorOrgrimmar.joinFaction(orgrimmarFaction);
        try
        {
            warriorOrgrimmar.setHealth(50);
            priestDarkspear.heal(warriorOrgrimmar);
        }
        catch(RuntimeException re)
        {
            String expectedException = "A character can only heal another character of his faction or friend faction";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("A priest can heal himself between 5 and 10 hp ")
    void priestHealsHimself() {
        for(int i = 0; i < 50; i++) {
            priestDarkspear.setHealth(50);
            priestDarkspear.heal(priestDarkspear);
            Assert.assertTrue((priestDarkspear.getHealth() - 50 <= 10) && (priestDarkspear.getHealth() - 50 >= 5));
        }
    }
}
