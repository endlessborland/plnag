package ru.mirea.skorobogatov.plang.AdvancedTokens;

import java.util.ArrayList;
import java.util.List;

public class FunctionCallToken extends AdvancedToken {

    String funcName;
    List<String> params;
    int paramAmount;
    String varName;

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVarName() {

        return varName;
    }

    public FunctionCallToken() {
        super(AdvancedTokenType.FUNC_CALL);
        paramAmount = 0;
        params = new ArrayList<>();
    }

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

    public void addParam(String string) {
        params.add(string);
        paramAmount++;
    }

    @Override
    public void print() {
        super.print();
        System.out.println(funcName);
        System.out.println(paramAmount);
        System.out.println(varName);
        for(String string: params)
            System.out.println(string);
    }
}
