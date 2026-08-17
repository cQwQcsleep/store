package com.intellij.util.diff;

import com.intellij.util.ThreeState;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface ShallowNodeComparator<OldNode, NewNode> {
    ThreeState deepEqual(OldNode oldnode, NewNode newnode);

    boolean hashCodesEqual(OldNode oldnode, NewNode newnode);

    boolean typesEqual(OldNode oldnode, NewNode newnode);
}
