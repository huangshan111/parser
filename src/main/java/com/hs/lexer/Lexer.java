package com.hs.lexer;

import java.util.ArrayList;
import java.util.stream.Stream;

import com.hs.common.AlphabetHelper;
import com.hs.common.PeekIterator;

public class Lexer {

    public ArrayList<Token> analyse(Stream source) throws LexicalException {

        ArrayList<Token> tokens = new ArrayList<>();

        var it = new PeekIterator<Character>(source, '\0');

        while (it.hasNext()) {

            char c = it.next();

            if (c == 0) {
                break;
            }

            char lookahead = it.peek();

            if (c == ' ' || c == '\n') {
                continue;
            }

            //删除注释
            if (c == '/') {
                if (lookahead == '/') {
                    while (it.hasNext() && (c = it.next()) != '\n') {
                    }
                    continue;
                } else if (lookahead == '*') {
                    boolean isValid = false;
                    it.next(); //吃掉这个*，防止/*/通过
                    while (it.hasNext()) {
                        var p = it.next();
                        if (p == '*' && it.peek() == '/') {
                            it.next();
                            isValid = true;
                            break;
                        }
                    }
                    if (!isValid) {
                        throw new LexicalException("comment not match");
                    }
                    continue;
                }
            }

            if (c == '{' || c == '(' || c == '}' || c == ')') {
                tokens.add(new Token(TokenType.BRACKET, c + ""));
                continue;
            }

            if (c == '\'' || c == '"') {
                it.putBack();
                tokens.add(Token.makeString(it));
                continue;
            }

            if (AlphabetHelper.isLetter(c)) {
                it.putBack();
                tokens.add(Token.makeVarOrKeyword(it));
                continue;
            }

            if (AlphabetHelper.isNumber(c)) {
                it.putBack();
                tokens.add(Token.makeNumber(it));
                continue;
            }

            if ((c == '+' || c == '-' || c == '.') && AlphabetHelper.isNumber(lookahead)) {
                var lastToken = tokens.size() > 0 ? tokens.get(tokens.size() - 1) : null;
                if (lastToken == null || !lastToken.isValue() || lastToken.isOperator()) {
                    it.putBack();
                    System.out.println(c);
                    tokens.add(Token.makeNumber(it));
                    continue;
                }
            }

            if (AlphabetHelper.isOperator(c)) {
                it.putBack();
                tokens.add(Token.makeOperator(it));
                continue;
            }
            throw new LexicalException(c);
        }

        return tokens;
    }
}
