import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssemblyTest extends Data{

    @BeforeEach
    void initData() {
        super.init();
    }

    @Test
    @DisplayName("A character can join an assembly")
    void characterCanJoinAssembly() {
        List<Character> expectedList = new ArrayList<>();
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

    @Test
    @DisplayName("Master is the last to leave the assembly, assembly has no master")
    void lastCharacterToLeave() {
        warriorDarnassus.joinAssembly(warriorAssembly);
        Assert.assertEquals(warriorDarnassus, warriorAssembly.getMaster());
        warriorDarnassus.leaveAssembly(warriorAssembly);
        Assert.assertNull(warriorAssembly.getMaster());
    }

    @Test
    @DisplayName("Only Master can change name of an Assembly")
    void onlyMasterChangeName() {
        warriorDarnassus.joinAssembly(warriorAssembly);
        warriorOrgrimmar.joinAssembly(warriorAssembly);
        try {
            warriorAssembly.setName("xX-DarkSasukes-Xx", warriorDarnassus);
            Assert.assertEquals(warriorAssembly.getName(), "xX-DarkSasukes-Xx");
            warriorAssembly.setName("xX-DarkNarutos-Xx", warriorOrgrimmar);
        } catch (RuntimeException re) {
            String expectedException = "Only the master can modify the name of the assembly";
            Assert.assertEquals(expectedException, re.getMessage());
        }
    }
}
