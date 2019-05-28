package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FormulaToken;
import ru.mirea.skorobogatov.plang.PolishCalc.PolishCalc;
import ru.mirea.skorobogatov.plang.Runner.Runner;
import ru.mirea.skorobogatov.plang.Runner.VarReplacer;

import java.util.HashMap;

public class ReturnNode extends Node implements Runnalbe {

    FormulaToken formulaToken;

    public ReturnNode() {
        super();
    }

    public void setFormulaToken(FormulaToken formulaToken) {
        this.formulaToken = formulaToken;
    }

    public FormulaToken getFormulaToken() {

        return formulaToken;
    }

    @Override
    public Node run(HashMap<String, Integer> varMap) {
        String formula = VarReplacer.replaceString(formulaToken.getFormula(), varMap);
        Integer a = PolishCalc.calculate(formula);
        Runner.stack.push(a);
        return null;
    }
}
