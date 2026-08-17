package com.android.tools.r8.inspector;

import com.android.tools.r8.references.FieldReference;
import java.util.Optional;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface FieldInspector {
    FieldReference getFieldReference();

    Optional<ValueInspector> getInitialValue();

    boolean isFinal();

    boolean isStatic();
}
