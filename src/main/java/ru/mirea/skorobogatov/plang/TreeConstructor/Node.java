package ru.mirea.skorobogatov.plang.TreeConstructor;

public class Node {
    Node parent;
    Node next;

    public void setParent(Node parent) {

        this.parent = parent;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getParent() {

        return parent;
    }

    public Node getNext() {
        return next;
    }
}
