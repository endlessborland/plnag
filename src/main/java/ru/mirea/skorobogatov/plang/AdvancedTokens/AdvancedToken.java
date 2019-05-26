package ru.mirea.skorobogatov.plang.AdvancedTokens;

import ru.mirea.skorobogatov.plang.Token;

import java.util.ArrayList;
import java.util.List;

public class AdvancedToken {
    List<Token> tokens;
    AdvancedTokenType tokenType;

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
