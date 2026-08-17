package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DesugarGraphConsumer {
    void accept(Origin origin, Origin origin2);

    default void acceptProgramNode(Origin origin) {
    }

    void finished();
}
