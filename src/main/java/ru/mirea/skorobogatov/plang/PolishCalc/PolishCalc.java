package ru.mirea.skorobogatov.plang.PolishCalc;

import ru.mirea.skorobogatov.plang.Exceptions.SyntaxException;

import java.util.*;
import java.lang.*;
import java.io.*;

// many thanks to http://java.mazurok.com/?p=454

public class PolishCalc {

    public static Integer calculate(String str) {
        List<String> expression = ExpressionParser.parse(str);
        return Calc.calc(expression);
    }

    static class ExpressionParser {
        private static String operators = "+-*/<>=!%";
        private static String delimiters = "() " + operators;
        public static boolean flag = true;

        private static boolean isDelimiter(String token) {
            for (int i = 0; i < delimiters.length(); i++) {
                if (token.charAt(0) == delimiters.charAt(i)) return true;
            }
            return false;
        }

        private static boolean isOperator(String token) {
            if (token.equals("u-")) return true;
            for (int i = 0; i < operators.length(); i++) {
                if (token.charAt(0) == operators.charAt(i)) return true;
            }
            return false;
        }

        private static boolean isFunction(String token) {
            if (token.equals("square")) return true;
            return false;
        }

        private static int priority(String token) {
            if (token.equals("(")) return 1;
            if (token.equals("+") || token.equals("-")) return 2;
            if (token.equals("*") || token.equals("/") || token.equals("%")) return 3;
            if (token.equals(">") || token.equals("<") || token.equals("=")) return 4;
            if (token.equals(">=") || token.equals("<=") || token.equals("!=")) return 4;
            return 5;
        }

        public static List<String> parse(String infix) {
            flag = true;
            List<String> postfix = new ArrayList<String>();
            Stack<String> stack = new Stack<String>();
            String str = "";
            Lexer lexer = new Lexer();
            List<String> temp;
            try {
                temp = lexer.run(infix);
            } catch (SyntaxException e) {
                System.err.print(e.getMessage());
                System.exit(1);
                return null;
            }
            String prev = "";
            String curr = "";
            int i = 0;
            while (i < temp.size()) {
                curr = temp.get(i++);
                if (!(i < temp.size()) && isOperator(curr)) {
                    System.err.println("Некорректное выражение.");
                    flag = false;
                    return postfix;
                }
                if (curr.equals(" ")) continue;
                if (isFunction(curr))
                    stack.push(curr);
                else if (isDelimiter(curr)) {
                    if (curr.equals("(")) stack.push(curr);
                    else if (curr.equals(")")) {
                        while (!stack.peek().equals("(")) {
                            postfix.add(stack.pop());
                            if (stack.isEmpty()) {
                                System.err.println("Скобки не согласованы.");
                                flag = false;
                                return postfix;
                            }
                        }
                        stack.pop();
                        if (!stack.isEmpty() && isFunction(stack.peek())) {
                            postfix.add(stack.pop());
                        }
                    }
                    else {
                        if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev)  && !prev.equals(")")))) {
                            // унарный минус
                            curr = "u-";
                        }
                        else {
                            while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
                                postfix.add(stack.pop());
                            }
                        }
                        stack.push(curr);
                    }
                }
                else {
                    postfix.add(curr);
                }
                prev = curr;
            }
            while (!stack.isEmpty()) {
                if (isOperator(stack.peek()))
                    postfix.add(stack.pop());
                else {
                    System.err.println("Скобки не согласованы.");
                    flag = false;
                    return postfix;
                }
            }
            return postfix;
        }
    }

    static class Calc {
        public static Integer calc(List<String> postfix) {
            Stack<Integer> stack = new Stack<Integer>();
            for (String x : postfix) {
                if (x.equals("square")) {
                    Integer tmp = stack.pop();
                    stack.push(tmp * tmp);
                }
                else if (x.equals("+")) stack.push(stack.pop() + stack.pop());
                else if (x.equals("-")) {
                    Integer b = stack.pop(), a = stack.pop();
                    stack.push(a - b);
                }
                else if (x.equals("*")) stack.push(stack.pop() * stack.pop());
                else if (x.equals("/")) {
                    Integer b = stack.pop(), a = stack.pop();
                    stack.push(a / b);
                } else if (x.equals("%")) {
                    Integer b = stack.pop(), a = stack.pop();
                    stack.push(a % b);
                }
                else if (x.equals("u-")) stack.push(-stack.pop());
                else if (x.equals(">")) {
                    Integer b = stack.pop(), a = stack.pop();
                    if (a > b)
                        stack.push(1);
                    else
                        stack.push(0);
                }
                else if (x.equals("<")) {
                    Integer b = stack.pop(), a = stack.pop();
                    if (a < b)
                        stack.push(1);
                    else
                        stack.push(0);
                }
                else if (x.equals("=")) {
                    Integer b = stack.pop(), a = stack.pop();
                    if (a == b)
                        stack.push(1);
                    else
                        stack.push(0);
                } else if (x.equals("!=")) {
                    Integer b = stack.pop(), a = stack.pop();
                    if (a != b)
                        stack.push(1);
                    else
                        stack.push(0);
                } else if (x.equals(">=")) {
                    Integer b = stack.pop(), a = stack.pop();
                    if (a >= b)
                        stack.push(1);
                    else
                        stack.push(0);
                } else if (x.equals("<=")) {
                    Integer b = stack.pop(), a = stack.pop();
                    if (a <= b)
                        stack.push(1);
                    else
                        stack.push(0);
                }
                else stack.push(Integer.valueOf(x));
            }
            return stack.pop();
        }

//        public static void main (String[] args) {
//            Scanner in = new Scanner(System.in);
//            String s = in.nextLine();
//            ExpressionParser n = new ExpressionParser();
//            List<String> expression = n.parse(s);
//            boolean flag = n.flag;
//            if (flag) {
//                for (String x : expression) System.out.print(x + " ");
//                System.out.println();
//                System.out.println(calc(expression));
//            }
//        }
    }
}
