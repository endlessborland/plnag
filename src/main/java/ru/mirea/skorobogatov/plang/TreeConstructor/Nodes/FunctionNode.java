package ru.mirea.skorobogatov.plang.TreeConstructor.Nodes;

import ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens.FunctionToken;

public class FunctionNode extends Node {

    FunctionToken functionToken;

    public FunctionNode() {
        super();
    }

    public void setFunctionToken(FunctionToken functionToken) {
        this.functionToken = functionToken;
    }

    public FunctionToken getFunctionToken() {

        return functionToken;
    }
}
