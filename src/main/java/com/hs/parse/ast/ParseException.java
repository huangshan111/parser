package com.hs.parse.ast;

import com.hs.lexer.Token;

public class ParseException extends Exception {

    private String msg;

    public ParseException(Token token) {
        this.msg = String.format("syntax error,unexpect token %s", token.getValue());
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
