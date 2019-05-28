package ru.mirea.skorobogatov.plang.TreeConstructor;

import java.util.HashMap;

public class ElseNode extends ChooseNode implements Runnalbe {

    public ElseNode() {
        super();
    }

    private boolean wasSetToFalse = false;

    @Override
    public Node run(HashMap<String, Integer> varMap) {
        if (wasSetToFalse) {
            wasSetToFalse = false;
            return this.next;
        } else {
            wasSetToFalse = true;
            return this.left;
        }
    }
}
