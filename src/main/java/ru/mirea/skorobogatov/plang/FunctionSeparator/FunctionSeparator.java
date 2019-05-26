package ru.mirea.skorobogatov.plang.FunctionSeparator;

import ru.mirea.skorobogatov.plang.AdvancedTokens.AdvancedToken;
import ru.mirea.skorobogatov.plang.AdvancedTokens.AdvancedTokenType;
import ru.mirea.skorobogatov.plang.Exceptions.SyntaxException;

import java.util.ArrayList;
import java.util.List;

public class FunctionSeparator {

    List<AdvancedToken> mainTokenList;
    List<List<AdvancedToken>> functionList;
    List<AdvancedToken> tokenList;

    public List<AdvancedToken> getMainTokenList() {
        return mainTokenList;
    }

    public List<List<AdvancedToken>> getFunctionList() {
        return functionList;
    }

    public FunctionSeparator(List<AdvancedToken> tokenList) {
        this.tokenList = tokenList;
        this.functionList = new ArrayList<>();
        this.mainTokenList = new ArrayList<>();
    }

    public void run() throws SyntaxException {
        int i = 1;
        if (tokenList.get(0).getTokenType() == AdvancedTokenType.L_F_B) {
            int brackets = 1;
            while (brackets != 0) {
                try {
                    if (tokenList.get(i).getTokenType() == AdvancedTokenType.L_F_B)
                        brackets++;
                    if (tokenList.get(i).getTokenType() == AdvancedTokenType.R_F_B) {
                        brackets--;
                        if (brackets < 0)
                            throw new SyntaxException("F_brackets misplaced");
                    }
                    if (brackets == 0)
                        break;
                    mainTokenList.add(this.tokenList.get(i));
                } catch (IndexOutOfBoundsException e) {
                    throw new SyntaxException("F_brackets misplaced - no right bracket");
                }
                i++;
            }
        } else
            throw new SyntaxException("Main should start with { and end with }. All other funcs - later");
        if (i >= this.tokenList.size())
            return;

        i++;

        for (; i < tokenList.size(); i++) {
            if (tokenList.get(i).getTokenType() == AdvancedTokenType.FUNC) {
                List<AdvancedToken> list = new ArrayList<>();
                list.add(tokenList.get(i));
                if (tokenList.get(++i).getTokenType() == AdvancedTokenType.L_F_B) {
                    int brackets = 1;
                    i++;
                    while (brackets != 0) {
                        try {
                            if (tokenList.get(i).getTokenType() == AdvancedTokenType.L_F_B)
                                brackets++;
                            if (tokenList.get(i).getTokenType() == AdvancedTokenType.R_F_B) {
                                brackets--;
                                if (brackets < 0)
                                    throw new SyntaxException("F_brackets misplaced");
                            }
                            if (brackets == 0)
                                break;
                            list.add(this.tokenList.get(i));
                        } catch (IndexOutOfBoundsException e) {
                            throw new SyntaxException("F_brackets misplaced - no right bracket");
                        }
                        i++;
                    }
                    this.functionList.add(list);
                } else
                    throw new SyntaxException("Func error - brackets");
            } else
                if (i >= this.tokenList.size())
                    return;
            else
                throw new SyntaxException("Func error - syntax");
        }
    }
}
