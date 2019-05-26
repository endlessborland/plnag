package ru.mirea.skorobogatov.plang.TreeConstructor;

import ru.mirea.skorobogatov.plang.AdvancedTokens.AdvancedToken;
import ru.mirea.skorobogatov.plang.AdvancedTokens.AssignToken;

public class AssignNode extends Node {

    AssignToken assignToken;

    public void setAssignToken(AssignToken assignToken) {
        this.assignToken = assignToken;
    }

    public AssignToken getAssignToken() {

        return assignToken;
    }
}
