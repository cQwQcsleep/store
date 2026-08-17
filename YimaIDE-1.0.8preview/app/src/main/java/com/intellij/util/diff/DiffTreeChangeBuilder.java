package com.intellij.util.diff;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface DiffTreeChangeBuilder<OT, NT> {
    void nodeDeleted(OT ot, OT ot2);

    void nodeInserted(OT ot, NT nt, int i);

    void nodeReplaced(OT ot, NT nt);
}
