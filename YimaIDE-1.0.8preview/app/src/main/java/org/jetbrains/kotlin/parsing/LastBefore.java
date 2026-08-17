package org.jetbrains.kotlin.parsing;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class LastBefore extends AbstractTokenStreamPattern {
    private final boolean dontStopRightAfterOccurrence;
    private final TokenStreamPredicate lookFor;
    private boolean previousLookForResult;
    private final TokenStreamPredicate stopAt;

    private LastBefore(TokenStreamPredicate tokenStreamPredicate, TokenStreamPredicate tokenStreamPredicate2, boolean z) {
        this.lookFor = tokenStreamPredicate;
        this.stopAt = tokenStreamPredicate2;
        this.dontStopRightAfterOccurrence = z;
    }

    @Override // org.jetbrains.kotlin.parsing.TokenStreamPattern
    public boolean processToken(int i, boolean z) {
        boolean zMatching = this.lookFor.matching(z);
        if (zMatching) {
            this.lastOccurrence = i;
        }
        if (this.stopAt.matching(z) && z && (!this.dontStopRightAfterOccurrence || !this.previousLookForResult)) {
            return true;
        }
        this.previousLookForResult = zMatching;
        return false;
    }

    @Override // org.jetbrains.kotlin.parsing.AbstractTokenStreamPattern
    public void reset() {
        super.reset();
        this.previousLookForResult = false;
    }

    public LastBefore(TokenStreamPredicate tokenStreamPredicate, TokenStreamPredicate tokenStreamPredicate2) {
        this(tokenStreamPredicate, tokenStreamPredicate2, false);
    }
}
