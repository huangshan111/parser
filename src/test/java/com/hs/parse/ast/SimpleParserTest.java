package com.hs.parse.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.hs.lexer.Lexer;
import com.hs.lexer.LexicalException;
import com.hs.lexer.Token;

class SimpleParserTest {

    @Test
    void parse() throws LexicalException, ParseException {
        var lexer = new Lexer();
        String expression = "1+2+3+4";
        ArrayList<Token> tokens = lexer.analyse(expression.chars().mapToObj(c -> (char) c));
        ASTNode expr = SimpleParser.parse(new PeekTokenIterator(tokens.stream()));

        assertEquals(expr.getChildren().size(), 2);

        assertEquals(expr.getChild(0).getLexeme().getValue(), "1");

        ASTNode expr1 = expr.getChild(1);
        assertEquals(expr1.getLexeme().getValue(), "+");
        assertEquals(expr1.getChild(0).getLexeme().getValue(), "2");

        ASTNode expr2 = expr1.getChild(1);
        assertEquals(expr2.getLexeme().getValue(), "+");
        assertEquals(expr2.getChild(0).getLexeme().getValue(), "3");

        ASTNode expr3 = expr2.getChild(1);
        assertEquals(expr3.getType(), ASTNodeType.SCALAR);
        assertEquals(expr3.getLexeme().getValue(), "4");

        expr.print(0);
    }
}