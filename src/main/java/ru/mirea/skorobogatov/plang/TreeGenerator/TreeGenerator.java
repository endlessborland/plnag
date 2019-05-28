package ru.mirea.skorobogatov.plang.TreeGenerator;

import ru.mirea.skorobogatov.plang.AdvancedTokens.AdvancedLexer;
import ru.mirea.skorobogatov.plang.AdvancedTokens.AdvancedToken;
import ru.mirea.skorobogatov.plang.Exceptions.SyntaxException;
import ru.mirea.skorobogatov.plang.FunctionSeparator.FunctionSeparator;
import ru.mirea.skorobogatov.plang.Lexer.Lexer;
import ru.mirea.skorobogatov.plang.Lexer.Token;
import ru.mirea.skorobogatov.plang.TreeConstructor.FunctionNode;
import ru.mirea.skorobogatov.plang.TreeConstructor.Node;
import ru.mirea.skorobogatov.plang.TreeConstructor.NodeConstructor;

import java.util.HashMap;
import java.util.List;

public class TreeGenerator {

    String input;
    Node mainNode;
    HashMap<String, Node> functions;

    public TreeGenerator(String input) {
        this.input = input;
    }

    public Node getMainNode() {
        return mainNode;
    }

    public HashMap<String, Node> getFunctions() {
        return functions;
    }

    public void generate() throws SyntaxException {
        functions = new HashMap<>();
        // starting lexer
        Lexer lexer = new Lexer();
        List<Token> tokenList = lexer.run(input);
        //starting advanced lexer
        AdvancedLexer advancedLexer = new AdvancedLexer(tokenList);
        advancedLexer.run();
        List<AdvancedToken> advancedTokenList = advancedLexer.getAdvancedTokenList();
        // separating functions
        FunctionSeparator functionSeparator = new FunctionSeparator(advancedTokenList);
        functionSeparator.run();
        List<List<AdvancedToken>> separatorFunctionList = functionSeparator.getFunctionList();
        // constructing trees for functions
        for (List<AdvancedToken> a : separatorFunctionList) {
            NodeConstructor nodeConstructor = new NodeConstructor(a);
            nodeConstructor.createTree();
            Node topNode = nodeConstructor.getTopNode();
            if (topNode instanceof FunctionNode) {
                String name = ((FunctionNode) topNode).getFunctionToken().getFuncName();
                functions.put(name, topNode);
            }
        }
        NodeConstructor nodeConstructor = new NodeConstructor(functionSeparator.getMainTokenList());
        nodeConstructor.createTree();
        mainNode = nodeConstructor.getTopNode();
    }

}
