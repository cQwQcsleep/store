package com.android.tools.r8.internal;

import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.retrace.RetraceStackTraceContext;
import java.util.OptionalInt;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class K90 implements RetraceStackTraceContext {
    public final ClassReference a;
    public final OptionalInt b;

    public K90(ClassReference classReference, OptionalInt optionalInt) {
        this.a = classReference;
        this.b = optionalInt;
    }
}
