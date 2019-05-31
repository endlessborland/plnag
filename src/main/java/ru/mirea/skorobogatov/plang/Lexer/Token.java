package ru.mirea.skorobogatov.plang.Lexer;

import ru.mirea.skorobogatov.plang.Lexer.LexemPatterns;

import java.io.Serializable;

public class Token implements Serializable {
    private LexemPatterns lexemPattern;
    private String value;

    public Token(LexemPatterns lexemPattern, String value) {
        this.lexemPattern = lexemPattern;
        this.value = value;
    }


    public LexemPatterns getLexeme() {
        return lexemPattern;
    }
    public String getValue()  {
        return value;
    }
    public String toString() {
        return value;
    }
}
