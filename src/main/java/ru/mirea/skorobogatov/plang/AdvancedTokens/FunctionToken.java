package ru.mirea.skorobogatov.plang.AdvancedTokens;

import java.util.ArrayList;
import java.util.List;

public class FunctionToken extends AdvancedToken {

    String funcName;
    List<String> params;
    int paramAmount;

    public String getFuncName() {
        return funcName;
    }

    public List<String> getParams() {
        return params;
    }

    public int getParamAmount() {
        return paramAmount;
    }

    public void setFuncName(String funcName) {

        this.funcName = funcName;
    }

    public FunctionToken() {
        super(AdvancedTokenType.FUNC);
        params = new ArrayList<>();
        paramAmount = 0;

    }

    public void addParam(String string) {
        params.add(string);
        paramAmount++;
    }

    @Override
    public void print() {
        super.print();
        System.out.println(funcName);
        System.out.println(paramAmount);
        for(String string: params)
            System.out.println(string);
    }
}
