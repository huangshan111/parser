package com.hs.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.hs.lexer.LexicalException;
import com.hs.lexer.Token;
import com.hs.lexer.TokenType;

public class TokenTest {

    @Test
    public void test_makeVarOrKeyword() {
        String source = "if true";

        var it = new PeekIterator(source.chars().mapToObj(c -> (char) c));

        Token token = Token.makeVarOrKeyword(it);
        assertEquals(TokenType.KEYWORD, token.getType());
        assertEquals("if", token.getValue());

        it.next();

        Token token2 = Token.makeVarOrKeyword(it);
        assertEquals(TokenType.BOOLEAN, token2.getType());
        assertEquals("true", token2.getValue());
    }

    @Test
    public void test_makeString() throws LexicalException {
        String[] tests = {
                "\"123\"",
                "\'123\'"
        };

        for (String test : tests) {
            var it = new PeekIterator(test.chars().mapToObj(c -> (char) c));
            Token token = Token.makeString(it);
            assertToken(token, test, TokenType.STRING);
        }
    }

    @Test
    public void test_makeOperator() throws LexicalException {
        String[] tests = {
                "++123",
                "+hello",
                "123--456",
                "123-456",
                "123*",
                "123*=",
        };

        String[] results = {"++", "+", "--", "-", "*", "*="};

        int i = 0;
        for (String test : tests) {
            var it = new PeekIterator(test.chars().mapToObj(c -> (char) c), '\0');
            Token token = Token.makeOperator(it);
            assertToken(token, results[i++], TokenType.OPERATOR);
        }
    }

    private void assertToken(Token token, String val, TokenType tokenType) {
        assertEquals(tokenType, token.getType());
        assertEquals(val, token.getValue());
    }
}