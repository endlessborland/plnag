package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FormulaToken;
import ru.mirea.skorobogatov.plang.PolishCalc.PolishCalc;
import ru.mirea.skorobogatov.plang.Runner.VarReplacer;

import java.util.HashMap;

public class WhileNode extends ChooseNode implements Runnalbe{

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
