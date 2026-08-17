package com.android.tools.r8.internal;

import com.android.tools.r8.naming.C3331k;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.retrace.RetraceThrownExceptionResult;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Q90 implements RetraceThrownExceptionResult {
    public final ClassReference a;
    public final C3331k b;

    public Q90(ClassReference classReference, C3331k c3331k) {
        this.a = classReference;
        this.b = c3331k;
    }

    @Override // com.android.tools.r8.retrace.RetraceThrownExceptionResult, com.android.tools.r8.retrace.RetraceResult
    public final boolean isEmpty() {
        return this.a == null;
    }

    @Override // com.android.tools.r8.retrace.RetraceResult
    public final Stream stream() {
        C3331k c3331k = this.b;
        return Stream.of(new P90(this, new W90(this.b != null, c3331k == null ? this.a : Reference.classFromTypeName(c3331k.a)), this.b, this.a));
    }
}
