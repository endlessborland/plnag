package ru.mirea.skorobogatov.plang.TreeConstructor;

public class ChooseNode extends Node {

    Node left;
    private boolean sw;

    public ChooseNode() {
        super();
        sw = true;
    }

    public Node getLeft() {
        return left;
    }

    @Override
    public Node getNext() {
        if (sw)
            return this.left;
        else
            return this.next;
    }

    @Override
    public void setNext(Node next) {
        if (sw) {
            this.left = next;
            sw = false;
        } else
            this.next = next;

    }
}
