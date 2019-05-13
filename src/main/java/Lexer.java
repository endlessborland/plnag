import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

class Lexer {
    public void run(String input) {
        String temp = input;
        List<Token> list = new ArrayList<>();
        while (input.length() > 0) {
            for(LexemPatterns lexemPattern: LexemPatterns.values()) {
                Matcher matcher = lexemPattern.getPattern().matcher(input);
                if (matcher.find()) {
                    list.add(new Token(lexemPattern, input.substring(matcher.start(), matcher.end())));
                    input = matcher.replaceFirst("");
                }
            }
        }
    }
}