package com.hs.parse.ast;

import java.util.stream.Stream;

import com.hs.common.PeekIterator;
import com.hs.lexer.Token;
import com.hs.lexer.TokenType;

public class PeekTokenIterator extends PeekIterator<Token> {

    public PeekTokenIterator(Stream<Token> stream) {
        super(stream);
    }

    public Token nextMatch(String value) throws ParseException {
        Token token = this.next();
        if (token.getValue().equals(value)) {
            return token;
        }
        throw new ParseException(token);
    }

    public Token nextMatch(TokenType type) throws ParseException {
        Token token = this.next();
        if (token.getType().equals(type)) {
            return token;
        }
        throw new ParseException(token);
    }
}
