package com.android.tools.r8.retrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetracedSingleFrame {
    int getIndex();

    RetracedMethodReference getMethodReference();

    RetracedSourceFile getSourceFile();
}
