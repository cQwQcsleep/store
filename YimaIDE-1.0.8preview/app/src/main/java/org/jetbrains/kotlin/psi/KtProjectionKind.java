package org.jetbrains.kotlin.psi;

import org.jetbrains.kotlin.lexer.KtSingleValueToken;
import org.jetbrains.kotlin.lexer.KtTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public enum KtProjectionKind {
    IN(KtTokens.IN_KEYWORD),
    OUT(KtTokens.OUT_KEYWORD),
    STAR(KtTokens.MUL),
    NONE(null);

    private final KtSingleValueToken token;

    KtProjectionKind(KtSingleValueToken ktSingleValueToken) {
        this.token = ktSingleValueToken;
    }

    public KtSingleValueToken getToken() {
        return this.token;
    }
}
