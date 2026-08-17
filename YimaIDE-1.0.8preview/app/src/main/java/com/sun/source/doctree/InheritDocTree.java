package com.sun.source.doctree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface InheritDocTree extends InlineTagTree {
    default ReferenceTree getSupertype() {
        return null;
    }
}
