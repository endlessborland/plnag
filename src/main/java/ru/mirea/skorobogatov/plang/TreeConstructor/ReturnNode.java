package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FormulaToken;

public class ReturnNode extends Node {

    FormulaToken formulaToken;

    public void setFormulaToken(FormulaToken formulaToken) {
        this.formulaToken = formulaToken;
    }

    public FormulaToken getFormulaToken() {

        return formulaToken;
    }
}