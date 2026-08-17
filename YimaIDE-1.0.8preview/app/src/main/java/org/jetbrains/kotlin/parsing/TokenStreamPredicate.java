package org.jetbrains.kotlin.parsing;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface TokenStreamPredicate {
    boolean matching(boolean z);

    TokenStreamPredicate or(TokenStreamPredicate tokenStreamPredicate);
}
