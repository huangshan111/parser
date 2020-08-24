package com.hs.parse.ast;

// HOF: High order function
@FunctionalInterface
public interface ExprHOF {

    ASTNode hoc() throws ParseException;

}
