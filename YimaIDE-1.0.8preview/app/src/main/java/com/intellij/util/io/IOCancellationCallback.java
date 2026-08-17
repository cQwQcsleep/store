package com.intellij.util.io;

import com.intellij.openapi.progress.ProcessCanceledException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface IOCancellationCallback {
    void checkCancelled() throws ProcessCanceledException;

    void interactWithUI();
}
