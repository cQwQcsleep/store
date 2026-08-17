package com.intellij.util.io;

import java.io.IOException;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class CorruptedException extends IOException {
    public CorruptedException(Path path) {
        this("Storage corrupted " + path);
    }

    public CorruptedException(String str) {
        super(str);
    }

    public CorruptedException(String str, Throwable th) {
        super(str, th);
    }
}
