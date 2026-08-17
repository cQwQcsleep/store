package com.google.common.base;

import com.google.errorprone.annotations.DoNotMock;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@DoNotMock("Use an instance of one of the Finalizable*Reference classes")
public interface FinalizableReference {
    void finalizeReferent();
}
