package ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens;

import ru.mirea.skorobogatov.plang.Lexer.Token;

import static ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens.AdvancedTokenType.FORMULA;

public class FormulaToken extends AdvancedToken {

    String formula = "";

    FormulaToken() {
        super(FORMULA);
    }

    public String getFormula() {
        formula = "";
        for (Token token : tokens) {
            formula += token.getValue();
        }
        return formula;
    }

    @Override
    public void print() {
        System.out.println(this.getFormula());
    }
}
