package com.android.tools.r8.inspector;

import com.android.tools.r8.references.ClassReference;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ClassInspector {
    void forEachField(Consumer<FieldInspector> consumer);

    void forEachMethod(Consumer<MethodInspector> consumer);

    ClassReference getClassReference();

    String getSourceFile();
}
