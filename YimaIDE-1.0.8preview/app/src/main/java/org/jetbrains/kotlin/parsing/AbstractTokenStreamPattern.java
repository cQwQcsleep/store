package org.jetbrains.kotlin.parsing;

import com.intellij.psi.tree.IElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public abstract class AbstractTokenStreamPattern implements TokenStreamPattern {
    protected int lastOccurrence = -1;

    public void fail() {
        this.lastOccurrence = -1;
    }

    @Override // org.jetbrains.kotlin.parsing.TokenStreamPattern
    public boolean handleUnmatchedClosing(IElementType iElementType) {
        return false;
    }

    @Override // org.jetbrains.kotlin.parsing.TokenStreamPattern
    public boolean isTopLevel(int i, int i2, int i3, int i4) {
        return i3 == 0 && i2 == 0 && i4 == 0 && i == 0;
    }

    public void reset() {
        this.lastOccurrence = -1;
    }

    @Override // org.jetbrains.kotlin.parsing.TokenStreamPattern
    public int result() {
        return this.lastOccurrence;
    }
}
