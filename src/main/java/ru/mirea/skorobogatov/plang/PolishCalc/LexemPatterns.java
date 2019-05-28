package ru.mirea.skorobogatov.plang.PolishCalc;

import java.util.regex.Pattern;

public enum LexemPatterns {
    DIGIT(Pattern.compile("^0|^[1-9][0-9]*")),
    OP(Pattern.compile("^\\+|^-|^\\*|^/|^%|^\\^")),
    LOG_OP(Pattern.compile("^<|^>|^<=|^>=|^=|^!=")),
    WS(Pattern.compile("^\\s")),
    L_R_B(Pattern.compile("^\\(")),
    R_R_B(Pattern.compile("^\\)"));


    private Pattern pattern;

    LexemPatterns(Pattern pattern) {
        this.pattern = pattern;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
