package ru.mirea.skorobogatov.plang.TreeConstructor.Nodes;

import ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens.AssignToken;
import ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens.FormulaToken;
import ru.mirea.skorobogatov.plang.PolishCalc.PolishCalc;
import ru.mirea.skorobogatov.plang.Runner.VarReplacer;
import ru.mirea.skorobogatov.plang.TreeConstructor.Runnable;

import java.util.HashMap;

public class AssignNode extends Node implements Runnable {
    public AssignNode() {
        super();
    }

    AssignToken assignToken;

    public void setAssignToken(AssignToken assignToken) {
        this.assignToken = assignToken;
    }

    public AssignToken getAssignToken() {

        return assignToken;
    }

    @Override
    public Node run(HashMap<String, Integer> varMap) {
        FormulaToken formulaToken = this.assignToken.getFormulaToken();
        String str = formulaToken.getFormula();
        String tmp = VarReplacer.replaceString(str, varMap);
        varMap.put(assignToken.getVar(), PolishCalc.calculate(tmp));
        System.out.print("");
        return this.next;
    }
}
