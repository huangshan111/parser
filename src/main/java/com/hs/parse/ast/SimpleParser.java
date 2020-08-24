package com.hs.parse.ast;

public class SimpleParser {

    /**
     * 最简Parser
     */
    public static ASTNode parse(PeekTokenIterator it) throws ParseException {
        //创建表达式
        var expr = new Expr();

        //吃一个Scalar
        var scalar = new Scalar(it.next());
        //如果没有token 说明是最后一个，直接返回Scalar
        if (!it.hasNext()) {
            return scalar;
        }

        // 到这说明还有token，继续吃一个操作符
        var token = it.nextMatch("+");
        expr.setLexeme(token);
        expr.setLabel(token.getValue());
        expr.setType(ASTNodeType.BINARY_EXPR);

        //添加左节点
        expr.addChild(scalar);
        //递归得到右节点
        ASTNode right = parse(it);
        //添加右节点
        expr.addChild(right);
        return expr;
    }
}
