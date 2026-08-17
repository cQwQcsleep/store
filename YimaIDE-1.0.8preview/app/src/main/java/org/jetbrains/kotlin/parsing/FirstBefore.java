package org.jetbrains.kotlin.parsing;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class FirstBefore extends AbstractTokenStreamPattern {
    private final TokenStreamPredicate lookFor;
    private final TokenStreamPredicate stopAt;

    public FirstBefore(TokenStreamPredicate tokenStreamPredicate, TokenStreamPredicate tokenStreamPredicate2) {
        this.lookFor = tokenStreamPredicate;
        this.stopAt = tokenStreamPredicate2;
    }

    @Override // org.jetbrains.kotlin.parsing.TokenStreamPattern
    public boolean processToken(int i, boolean z) {
        if (!this.lookFor.matching(z)) {
            return this.stopAt.matching(z);
        }
        this.lastOccurrence = i;
        return true;
    }
}
