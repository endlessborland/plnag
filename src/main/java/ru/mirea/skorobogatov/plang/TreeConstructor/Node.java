package ru.mirea.skorobogatov.plang.TreeConstructor;

import java.io.Serializable;

public class Node implements Serializable {
    Node parent;
    Node next;


    public Node() {
        this.parent = null;
        this.next = null;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }


}
