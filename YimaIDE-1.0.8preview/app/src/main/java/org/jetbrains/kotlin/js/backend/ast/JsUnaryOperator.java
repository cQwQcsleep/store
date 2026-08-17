package org.jetbrains.kotlin.js.backend.ast;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public enum JsUnaryOperator implements JsOperator {
    BIT_NOT("~", 14, 8),
    DEC("--", 14, 12),
    DELETE("delete", 14, 8),
    INC("++", 14, 12),
    NEG("-", 14, 8),
    POS("+", 14, 8),
    NOT("!", 14, 8),
    TYPEOF("typeof", 14, 8),
    VOID("void", 14, 8);

    private final int mask;
    private final int precedence;
    private final String symbol;

    JsUnaryOperator(String str, int i, int i2) {
        this.symbol = str;
        this.precedence = i;
        this.mask = i2;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsOperator
    public int getPrecedence() {
        return this.precedence;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsOperator
    public String getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsOperator
    public boolean isKeyword() {
        return this == DELETE || this == TYPEOF || this == VOID;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsOperator
    public boolean isLeftAssociative() {
        return (this.mask & 1) != 0;
    }

    public boolean isModifying() {
        return this == DEC || this == INC || this == DELETE;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.symbol;
    }
}
