package com.hs.parse.ast;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.hs.lexer.Lexer;
import com.hs.lexer.LexicalException;
import com.hs.lexer.Token;

class ExprTest {

    @Test
    void parse() throws LexicalException, ParseException {
        String expression = "2+3*4";
        ArrayList<Token> tokens = new Lexer().analyse(expression.chars().mapToObj(c -> (char) c));
        ASTNode expr = Expr.parse(new PeekTokenIterator(tokens.stream()));
        Token token = expr.getLexeme();
    }
}