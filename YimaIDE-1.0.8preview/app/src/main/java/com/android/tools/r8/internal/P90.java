package com.android.tools.r8.internal;

import com.android.tools.r8.naming.C3331k;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.retrace.RetraceResult;
import com.android.tools.r8.retrace.RetraceStackTraceContext;
import com.android.tools.r8.retrace.RetraceThrownExceptionElement;
import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedSourceFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P90 implements RetraceThrownExceptionElement {
    public final Q90 a;
    public final W90 b;
    public final C3331k c;
    public final ClassReference d;

    public P90(Q90 q90, W90 w90, C3331k c3331k, ClassReference classReference) {
        this.a = q90;
        this.b = w90;
        this.c = c3331k;
        this.d = classReference;
    }

    @Override // com.android.tools.r8.retrace.RetraceThrownExceptionElement
    public final RetraceStackTraceContext getContext() {
        J90 j90 = new J90();
        ClassReference classReference = this.d;
        j90.a = classReference;
        return new K90(classReference, j90.b);
    }

    @Override // com.android.tools.r8.retrace.RetraceThrownExceptionElement, com.android.tools.r8.retrace.RetraceElement
    public final RetraceResult getParentResult() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.RetraceThrownExceptionElement
    public final RetracedClassReference getRetracedClass() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetraceThrownExceptionElement
    public final RetracedSourceFile getSourceFile() {
        String str;
        C3331k c3331k = this.c;
        if (c3331k != null) {
            for (com.android.tools.r8.naming.mappinginformation.e eVar : c3331k.c()) {
                eVar.getClass();
                if (eVar instanceof com.android.tools.r8.naming.mappinginformation.a) {
                    str = eVar.a().a;
                }
            }
            str = null;
        } else {
            str = null;
        }
        return new C1410ea0(this.b, str);
    }

    @Override // com.android.tools.r8.retrace.RetraceElement
    public final boolean isCompilerSynthesized() {
        return false;
    }
}
