import org.junit.Test;
import ru.mirea.skorobogatov.plang.Runner.Runner;

public class SimpleTests {

    @Test
    public void testRecursion() {
        String str = "{ b:= (5) f(b) -> c return(c) } func f(n) { if (n < 2) { return (1) } else { p := (n-1) f(p) -> d return (n*d) } }";
        Runner.setUp(str);
        Runner runner = new Runner();
        runner.run(Runner.MainFunction);
        assert Runner.stack.pop() == 120;
    }

    @Test
    public void testComplexStructure() {
        String str = "{  n := (5) p := (0) k := (0) c := (0)" +
                "while (p < n) {" +
                " p := (p + 1)" +
                " if ((p % 2) = 0) {" +
                "k := (k+p)" +
                "} " +
                "else {" +
                "c := (c+p) }" +
                "} " +
                "return (c)" +
                "}";
        Runner.setUp(str);
        Runner runner = new Runner();
        runner.run(Runner.MainFunction);
        assert Runner.stack.pop() == 9;
    }
}
