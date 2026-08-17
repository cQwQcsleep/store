package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetraceResult;
import com.android.tools.r8.retrace.RetraceTypeElement;
import com.android.tools.r8.retrace.RetraceTypeResult;
import com.android.tools.r8.retrace.RetracedTypeReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class R90 implements RetraceTypeElement {
    public final RetraceTypeResult a;
    public final RetracedTypeReference b;

    public R90(RetraceTypeResult retraceTypeResult, RetracedTypeReference retracedTypeReference) {
        this.a = retraceTypeResult;
        this.b = retracedTypeReference;
    }

    @Override // com.android.tools.r8.retrace.RetraceTypeElement, com.android.tools.r8.retrace.RetraceElement
    public final RetraceResult getParentResult() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.RetraceTypeElement
    public final RetracedTypeReference getType() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetraceElement
    public final boolean isCompilerSynthesized() {
        return false;
    }
}
