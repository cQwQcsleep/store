package com.sun.org.apache.xalan.internal.xsltc.compiler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ArgumentList {
    private final Expression _arg;
    private final ArgumentList _rest;

    public ArgumentList(Expression expression, ArgumentList argumentList) {
        this._arg = expression;
        this._rest = argumentList;
    }

    public String toString() {
        ArgumentList argumentList = this._rest;
        Expression expression = this._arg;
        if (argumentList == null) {
            return expression.toString();
        }
        return expression.toString() + ", " + this._rest.toString();
    }
}
