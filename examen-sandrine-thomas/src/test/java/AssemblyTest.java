import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class AssemblyTest {
    Faction orgrimmarFaction;
    Faction stormwindFaction;
    Faction darkspearFaction;
    Faction darnassusFaction;
    Faction ironforgeFaction;

    Assembly warriorAssembly;
    Assembly hordeWarriorsAssembly;

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

        // Assemblies

        ArrayList<Class> warriorAssemblyAllowedRoles = new ArrayList<>();
        warriorAssemblyAllowedRoles.add(Warrior.class);
        warriorAssembly = new Assembly("Warriors", warriorAssemblyAllowedRoles);
        hordeWarriorsAssembly = new Assembly("Horde Warriors", warriorAssemblyAllowedRoles);
    }

    @Test
    @DisplayName("A character can join an assembly")
    void characterCanJoinAssembly() {
        ArrayList<Character> expectedList = new ArrayList<>();
        expectedList.add(warriorOrgrimmar);

        warriorOrgrimmar.joinAssembly(warriorAssembly);
        Assert.assertEquals(warriorAssembly.getMembers(), expectedList);
    }

    @Test
    @DisplayName("A character can't join multiple assemblies")
    void characterCantJoinMultipleAssembly() {
        try {
            warriorOrgrimmar.joinAssembly(warriorAssembly);
            warriorOrgrimmar.joinAssembly(hordeWarriorsAssembly);
        }
        catch (RuntimeException re) {
            String expectedException = "This character already belong to an assembly";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("An unallowed character can't join an assembly")
    void unallowedCharacterCannotJoinAssembly() {
        try {
            priestDarkspear.joinAssembly(warriorAssembly);
        }
        catch (RuntimeException re) {
            String expectedException = "This character role is not allowed in this assembly";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }

    @Test
    @DisplayName("The first character to join the assembly is the master")
    void firstCharacterIsTheMaster() {
        warriorOrgrimmar.joinAssembly(warriorAssembly);
        Assert.assertEquals(warriorOrgrimmar, warriorAssembly.getMaster());
    }

    @Test
    @DisplayName("When the master leaves the assembly, an other random character become the master")
    void newCharacterIsTheMaster() {
        warriorOrgrimmar.joinAssembly(warriorAssembly);
        warriorDarkspear.joinAssembly(warriorAssembly);
        warriorDarnassus.joinAssembly(warriorAssembly);
        Assert.assertEquals(warriorOrgrimmar, warriorAssembly.getMaster());
        warriorOrgrimmar.leaveAssembly(warriorAssembly);
        Assert.assertTrue(warriorAssembly.getMaster().equals(warriorDarkspear) || warriorAssembly.getMaster().equals(warriorDarnassus) );
    }
}
