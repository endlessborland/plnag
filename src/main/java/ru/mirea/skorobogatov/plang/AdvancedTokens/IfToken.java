package ru.mirea.skorobogatov.plang.AdvancedTokens;

public class IfToken extends AdvancedToken {

    FormulaToken formulaToken;

    public void setFormulaToken(FormulaToken formulaToken) {
        this.formulaToken = formulaToken;
    }

    public FormulaToken getFormulaToken() {

        return formulaToken;
    }

    public IfToken() {
        super(AdvancedTokenType.IF);
    }

    @Override
    public void print() {
        System.out.println("IF");
        System.out.println(this.formulaToken.getFormula());
    }
}
