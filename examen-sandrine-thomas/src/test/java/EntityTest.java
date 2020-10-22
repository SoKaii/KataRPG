import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntityTest {
    Faction orgrimmarFaction;
    Building testBulding;

    @BeforeEach
    void init() {
        orgrimmarFaction = new Faction("Orgrimmar");
        testBulding =  new Building("Building Test", 10000);
    }

    @Test
    @DisplayName("Anything that has health is considered as Entity")
    void entityHasHealth() {
        Assert.assertTrue(testBulding instanceof Entity);
    }
}
