package ru.mirea.skorobogatov.plang.PolishCalc;

import ru.mirea.skorobogatov.plang.Exceptions.SyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Lexer {
    public List<String> run(String input) throws SyntaxException {
        String temp = input;
        List<String> list = new ArrayList<>();
        int search = 0;
        while (input.length() > 0) {
            for(LexemPatterns lexemPattern: LexemPatterns.values()) {
                Matcher matcher = lexemPattern.getPattern().matcher(input);
                if (matcher.find()) {
                    list.add(input.substring(matcher.start(), matcher.end()));
                    input = matcher.replaceFirst("");
                    search = 0;
                }
                search++;
                if (search > LexemPatterns.values().length * 2)
                    throw new SyntaxException("Unresolved symbol in formula PolishCalc Lexer " + input);
            }
        }
        while (list.contains(" "))
            list.remove(" ");
        return list;
    }
}
