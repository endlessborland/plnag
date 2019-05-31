package ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens;

import ru.mirea.skorobogatov.plang.Lexer.Token;

import java.util.ArrayList;
import java.util.List;

import static ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens.AdvancedTokenType.FORMULA;

public class FormulaToken extends AdvancedToken {

    String formula = "";

    List<FunctionCallToken> functionCallTokenList;

    public FormulaToken() {
        super(FORMULA);
        functionCallTokenList = new ArrayList<>();
    }

    public void addFunction(FunctionCallToken functionCallToken) {
        this.functionCallTokenList.add(functionCallToken);
    }

    public String getFormula() {
        formula = "";
        for (Token token : tokens) {
            formula += token.getValue();
        }
        return formula;
    }

    @Override
    public void print() {
        System.out.println(this.getFormula());
    }
}
