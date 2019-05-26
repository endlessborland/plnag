package ru.mirea.skorobogatov.plang.AdvancedTokens;

import ru.mirea.skorobogatov.plang.Token;

import static ru.mirea.skorobogatov.plang.AdvancedTokens.AdvancedTokenType.FORMULA;

public class FormulaToken extends AdvancedToken{

    String formula = "";

    FormulaToken() {
        super(FORMULA);
    }

    public String getFormula() {
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
