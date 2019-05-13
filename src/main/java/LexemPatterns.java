import java.util.regex.Pattern;


// ALL THE LEXEMES PATTERNS

public enum LexemPatterns {
    IF(Pattern.compile("^if$")),
    WHILE(Pattern.compile("^while$")),
    VAR(Pattern.compile("^[a-zA-Z]+$")),
    ASSIGN_OP(Pattern.compile("^=$")),
    FORMULA(Pattern.compile("^\\Q[\\E(.*?)\\Q]\\E$")),
    DIGIT(Pattern.compile("^0|[1-9][0-9]*")),
    WS(Pattern.compile("^\\s+")),
    L_F_B(Pattern.compile("^\\{$")),
    R_F_B(Pattern.compile("^}$")),
    SEM(Pattern.compile("^;$"));

    private Pattern pattern;

    LexemPatterns(Pattern pattern) {
        this.pattern = pattern;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
