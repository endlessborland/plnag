package ru.mirea.skorobogatov.plang.Lexer;

import java.util.regex.Pattern;


// ALL THE LEXEMES PATTERNS

public enum LexemPatterns {
    IF(Pattern.compile("^if")),
    FUNC_ASSIGN(Pattern.compile("^->")),
    RETURN(Pattern.compile("^return ")),
    ELSE(Pattern.compile("^else ")),
    WHILE(Pattern.compile("^while ")),
    FUNC(Pattern.compile("^func ")),
    VAR(Pattern.compile("^[a-zA-Z]+")),
    DIGIT(Pattern.compile("^0|^[1-9][0-9]*")),
    ASSIGN_OP(Pattern.compile("^:=")),
    OP(Pattern.compile("^\\+|^-|^\\*|^/|^%|^\\^")),
    LOG_OP(Pattern.compile("^<|^>|^<=|^>=|^=|^!=")),
    WS(Pattern.compile("^\\s")),
    L_F_B(Pattern.compile("^\\{")),
    R_F_B(Pattern.compile("^}")),
    L_R_B(Pattern.compile("^\\(")),
    R_R_B(Pattern.compile("^\\)")),
    SEM(Pattern.compile("^;")),
    COMMA(Pattern.compile("^,")),
    // INTERNAL USE ONLY
    FUNC_IN_FORMULA;

    private Pattern pattern;

    LexemPatterns(Pattern pattern) {
        this.pattern = pattern;
    }

    LexemPatterns() {};

    public Pattern getPattern() {
        return pattern;
    }

    public String stringPattern() {
        return this.getPattern().pattern();
    }
}
