package com.intellij.util.containers;

import com.intellij.openapi.util.text.StringUtilRt;
import com.intellij.openapi.util.text.Strings;
import it.unimi.dsi.fastutil.Hash;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class FastUtilCharSequenceHashingStrategy implements Hash.Strategy<CharSequence> {
    private final boolean isCaseSensitive;

    public FastUtilCharSequenceHashingStrategy(boolean z) {
        this.isCaseSensitive = z;
    }

    public int hashCode(CharSequence charSequence) {
        if (charSequence == null) {
            return 0;
        }
        return this.isCaseSensitive ? Strings.stringHashCode(charSequence) : Strings.stringHashCodeInsensitive(charSequence);
    }

    public boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        return StringUtilRt.equal(charSequence, charSequence2, this.isCaseSensitive);
    }
}
