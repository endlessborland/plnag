package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FormulaToken;

public class WhileNode extends Node {

    Node trueNode;
    FormulaToken formulaToken;

    public void setTrueNode(Node trueNode) {
        this.trueNode = trueNode;
    }

    public void setFormulaToken(FormulaToken formulaToken) {
        this.formulaToken = formulaToken;
    }

    public Node getTrueNode() {

        return trueNode;
    }

    public FormulaToken getFormulaToken() {
        return formulaToken;
    }
}
