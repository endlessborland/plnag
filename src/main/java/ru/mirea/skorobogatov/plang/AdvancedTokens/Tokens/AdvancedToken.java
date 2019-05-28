package ru.mirea.skorobogatov.plang.AdvancedTokens.Tokens;

import ru.mirea.skorobogatov.plang.Lexer.Token;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdvancedToken implements Serializable {
    List<Token> tokens;
    AdvancedTokenType tokenType;

    public AdvancedTokenType getTokenType() {
        return tokenType;
    }

    public void print() {
        for (Token token: tokens) {
            System.out.println(token.toString());
        }
    }

    public AdvancedToken(AdvancedTokenType tokenType) {
        this.tokenType = tokenType;
        tokens = new ArrayList<>();
    }

    public void addToken(Token t) {
        tokens.add(t);
    }

}
