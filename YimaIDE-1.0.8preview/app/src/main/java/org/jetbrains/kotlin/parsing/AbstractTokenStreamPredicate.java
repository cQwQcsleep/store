package org.jetbrains.kotlin.parsing;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public abstract class AbstractTokenStreamPredicate implements TokenStreamPredicate {
    @Override // org.jetbrains.kotlin.parsing.TokenStreamPredicate
    public TokenStreamPredicate or(final TokenStreamPredicate tokenStreamPredicate) {
        return new AbstractTokenStreamPredicate() { // from class: org.jetbrains.kotlin.parsing.AbstractTokenStreamPredicate.1
            @Override // org.jetbrains.kotlin.parsing.TokenStreamPredicate
            public boolean matching(boolean z) {
                if (AbstractTokenStreamPredicate.this.matching(z)) {
                    return true;
                }
                return tokenStreamPredicate.matching(z);
            }
        };
    }
}
