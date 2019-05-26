package ru.mirea.skorobogatov.plang.AdvancedTokens;

public class WhileToken extends AdvancedToken {

    FormulaToken formulaToken;

    public void setFormulaToken(FormulaToken formulaToken) {
        this.formulaToken = formulaToken;
    }

    public FormulaToken getFormulaToken() {

        return formulaToken;
    }

    public WhileToken() {
        super(AdvancedTokenType.WHILE);
    }

    @Override
    public void print() {
        System.out.println("WHILE");
        System.out.println(this.formulaToken.getFormula());
    }
}
