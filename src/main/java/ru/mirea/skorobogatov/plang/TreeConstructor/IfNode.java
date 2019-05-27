package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FormulaToken;

public class IfNode extends ChooseNode {

    FormulaToken formula;

    public IfNode() {
        super();
    }

    public void setFormula(FormulaToken formula) {
        this.formula = formula;
    }

    public FormulaToken getFormula() {
        return formula;
    }
}
