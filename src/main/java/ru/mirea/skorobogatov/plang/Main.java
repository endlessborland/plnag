package ru.mirea.skorobogatov.plang;

import ru.mirea.skorobogatov.plang.Runner.Runner;

public class Main {


    public static void main(String[] args) {
        String str = "{ p = function(f1(a + b), 5); }";
        Runner.setUp(str);
        Runner runner = new Runner();
        runner.run(Runner.MainFunction);
        System.out.println(Runner.stack.pop());

        return;
    }

}
