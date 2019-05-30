package ru.mirea.skorobogatov.plang.Lexer;

import ru.mirea.skorobogatov.plang.Exceptions.SyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Lexer {
    public List<Token> run(String input) throws SyntaxException {
        String temp = input;
        List<Token> list = new ArrayList<>();
        int search = 0;
        while (input.length() > 0) {
            for(LexemPatterns lexemPattern: LexemPatterns.values()) {
                Matcher matcher = lexemPattern.getPattern().matcher(input);
                if (matcher.find()) {
                    if (lexemPattern != LexemPatterns.WS)
                        list.add(new Token(lexemPattern, input.substring(matcher.start(), matcher.end())));
                    input = matcher.replaceFirst("");
                    search = 0;
                }
                search++;
                if (search > LexemPatterns.values().length + 3)
                    throw new SyntaxException("Unresolved symbol in " + input);
            }
        }
        return list;
    }
}