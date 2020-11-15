import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntityTest extends Data{

    @BeforeEach
    void initData() {
        super.init();
    }

    @Test
    @DisplayName("Anything that has health is considered as Entity")
    void entityHasHealth() {
        Assert.assertTrue(molfenderTower instanceof Entity);
    }
}
