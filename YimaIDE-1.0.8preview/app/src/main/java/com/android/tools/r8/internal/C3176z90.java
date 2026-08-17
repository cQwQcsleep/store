package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetraceClassElement;
import com.android.tools.r8.retrace.RetraceFieldElement;
import com.android.tools.r8.retrace.RetraceResult;
import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedFieldReference;
import com.android.tools.r8.retrace.RetracedSourceFile;
import java.util.HashSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.z90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3176z90 implements RetraceFieldElement {
    public static final /* synthetic */ boolean e = true;
    public final Z90 a;
    public final A90 b;
    public final C3007x90 c;
    public final com.android.tools.r8.naming.V d;

    public C3176z90(A90 a90, C3007x90 c3007x90, Z90 z90, com.android.tools.r8.naming.V v) {
        this.c = c3007x90;
        this.a = z90;
        this.b = a90;
        this.d = v;
    }

    @Override // com.android.tools.r8.retrace.RetraceFieldElement
    public final RetraceClassElement getClassElement() {
        return this.c;
    }

    @Override // com.android.tools.r8.retrace.RetraceFieldElement
    public final RetracedFieldReference getField() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.RetraceFieldElement, com.android.tools.r8.retrace.RetraceElement
    public final RetraceResult getParentResult() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetraceFieldElement
    public final RetracedSourceFile getSourceFile() {
        RetracedClassReference holderClass = this.a.getHolderClass();
        C1667ha0 c1667ha0 = this.b.c;
        HashSet hashSet = V90.a;
        return new C1410ea0(holderClass, c1667ha0.a.b(holderClass.getClassReference().getTypeName()));
    }

    @Override // com.android.tools.r8.retrace.RetraceElement
    public final boolean isCompilerSynthesized() {
        com.android.tools.r8.naming.V v = this.d;
        return v != null && v.g();
    }

    @Override // com.android.tools.r8.retrace.RetraceFieldElement
    public final boolean isUnknown() {
        if (!e) {
            boolean z = this.d == null;
            Z90 z90 = this.a;
            z90.getClass();
            if (z != (!(z90 instanceof X90))) {
                x1f.a();
                return false;
            }
        }
        Z90 z91 = this.a;
        z91.getClass();
        return !(z91 instanceof X90);
    }
}
