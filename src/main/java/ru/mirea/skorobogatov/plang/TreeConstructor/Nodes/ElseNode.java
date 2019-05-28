package ru.mirea.skorobogatov.plang.TreeConstructor.Nodes;

import ru.mirea.skorobogatov.plang.TreeConstructor.Runnable;

import java.util.HashMap;

public class ElseNode extends ChooseNode implements Runnable {

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
