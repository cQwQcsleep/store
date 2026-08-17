package com.android.tools.r8.retrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetracedSourceFile {
    String getOrInferSourceFile();

    String getOrInferSourceFile(String str);

    String getSourceFile();

    boolean hasRetraceResult();
}
