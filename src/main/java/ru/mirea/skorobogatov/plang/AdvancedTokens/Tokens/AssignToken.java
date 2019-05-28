package ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens;

import static ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens.AdvancedTokenType.VAR_ASSIGN;

public class AssignToken extends AdvancedToken {
    String var;
    FormulaToken formulaToken;


    public AssignToken() {
        super(VAR_ASSIGN);
    }

    public String getVar() {
        return var;
    }

    public FormulaToken getFormulaToken() {
        return formulaToken;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setFormulaToken(FormulaToken formulaToken) {
        this.formulaToken = formulaToken;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Formula: ");
        System.out.print("Assign to: ");
        System.out.println(var);
        formulaToken.print();
    }
}
