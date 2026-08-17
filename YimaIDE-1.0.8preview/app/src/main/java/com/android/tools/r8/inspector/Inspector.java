package com.android.tools.r8.inspector;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface Inspector {
    void forEachClass(Consumer<ClassInspector> consumer);
}
