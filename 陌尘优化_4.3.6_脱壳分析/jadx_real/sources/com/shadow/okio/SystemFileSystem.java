package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.FileSystem;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class SystemFileSystem {
    public static final /* synthetic */ FileSystem getSYSTEM(FileSystem.Companion companion) {
        CloseableKt.checkNotNullParameter(companion, "<this>");
        return FileSystem.SYSTEM;
    }

    public static /* synthetic */ void getSYSTEM$annotations(FileSystem.Companion companion) {
    }
}
