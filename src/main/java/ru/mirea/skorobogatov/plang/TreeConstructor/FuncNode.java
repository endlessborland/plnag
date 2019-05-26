package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FuncToken;

public class FuncNode extends Node {

    FuncToken funcToken;

    public void setFuncToken(FuncToken funcToken) {
        this.funcToken = funcToken;
    }

    public FuncToken getFuncToken() {

        return funcToken;
    }
}
