import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WarriorTest {
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
    @DisplayName("A warrior can attack a character of another faction")
    void warriorAttacks() {
        warriorOrgrimmar.joinFaction(orgrimmarFaction);
        priestStormwind.joinFaction(stormwindFaction);
        warriorOrgrimmar.attack(priestStormwind);
        Assert.assertTrue(priestStormwind.getHealth() < 100);
    }

    @Test
    @DisplayName("A warrior can't attack a character of his faction")
    void warriorAttacksFaction() {
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
    @DisplayName("A warrior can kill a character of another faction")
    void warriorKills() {
        warriorOrgrimmar.joinFaction(orgrimmarFaction);
        priestStormwind.joinFaction(stormwindFaction);
        priestStormwind.setHealth(1);
        Assert.assertTrue(priestStormwind.getIsAlive());
        warriorOrgrimmar.attack(priestStormwind);
        Assert.assertFalse(priestStormwind.getIsAlive());
    }

    @Test
    @DisplayName("A warrior can heals himself ")
    void warriorHealsHimself() {
        warriorOrgrimmar.setHealth(50);
        warriorOrgrimmar.heal(warriorOrgrimmar);
        Assert.assertEquals(51,warriorOrgrimmar.getHealth());
    }

    @Test
    @DisplayName("A warrior can't heals another character ")
    void warriorHealsAnotherCharacter() {
        try
        {
            priestDarkspear.setHealth(50);
            warriorOrgrimmar.heal(priestDarkspear);
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
            priestStormwind.setHealth(50);
            warriorOrgrimmar.attack(priestStormwind);
            Assert.assertTrue((50 - priestStormwind.getHealth() <= 9) && (50 - priestStormwind.getHealth() >= 0));
        }
    }
}
