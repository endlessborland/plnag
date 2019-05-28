package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.*;
import ru.mirea.skorobogatov.plang.Exceptions.SyntaxException;

import java.util.List;

public class NodeConstructor {

    int index;
    List<AdvancedToken> advancedTokenList;

    public Node getTopNode() {
        while (currentNode.getParent() != null)
            currentNode = currentNode.getParent();
        return currentNode.getNext();
    }

    private Node currentNode;

    public NodeConstructor(List<AdvancedToken> advancedTokenList) {
        this.index = 0;
        this.advancedTokenList = advancedTokenList;
        currentNode = new Node();
        currentNode.setParent(null);
        currentNode.setNext(null);
    }

    public void createTree() throws SyntaxException {
        for(; index < advancedTokenList.size(); index++)
            constructor();

    }

    private void constructor() throws SyntaxException {
        switch (advancedTokenList.get(index).getTokenType()) {
            case L_F_B:
                break;
            case VAR_ASSIGN:
                assignConstructor((AssignToken) advancedTokenList.get(index));
                break;
            case FUNC_CALL:
                functionCallConstructor((FunctionCallToken) advancedTokenList.get(index));
                break;
            case FUNC:
                functionNodeConstructor((FunctionToken) advancedTokenList.get(index));
                break;
            case RETURN:
                returnNodeConstructor((ReturnToken) advancedTokenList.get(index));
                break;
            case IF:
                ifConstructor((IfToken) advancedTokenList.get(index));
                break;
            case ELSE:
                elseConstructor();
                break;
            case WHILE:
                whileConstructor((WhileToken) advancedTokenList.get(index));
                break;
            case R_F_B:
                Node temp = currentNode;
                do {
                    try {
                        currentNode = currentNode.getParent();
                    } catch (NullPointerException e) {
                        throw new SyntaxException("Wrong figure brackets placement");
                    }
                } while (!((currentNode instanceof WhileNode || currentNode instanceof  IfNode || currentNode instanceof ElseNode) &&((ChooseNode)currentNode).isFinalized() == false));
                temp.setNext(currentNode);
                ((ChooseNode) currentNode).finalize();
                break;
        }
    }

    private void elseConstructor() {
        ElseNode elseNode = new ElseNode();
        currentNode.setNext(elseNode);
        elseNode.setParent(currentNode);
        currentNode = elseNode;
    }

    private void ifConstructor(IfToken ifToken) {
        IfNode ifNode = new IfNode();
        ifNode.setFormula(ifToken.getFormulaToken());
        currentNode.setNext(ifNode);
        ifNode.setParent(currentNode);
        currentNode = ifNode;
    }

    private void whileConstructor(WhileToken whileToken) {
        WhileNode whileNode = new WhileNode();
        whileNode.setFormulaToken(whileToken.getFormulaToken());
        currentNode.setNext(whileNode);
        whileNode.setParent(currentNode);
        currentNode = whileNode;
    }

    private void assignConstructor(AssignToken assignToken) {
        AssignNode assignNode = new AssignNode();
        assignNode.setAssignToken(assignToken);
        currentNode.setNext(assignNode);
        assignNode.setParent(currentNode);
        currentNode = assignNode;
    }

    private void functionCallConstructor(FunctionCallToken functionCallToken) {
        FunctionCallNode functionCallNode = new FunctionCallNode();
        functionCallNode.setFunctionCallToken(functionCallToken);
        currentNode.setNext(functionCallNode);
        functionCallNode.setParent(currentNode);
        currentNode = functionCallNode;
    }

    private void returnNodeConstructor(ReturnToken returnToken) {
        ReturnNode returnNode = new ReturnNode();
        returnNode.setFormulaToken(returnToken.getFormulaToken());
        currentNode.setNext(returnNode);
        returnNode.setParent(currentNode);
        returnNode.setNext(null);
        currentNode = returnNode;
    }

    private void functionNodeConstructor(FunctionToken functionToken) {
        FunctionNode functionNode = new FunctionNode();
        functionNode.setFunctionToken(functionToken);
        currentNode.setNext(functionNode);
        functionNode.setParent(currentNode);
        currentNode = functionNode;
    }

}
