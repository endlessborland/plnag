package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.*;

import java.util.List;

public class TreeConstructor {

    List<AdvancedToken> advancedTokenList;



    private Node funcCall(FuncToken funcToken, Node currentNode) {
        FuncNode node = new FuncNode();
        node.setParent(currentNode);
        node.setNext(null);
        node.setFuncToken(funcToken);
        currentNode.setNext(node);
        return node;
    }

    private Node funcNode(FuncToken funcToken, Node currentNode) {
        for (int i = 0; i < funcToken.getParamAmount(); i++)
        {
            StackReadNode node = new StackReadNode();
            node.setParent(currentNode);
            node.setNext(null);
            node.setVarName(funcToken.getParams().get(i));
            currentNode.setNext(node);
            currentNode = node;
        }
        return currentNode;
    }

    private Node returnNode(ReturnToken returnToken, Node currentNode) {
        ReturnNode node = new ReturnNode();
        node.setParent(currentNode);
        node.setNext(null);
        node.setFormulaToken(returnToken.getFormulaToken());
        currentNode.setNext(node);
        return node;
    }

    private Node assignNode(AssignToken token, Node currentNode) {
        AssignNode node = new AssignNode();
        node.setParent(currentNode);
        node.setAssignToken(token);
        node.setNext(null);
        currentNode.next = node;
        return node;
    }
}
