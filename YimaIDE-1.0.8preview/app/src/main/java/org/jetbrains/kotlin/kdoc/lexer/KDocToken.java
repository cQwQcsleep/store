package org.jetbrains.kotlin.kdoc.lexer;

import org.jetbrains.kotlin.lexer.KtToken;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class KDocToken extends KtToken {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugName", "org/jetbrains/kotlin/kdoc/lexer/KDocToken", "<init>"));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public KDocToken(String str) {
        super(str);
        if (str == null) {
            $$$reportNull$$$0(0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KDocToken(String str, int i) {
        super(str, i);
        if (str == null) {
            $$$reportNull$$$0(1);
        }
    }
}
