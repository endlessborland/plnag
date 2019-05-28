package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.FunctionCallToken;
import ru.mirea.skorobogatov.plang.Runner.Runner;

import java.util.HashMap;

public class FunctionCallNode extends Node implements Runnalbe{

    FunctionCallToken functionCallToken;

    public FunctionCallNode() {
        super();
    }

    public void setFunctionCallToken(FunctionCallToken functionCallToken) {
        this.functionCallToken = functionCallToken;
    }

    public FunctionCallToken getFunctionCallToken() {

        return functionCallToken;
    }

    @Override
    public Node run(HashMap<String, Integer> varMap) {
        Runner runner = new Runner();
        String funcName = this.functionCallToken.getFuncName();
        for (int i = 0; i < this.functionCallToken.getParamAmount(); i++)
            Runner.stack.push(varMap.get(this.functionCallToken.getParams().get(i)));
        runner.run(Runner.functions.get(funcName));
        Integer a = Runner.stack.pop();
        varMap.put(functionCallToken.getVarName(), a);
        return this.getNext();
    }
}
