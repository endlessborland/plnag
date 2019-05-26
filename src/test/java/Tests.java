import org.junit.Test;

import ru.mirea.skorobogatov.plang.AdvancedTokens.AdvancedLexer;
import ru.mirea.skorobogatov.plang.AdvancedTokens.AdvancedToken;
import ru.mirea.skorobogatov.plang.Exceptions.SyntaxException;
import ru.mirea.skorobogatov.plang.Lexer.*;
import ru.mirea.skorobogatov.plang.Token;

import java.util.List;

public class Tests {

    public void testAdvancedLexer(String str) {
        Lexer lexer = new Lexer();
        List<Token> a;
        try {
             a = lexer.run(str);
        } catch (SyntaxException e) {
            System.out.print(e.toString());
            assert false;
            return;
        }
        AdvancedLexer advancedLexer = new AdvancedLexer(a);
        try {
            advancedLexer.run();
        } catch (SyntaxException e) {
            System.out.print(e.toString());
            assert false;
        }
        List<AdvancedToken> list = advancedLexer.getAdvancedTokenList();
        for (AdvancedToken t: list)
            t.print();
    }
    @Test
    public void testAssignLexerFormula() {
        testAdvancedLexer("a := (5 + b)");
    }

    @Test
    public void testAdvancedLexerWhile() {
        testAdvancedLexer("while (a + b - 5 / 16)");
    }

    @Test
    public void testAdvancedLexerIf() {
        testAdvancedLexer("if (a + b = 5 / 16)");
    }

    @Test
    public void testAsIs() {
        testAdvancedLexer("{}else{}else}}");
    }

    @Test
    public void testFunc() {
        testAdvancedLexer("func abs(a, b, c)");
    }

    @Test
    public void testFuncCall() {
        testAdvancedLexer("abs(a, b, c) -> 3");
    }

    @Test
    public void testAll() {
        testAdvancedLexer("func f(a, b, c) { return (a+b) } { a := (5) b := (6) if (a > b) { while (b - a > 0) { b := (b + 1) } } f(b, a, 5) -> c }");
    }

    @Test
    public void testLexer() {
        String str = "while(a+b*5/16)";
        Lexer lexer = new Lexer();
        List<Token> a;
        try {
             a = lexer.run(str);
        } catch (SyntaxException e) {
            System.out.print(e.toString());
            assert false;
            return;
        }
        for (Token token: a)
            System.out.println(token.toString());
    }
}
