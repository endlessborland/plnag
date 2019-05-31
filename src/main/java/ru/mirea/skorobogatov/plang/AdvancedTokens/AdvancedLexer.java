package ru.mirea.skorobogatov.plang.AdvancedTokens;

import ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens.*;
import ru.mirea.skorobogatov.plang.Exceptions.SyntaxException;
import ru.mirea.skorobogatov.plang.Lexer.LexemPatterns;
import ru.mirea.skorobogatov.plang.Lexer.Token;

import java.util.ArrayList;
import java.util.List;

public class AdvancedLexer {

    private Integer index;

    List<Token> tokenList;

    public List<AdvancedToken> getAdvancedTokenList() {
        return advancedTokenList;
    }

    List<AdvancedToken> advancedTokenList;

    public AdvancedLexer(List<Token> tokenList) {
        this.tokenList = tokenList;
        this.advancedTokenList = new ArrayList<>();
        this.index = 0;
    }


    public void run() throws SyntaxException {
        for (; index < tokenList.size(); index++)
        {
            switch(tokenList.get(index).getLexeme()) {
                case FUNC:
                    createFUNC();
                    break;
                case IF:
                    createIF();
                    break;
                case WHILE:
                    createWHILE();
                    break;
                case VAR:
                    createASSIGN();
                    break;
                case L_F_B:
                    createLFB();
                    break;
                case R_F_B:
                    createRFB();
                    break;
                case ELSE:
                    createELSE();
                    break;
                case RETURN:
                    createReturn();
                    break;
                default:
                    break;
            }
        }
    }

    private void createReturn() throws SyntaxException {
        ReturnToken returnToken = new ReturnToken();
        FormulaToken formulaToken = new FormulaToken();
        createFORMULA(formulaToken);
        returnToken.setFormulaToken(formulaToken);
        this.advancedTokenList.add(returnToken);
        return;
    }

    //region Brackets and Else- leave as is
    private void createELSE() {
        AdvancedToken tmp = new AdvancedToken(AdvancedTokenType.ELSE);
        tmp.addToken(tokenList.get(index));
        this.advancedTokenList.add(tmp);
    }

    private void createRFB() {
        AdvancedToken tmp = new AdvancedToken(AdvancedTokenType.R_F_B);
        tmp.addToken(tokenList.get(index));
        this.advancedTokenList.add(tmp);
    }

    private void createLFB() {
        AdvancedToken tmp = new AdvancedToken(AdvancedTokenType.L_F_B);
        tmp.addToken(tokenList.get(index));
        this.advancedTokenList.add(tmp);
    }
    //endregion

    private void createASSIGN() throws SyntaxException {
        if (this.tokenList.get(index+1).getLexeme() == LexemPatterns.LOG_OP)
        {
            if (!this.tokenList.get(index+1).getValue().equals("="))
                throw new SyntaxException("Error in ASSIGN: no assign lexem token " + index);
            AssignToken assignToken = new AssignToken();
            assignToken.setVar(tokenList.get(index).getValue());

            if (!this.tokenList.get(++index).getValue().equals("="))
                throw new SyntaxException("Error in ASSIGN at Token " + index);
            FormulaToken formulaToken = new FormulaToken();
            createFORMULA(formulaToken);
            assignToken.setFormulaToken(formulaToken);
            this.advancedTokenList.add(assignToken);
        } else {
            throw new SyntaxException("Error in ASSIGN/FUNC_CALL at Token" + index);
        }

    }

    private FunctionCallToken createFUNC_CALL() throws SyntaxException {
        int pos = 1;
        FunctionCallToken functionCallToken = new FunctionCallToken();
        functionCallToken.setFuncName(this.tokenList.get(index).getValue());
        while (this.tokenList.get(index).getLexeme() != LexemPatterns.SEM) {
            switch(this.tokenList.get(++index).getLexeme()) {
                case COMMA:
                    pos++;
                    break;
                case OP:
                    if (!this.tokenList.get(index).equals("-"))
                        throw new SyntaxException("Error in functionCall at token " + index);
                case DIGIT:
                case L_R_B:
                case VAR:
                    index--;
                    pos--;
                    FormulaToken formulaToken = new FormulaToken();
                    createFORMULA(formulaToken);
                    functionCallToken.addParam((FormulaToken) this.advancedTokenList.get(this.advancedTokenList.size() - 1));
                    this.advancedTokenList.remove(this.advancedTokenList.size() - 1);
                    break;
                default:
                    throw new SyntaxException("Error in function call: unexpected symol at token " + index);

            }
            if (pos > 1)
                throw new SyntaxException("Error in function call: invalid arguments at token " + index);
        }
        if (functionCallToken.getParams().size() != 1)
            if (pos != 0)
                throw new SyntaxException("Error in function call: invalid arguments at token " + index);

        return functionCallToken;

    }

    private void createWHILE() throws SyntaxException {
        WhileToken whileToken = new WhileToken();
        if (this.tokenList.get(++index).getLexeme() == LexemPatterns.L_R_B) {
            FormulaToken formulaToken = new FormulaToken();
            createFORMULA(formulaToken);
            whileToken.setFormulaToken(formulaToken);
        } else throw new SyntaxException("Error in WHILE at Token " + index + this.tokenList.get(index).toString());
        this.advancedTokenList.add(whileToken);
    }

    private void createFORMULA(FormulaToken formulaToken) throws SyntaxException {
        int brackets = 0;
        while (this.tokenList.get(index).getLexeme() != LexemPatterns.SEM)
            switch(this.tokenList.get(++index).getLexeme()) {
                case COMMA:
                case SEM:
                    if (brackets != 0)
                        throw new SyntaxException("Error in FORMULA SYNTAX - brackets misplaced at Token " + index);
                    this.advancedTokenList.add(formulaToken);
                    return; // end of the line
                case L_R_B:
                    brackets++;
                    formulaToken.addToken(this.tokenList.get(index));
                    break;
                case R_R_B:
                    brackets--;
                    if (brackets < 0)
                        throw new SyntaxException("Error in FORMULA SYNTAX - brackets at Token " + index);
                    formulaToken.addToken(this.tokenList.get(index));
                    break;
                case OP:
                case LOG_OP:
                case DIGIT:
                    formulaToken.addToken(this.tokenList.get(index));
                    break;
                case VAR:
                    if (this.tokenList.get(index + 1).getLexeme() == LexemPatterns.L_R_B) {
                        formulaToken.addToken(new Token(LexemPatterns.FUNC_IN_FORMULA, "@"));
                        formulaToken.addFunction(createFUNC_CALL());
                    } else
                        formulaToken.addToken(this.tokenList.get(index));
                    break;
                default:
                    throw new SyntaxException("Error in FORMULA SYNTAX - invalid symbol at Token " + index);
            }
        }


    private void createIF() throws SyntaxException {
        IfToken ifToken = new IfToken();
        if (this.tokenList.get(++index).getLexeme() == LexemPatterns.L_R_B) {
            FormulaToken formulaToken = new FormulaToken();
            createFORMULA(formulaToken);
            ifToken.setFormulaToken(formulaToken);
        } else throw new SyntaxException("Error in IF at Token " + index + this.tokenList.get(index).toString());
        this.advancedTokenList.add(ifToken);
        return;
    }

    private void createFUNC() throws SyntaxException {
        int pos;
        int brackets = 1;
        FunctionToken functionToken = new FunctionToken();

        if (this.tokenList.get(++index).getLexeme() == LexemPatterns.VAR)
            functionToken.setFuncName(this.tokenList.get(index).toString());
        else
            throw new SyntaxException("Error in FUNC def at Token " + index);

        if (this.tokenList.get(++index).getLexeme() == LexemPatterns.L_R_B) {
            pos = 0;
            while(brackets != 0) {
                if (brackets < 0)
                    throw new SyntaxException("Round brackets misplaced at function definition at Token " + index);
                switch(this.tokenList.get(++index).getLexeme()) {
                    case R_R_B:
                        brackets--;
                        break;
                    case L_R_B:
                        brackets++;
                        break;
                    case VAR:
                    case DIGIT:
                        pos++;
                        functionToken.addParam(this.tokenList.get(index).toString());
                        break;
                    case COMMA:
                        pos--;
                        break;
                    default:
                        throw new SyntaxException("Error in FUNC def, not a VAR nor COMMA nor DIGIT at Token " + index);
                }
                if (pos > 1)
                    throw new SyntaxException("Error in FUNC def misplaced COMMAS at Token " + index);
            }
        } else throw new SyntaxException("Error in FUNC at Token " + index);
        this.advancedTokenList.add(functionToken);
    }


}
