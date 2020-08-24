package com.hs.parse.ast;

import com.hs.lexer.Token;

public class Scalar extends Factor{
    public Scalar(Token token) {
        super(token);
        this.type = ASTNodeType.SCALAR;
    }
}
