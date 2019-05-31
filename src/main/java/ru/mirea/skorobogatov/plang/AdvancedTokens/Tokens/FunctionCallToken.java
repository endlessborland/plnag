package ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens;

import java.util.ArrayList;
import java.util.List;

public class FunctionCallToken extends AdvancedToken {

    String funcName;
    List<FormulaToken> params;
    int paramAmount;



    public FunctionCallToken() {
        super(AdvancedTokenType.FUNC_CALL);
        paramAmount = 0;
        params = new ArrayList<>();
    }

    public String getFuncName() {
        return funcName;
    }

    public List<FormulaToken> getParams() {
        return params;
    }

    public int getParamAmount() {
        return paramAmount;
    }

    public void setFuncName(String funcName) {

        this.funcName = funcName;
    }

    public void addParam(FormulaToken formulaToken) {
        params.add(formulaToken);
        paramAmount++;
    }

    @Override
    public void print() {
        super.print();
        System.out.println(funcName);
        System.out.println(paramAmount);
        for(FormulaToken formulaToken: params)
            System.out.println(formulaToken);
    }
}
