package org.jetbrains.kotlin.js.backend.ast;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public enum JsBinaryOperator implements JsOperator {
    MUL("*", 13, 3),
    DIV("/", 13, 3),
    MOD("%", 13, 3),
    ADD("+", 12, 3),
    SUB("-", 12, 3),
    SHL("<<", 11, 3),
    SHR(">>", 11, 3),
    SHRU(">>>", 11, 3),
    LT("<", 10, 3),
    LTE("<=", 10, 3),
    GT(">", 10, 3),
    GTE(">=", 10, 3),
    INSTANCEOF("instanceof", 10, 3),
    INOP("in", 10, 3),
    EQ("==", 9, 3),
    NEQ("!=", 9, 3),
    REF_EQ("===", 9, 3),
    REF_NEQ("!==", 9, 3),
    BIT_AND("&", 8, 3),
    BIT_XOR("^", 7, 3),
    BIT_OR("|", 6, 3),
    AND("&&", 5, 3),
    OR("||", 4, 3),
    ASG("=", 2, 2),
    ASG_ADD("+=", 2, 2),
    ASG_SUB("-=", 2, 2),
    ASG_MUL("*=", 2, 2),
    ASG_DIV("/=", 2, 2),
    ASG_MOD("%=", 2, 2),
    ASG_SHL("<<=", 2, 2),
    ASG_SHR(">>=", 2, 2),
    ASG_SHRU(">>>=", 2, 2),
    ASG_BIT_AND("&=", 2, 2),
    ASG_BIT_OR("|=", 2, 2),
    ASG_BIT_XOR("^=", 2, 2),
    COMMA(",", 1, 3);

    private final int mask;
    private final int precedence;
    private final String symbol;

    JsBinaryOperator(String str, int i, int i2) {
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

    public boolean isAssignment() {
        return getPrecedence() == ASG.getPrecedence();
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsOperator
    public boolean isKeyword() {
        return this == INSTANCEOF || this == INOP;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsOperator
    public boolean isLeftAssociative() {
        return (this.mask & 1) != 0;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.symbol;
    }
}
