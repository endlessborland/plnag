package ru.mirea.skorobogatov.plang.TreeConstructor;

import java.util.HashMap;

public class ChooseNode extends Node{

    Node left;
    private boolean setsw = true;
    private boolean finalized = false;

    public void finalize() {
        finalized = true;
    }

    public boolean isFinalized() {
        return finalized;
    }

    public ChooseNode() {
        super();
    }

    public Node getLeft() {
        return left;
    }


    @Override
    public void setNext(Node next) {
        if (setsw) {
            this.left = next;
            setsw = false;
        } else
            this.next = next;
    }
}
