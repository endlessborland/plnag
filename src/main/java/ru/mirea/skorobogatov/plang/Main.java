package ru.mirea.skorobogatov.plang;

import ru.mirea.skorobogatov.plang.Runner.Runner;
import ru.mirea.skorobogatov.plang.TreeConstructor.Runnalbe;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String str = "{ b:= (5) f(b) -> c return(c) } func f(n) { if (n < 2) { return (1) } else { p := (n-1) f(p) -> d return (n*d) } }";
        Runner.setUp(str);
        Runner runner = new Runner();
        runner.run(Runner.MainFunction);
        System.out.println(Runner.stack.pop());

        return;
    }

}
