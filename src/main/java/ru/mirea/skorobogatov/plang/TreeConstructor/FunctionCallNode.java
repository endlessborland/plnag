package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FunctionCallToken;

public class FunctionCallNode extends Node {

    FunctionCallToken functionCallToken;

    public FunctionCallNode() {
        super();
    }

    public void setFunctionCallToken(FunctionCallToken functionCallToken) {
        this.functionCallToken = functionCallToken;
    }

    public FunctionCallToken getFunctionCallToken() {

        return functionCallToken;
    }
}
