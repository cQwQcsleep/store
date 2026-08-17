package com.sun.source.doctree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ValueTree extends InlineTagTree {
    default TextTree getFormat() {
        return null;
    }

    ReferenceTree getReference();
}
