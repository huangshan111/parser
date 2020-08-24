package com.hs.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LexerTest {

    @Test
    public void test_expression() throws LexicalException {
        var lexer = new Lexer();
        String source = "(a+b)^100.12==+100-20";
        var tokens = lexer.analyse(source.chars().mapToObj(c -> (char) c));
        assertEquals(11, tokens.size());
        assertToken(tokens.get(0), "(", TokenType.BRACKET);
        assertToken(tokens.get(1), "a", TokenType.VARIABLE);
        assertToken(tokens.get(2), "+", TokenType.OPERATOR);
        assertToken(tokens.get(3), "b", TokenType.VARIABLE);
        assertToken(tokens.get(4), ")", TokenType.BRACKET);
        assertToken(tokens.get(5), "^", TokenType.OPERATOR);
        assertToken(tokens.get(6), "100.12", TokenType.FLOAT);
        assertToken(tokens.get(7), "==", TokenType.OPERATOR);
        assertToken(tokens.get(8), "+100", TokenType.INTEGER);
        assertToken(tokens.get(9), "-", TokenType.OPERATOR);
        assertToken(tokens.get(10), "20", TokenType.INTEGER);
    }

    @Test
    public void test_comment() throws LexicalException {
        var lexer = new Lexer();
        String source = "/* var a = 1 */ abc=1";
        var tokens = lexer.analyse(source.chars().mapToObj(c -> (char) c));
        assertEquals(3, tokens.size());
        assertToken(tokens.get(0), "abc", TokenType.VARIABLE);
        assertToken(tokens.get(1), "=", TokenType.OPERATOR);
        assertToken(tokens.get(2), "1", TokenType.INTEGER);
    }

    @Test
    public void test01() {
        char c1 = '1';
        char c2 = '\1';
        System.out.println((int) c1);
        System.out.println((int) c2);
    }

    private void assertToken(Token token, String val, TokenType tokenType) {
        assertEquals(tokenType, token.getType());
        assertEquals(val, token.getValue());
    }
}