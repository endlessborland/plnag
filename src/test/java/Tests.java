import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Tests {

    @org.junit.Test
    public void testVARReplacer() {
        Memory.varValueMap.put("a", 12);
        assertTrue("12+12".equals(VARReplacer.replaceString("a+12")));
    }

    @org.junit.Test
    public void testPolishCalc() {
        Memory.varValueMap.put("a", 3);
        Memory.varValueMap.put("b", 3);
        assertTrue(PolishCalc.calculate(VARReplacer.replaceString("12+5*a")) == 27);
        assertTrue(PolishCalc.calculate(VARReplacer.replaceString("a=b")) == 1);
        assertTrue(PolishCalc.calculate(VARReplacer.replaceString("a>b")) == 0);
        assertTrue(PolishCalc.calculate(VARReplacer.replaceString("a<b")) == 0);
        assertTrue(PolishCalc.calculate(VARReplacer.replaceString("3=2")) == 0);
        assertTrue(PolishCalc.calculate(VARReplacer.replaceString("3>4")) == 0);
        assertTrue(PolishCalc.calculate(VARReplacer.replaceString("6<5")) == 0);

    }

    @org.junit.Test
    public void testLexer() {
        Lexer lex = new Lexer();
        List<Token> tokens = lex.identify("array; if; for; stuu [hello]");
        for (Token token: tokens) {
            System.out.println(token.toString());
        }
    }
}
