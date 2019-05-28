package ru.mirea.skorobogatov.plang.TreeConstructor.Nodes;

import ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens.FormulaToken;
import ru.mirea.skorobogatov.plang.PolishCalc.PolishCalc;
import ru.mirea.skorobogatov.plang.Runner.VarReplacer;
import ru.mirea.skorobogatov.plang.TreeConstructor.Runnable;

import java.util.HashMap;

public class WhileNode extends ChooseNode implements Runnable {

    FormulaToken formulaToken;

    public WhileNode() {
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
        String formula = VarReplacer.replaceString(this.formulaToken.getFormula(), varMap);
        Integer a = PolishCalc.calculate(formula);
        if (a != 0)
            return this.left;
        return this.next;
    }
}
