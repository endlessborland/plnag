package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FormulaToken;

public class IfNode extends Node {

    Node trueNode;

    FormulaToken formula;

    public void setFormula(FormulaToken formula) {
        this.formula = formula;
    }

    public FormulaToken getFormula() {

        return formula;
    }

    public void setTrueNode(Node trueNode) {
        this.trueNode = trueNode;
    }

    public Node getTrueNode() {
        return trueNode;
    }

}
