import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WarriorTest extends Data{

    @BeforeEach
    void initData() {
        super.init();
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
