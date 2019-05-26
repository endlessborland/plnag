package ru.mirea.skorobogatov.plang.AdvancedTokens;

public class ReturnToken extends AdvancedToken {

    FormulaToken formulaToken;

    @Override
    public void print() {
        System.out.println("RETURN " + formulaToken.getFormula());
    }

    public void setFormulaToken(FormulaToken formulaToken) {
        this.formulaToken = formulaToken;
    }

    public FormulaToken getFormulaToken() {

        return formulaToken;
    }

    public ReturnToken() {
        super(AdvancedTokenType.RETURN);
    }
}
