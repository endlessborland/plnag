import org.junit.Test;
import ru.mirea.skorobogatov.plang.PolishCalc.*;

public class PolishCalcTest {

    @Test
    public void test() {
        assert 0 == PolishCalc.calculate("5 = 1");
        assert 1 == PolishCalc.calculate("5 != 1");
    }
}
