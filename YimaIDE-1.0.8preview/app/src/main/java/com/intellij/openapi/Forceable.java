package com.intellij.openapi;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface Forceable {
    void force() throws IOException;

    boolean isDirty();
}
