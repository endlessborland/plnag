package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.TreeConstructor.Nodes.Node;

import java.util.HashMap;

public interface Runnable {
    Node run(HashMap<String, Integer> varMap);
}
