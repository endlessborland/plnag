package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FunctionToken;

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
