package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FormulaToken;

public class WhileNode extends ChooseNode {

    FormulaToken formulaToken;

    public WhileNode() {
        super();
    }

    public void setFormulaToken(FormulaToken formulaToken) {
        this.formulaToken = formulaToken;
    }

    public FormulaToken getFormulaToken() {
        return formulaToken;
    }
}
