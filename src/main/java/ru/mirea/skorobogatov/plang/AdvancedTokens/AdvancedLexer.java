package ru.mirea.skorobogatov.plang.AdvancedTokens;

import ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens.*;
import ru.mirea.skorobogatov.plang.Exceptions.SyntaxException;
import ru.mirea.skorobogatov.plang.Lexer.LexemPatterns;
import ru.mirea.skorobogatov.plang.Lexer.Token;

import java.util.ArrayList;
import java.util.List;

public class AdvancedLexer {

    List<Token> tokenList;

    public List<AdvancedToken> getAdvancedTokenList() {
        return advancedTokenList;
    }

    List<AdvancedToken> advancedTokenList;

    public AdvancedLexer(List<Token> tokenList) {
        this.tokenList = tokenList;
        this.advancedTokenList = new ArrayList<>();
    }

    public void run() throws SyntaxException {
        for (int i = 0; i < tokenList.size(); i++)
        {
            switch(tokenList.get(i).getLexeme()) {
                case FUNC:
                    i = createFUNC(i);
                    break;
                case IF:
                    i = createIF(i);
                    break;
                case WHILE:
                    i = createWHILE(i);
                    break;
                case VAR:
                    i = createASSIGNorFUNC_CALL(i);
                    break;
                case L_F_B:
                    i = createLFB(i);
                    break;
                case R_F_B:
                    i = createRFB(i);
                    break;
                case ELSE:
                    i = createELSE(i);
                    break;
                case RETURN:
                    i = createReturn(i);
                    break;
                default:
                    break;
            }
        }
    }

    private int createReturn(int i) throws SyntaxException {
        ReturnToken returnToken = new ReturnToken();
        while (this.tokenList.get(++i).getLexeme() == LexemPatterns.WS) { /* do nothing */ }

        if (this.tokenList.get(i).getLexeme() == LexemPatterns.L_R_B) {
            FormulaToken formulaToken = new FormulaToken();
            i = createFORMULA(i, formulaToken);
            returnToken.setFormulaToken(formulaToken);
        } else throw new SyntaxException("Error in RETURN at Token " + i);
        this.advancedTokenList.add(returnToken);
        return i;
    }

    //region Brackets and Else- leave as is
    private int createELSE(int i) {
        AdvancedToken tmp = new AdvancedToken(AdvancedTokenType.ELSE);
        tmp.addToken(tokenList.get(i));
        this.advancedTokenList.add(tmp);
        return i;
    }

    private int createRFB(int i) {
        AdvancedToken tmp = new AdvancedToken(AdvancedTokenType.R_F_B);
        tmp.addToken(tokenList.get(i));
        this.advancedTokenList.add(tmp);
        return i;
    }

    private int createLFB(int i) {
        AdvancedToken tmp = new AdvancedToken(AdvancedTokenType.L_F_B);
        tmp.addToken(tokenList.get(i));
        this.advancedTokenList.add(tmp);
        return i;
    }
    //endregion

    private int createASSIGNorFUNC_CALL(int i) throws SyntaxException {
        int old_i = i;
        while (this.tokenList.get(++i).getLexeme() == LexemPatterns.WS) { /* do nothing */ }
        switch (this.tokenList.get(i).getLexeme()) {
            case ASSIGN_OP:
                i = createASSIGN(old_i);
                break;
            case L_R_B:
                i = createFUNC_CALL(old_i);
                break;
            default:
                throw new SyntaxException("Error in ASSIGN/FUNC_CALL at Token" + i);
        }
        return i;
    }

    private int createASSIGN(int i) throws SyntaxException {
        AssignToken assignToken = new AssignToken();
        assignToken.setVar(tokenList.get(i).getValue());

        while (this.tokenList.get(++i).getLexeme() == LexemPatterns.WS) { /* do nothing */ }
        if (this.tokenList.get(i).getLexeme() != LexemPatterns.ASSIGN_OP)
            throw new SyntaxException("Error in ASSIGN at Token " + i);
        while (this.tokenList.get(++i).getLexeme() == LexemPatterns.WS) { /* do nothing */ }
        if (this.tokenList.get(i).getLexeme() == LexemPatterns.L_R_B) {
            FormulaToken formulaToken = new FormulaToken();
            i = createFORMULA(i, formulaToken);
            assignToken.setFormulaToken(formulaToken);
        } else throw new SyntaxException("Error in ASSIGN at Token " + i);
        this.advancedTokenList.add(assignToken);

        return i;
    }

    private int createFUNC_CALL(int i) throws SyntaxException {
        FunctionCallToken functionCallToken = new FunctionCallToken();
        functionCallToken.setFuncName(tokenList.get(i).getValue());
        while (this.tokenList.get(++i).getLexeme() == LexemPatterns.WS) { /* do nothing */ }
        if (this.tokenList.get(i).getLexeme() == LexemPatterns.L_R_B) {
            int pos = 0;
            while(this.tokenList.get(++i).getLexeme() != LexemPatterns.R_R_B) {
                switch(this.tokenList.get(i).getLexeme()) {
                    case WS:
                        continue;
                    case VAR:
                        pos++;
                        functionCallToken.addParam(this.tokenList.get(i).getValue());
                        break;
                    case DIGIT:
                        pos++;
                        functionCallToken.addParam(this.tokenList.get(i).getValue());
                        break;
                    case COMMA:
                        pos--;
                        break;
                    default:
                        throw new SyntaxException("Error in FUNC_CALL, not a VAR nor COMMA nor DIGIT at Token " + i);
                }
                if (pos > 1)
                    throw new SyntaxException("Error in FUNC_CALL misplaced COMMAS at Token " + i);
            }
        } else throw new SyntaxException("Error in FUNC_CALL at Token " + i);
        while (this.tokenList.get(++i).getLexeme() == LexemPatterns.WS) { /* do nothing */ }
        if (this.tokenList.get(i).getLexeme() == LexemPatterns.FUNC_ASSIGN) {
            while (this.tokenList.get(++i).getLexeme() == LexemPatterns.WS) { /* do nothing */ }
            if (this.tokenList.get(i).getLexeme() == LexemPatterns.VAR) {
                functionCallToken.setVarName(this.tokenList.get(i).getValue());
            }
        } else throw new SyntaxException("Error in FUNC_CALL nothing to assign to " + i);
        this.advancedTokenList.add(functionCallToken);
        return i;
    }

    private int createWHILE(int i) throws SyntaxException {
        WhileToken whileToken = new WhileToken();

        while (this.tokenList.get(++i).getLexeme() == LexemPatterns.WS) { /* do nothing */ }

        if (this.tokenList.get(i).getLexeme() == LexemPatterns.L_R_B) {
            FormulaToken formulaToken = new FormulaToken();
            i = createFORMULA(i, formulaToken);
            whileToken.setFormulaToken(formulaToken);
        } else throw new SyntaxException("Error in WHILE at Token " + i + this.tokenList.get(i).toString());
        this.advancedTokenList.add(whileToken);
        return i;
    }

    private int createFORMULA(int i, FormulaToken formulaToken) throws SyntaxException {
        int brackets = 1;
        while (true) {
            switch(this.tokenList.get(++i).getLexeme()) {
                case WS:
                    continue;
                case L_R_B:
                    brackets++;
                    formulaToken.addToken(this.tokenList.get(i));
                    break;
                case R_R_B:
                    brackets--;
                    if (brackets < 0)
                        throw new SyntaxException("Error in FORMULA SYNTAX - brackets at Token " + i);
                    if (brackets == 0)
                        return i; // Only correct exit
                    formulaToken.addToken(this.tokenList.get(i));
                    break;
                case OP:
                case LOG_OP:
                case VAR:
                case DIGIT:
                    formulaToken.addToken(this.tokenList.get(i));
                    break;
                default:
                    throw new SyntaxException("Error in FORMULA SYNTAX - invalid symbol at Token " + i);
            }
        }
    }

    private int createIF(int i) throws SyntaxException {
        IfToken ifToken = new IfToken();

        while (this.tokenList.get(++i).getLexeme() == LexemPatterns.WS) { /* do nothing */ }

        if (this.tokenList.get(i).getLexeme() == LexemPatterns.L_R_B) {
            FormulaToken formulaToken = new FormulaToken();
            i = createFORMULA(i, formulaToken);
            ifToken.setFormulaToken(formulaToken);
        } else throw new SyntaxException("Error in IF at Token " + i + this.tokenList.get(i).toString());
        this.advancedTokenList.add(ifToken);
        return i;
    }

    private int createFUNC(int i) throws SyntaxException {
        FunctionToken functionToken = new FunctionToken();
        while (this.tokenList.get(++i).getLexeme() == LexemPatterns.WS) { /* do nothing */ }

        if (this.tokenList.get(i).getLexeme() == LexemPatterns.VAR)
            functionToken.setFuncName(this.tokenList.get(i).toString());
        else
            throw new SyntaxException("Error in FUNC at Token " + i);

        while (this.tokenList.get(++i).getLexeme() == LexemPatterns.WS) { /* do nothing */ }

        if (this.tokenList.get(i).getLexeme() == LexemPatterns.L_R_B) {
            int pos = 0;
            while(this.tokenList.get(++i).getLexeme() != LexemPatterns.R_R_B) {
                switch(this.tokenList.get(i).getLexeme()) {
                    case WS:
                        continue;
                    case VAR:
                        pos++;
                        functionToken.addParam(this.tokenList.get(i).toString());
                        break;
                    case DIGIT:
                        pos++;
                        functionToken.addParam(this.tokenList.get(i).toString());
                        break;
                    case COMMA:
                        pos--;
                        break;
                    default:
                        throw new SyntaxException("Error in FUNC, not a VAR nor COMMA nor DIGIT at Token " + i);
                }
                if (pos > 1)
                    throw new SyntaxException("Error in FUNC misplaced COMMAS at Token " + i);
            }
        } else throw new SyntaxException("Error in FUNC at Token " + i);
        this.advancedTokenList.add(functionToken);
        return i+1;
    }


}
