package ru.mirea.skorobogatov.plang.TreeConstructor.Nodes;

import ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens.FormulaToken;
import ru.mirea.skorobogatov.plang.PolishCalc.PolishCalc;
import ru.mirea.skorobogatov.plang.Runner.VarReplacer;
import ru.mirea.skorobogatov.plang.TreeConstructor.Runnable;

import java.util.HashMap;

public class IfNode extends ChooseNode implements Runnable {

    FormulaToken formula;
    private boolean wasSetToTruth = false;

    public IfNode() {
        super();
    }

    public void setFormula(FormulaToken formula) {
        this.formula = formula;
    }

    public FormulaToken getFormula() {
        return formula;
    }

    @Override
    public Node run(HashMap<String, Integer> varMap) {
        String formula = VarReplacer.replaceString(this.formula.getFormula(), varMap);
        Integer a = PolishCalc.calculate(formula);
        if (wasSetToTruth) {
            if (this.next instanceof ElseNode) {
                wasSetToTruth = false;
                return this.next.next;
            } else {
                wasSetToTruth = false;
                return this.next;
            }
        } else {
            if (a == 0) //true
            {
                wasSetToTruth = false;
                return this.next;
            } else {
                wasSetToTruth = true;
                return this.left;
            }
        }
    }
}
