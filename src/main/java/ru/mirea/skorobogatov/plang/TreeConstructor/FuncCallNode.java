package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FunctionCallToken;

public class FuncCallNode extends Node {

    FunctionCallToken functionCallToken;

    public void setFunctionCallToken(FunctionCallToken functionCallToken) {
        this.functionCallToken = functionCallToken;
    }

    public FunctionCallToken getFunctionCallToken() {

        return functionCallToken;
    }
}
