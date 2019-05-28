package ru.mirea.skorobogatov.plang.Runner;

import org.apache.commons.lang3.SerializationUtils;
import ru.mirea.skorobogatov.plang.Exceptions.SyntaxException;
import ru.mirea.skorobogatov.plang.TreeConstructor.FunctionNode;
import ru.mirea.skorobogatov.plang.TreeConstructor.Node;
import ru.mirea.skorobogatov.plang.TreeConstructor.Runnalbe;
import ru.mirea.skorobogatov.plang.TreeGenerator.TreeGenerator;

import java.util.HashMap;
import java.util.Stack;

public class Runner {

    public static Stack<Integer> stack = new Stack<>();
    public static HashMap<String, Node> functions = new HashMap<>();
    public static Node MainFunction;

    public static void setUp(String input) {
        TreeGenerator treeGenerator = new TreeGenerator(input);
        try {
            treeGenerator.generate();
        } catch (SyntaxException e) {
            System.err.print(e.getMessage());
            System.exit(1);
            return;
        }
        Runner.functions = treeGenerator.getFunctions();
        Runner.MainFunction = treeGenerator.getMainNode();
    }

    private HashMap<String, Integer> varMap;

    public Runner() {
        varMap = new HashMap<>();
    }

    public void run(Node coreNode) {
        Node node = coreNode;
        if (coreNode instanceof FunctionNode) {
            for (int i = 0; i < ((FunctionNode) coreNode).getFunctionToken().getParamAmount(); i++)
                this.varMap.put(((FunctionNode) coreNode).getFunctionToken().getParams().get(i), Runner.stack.pop());
            node = coreNode.getNext();
        }
        while (node != null) {
            Runnalbe r = (Runnalbe) SerializationUtils.clone(node);
            node = r.run(this.varMap);
            System.out.print("");
        }
    }


}
