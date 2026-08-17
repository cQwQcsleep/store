package com.android.tools.r8.internal;

import com.android.tools.r8.naming.C3331k;
import com.android.tools.r8.retrace.RetraceClassElement;
import com.android.tools.r8.retrace.RetraceMethodElement;
import com.android.tools.r8.retrace.RetraceResult;
import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedMethodReference;
import com.android.tools.r8.retrace.RetracedSourceFile;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class F90 implements RetraceMethodElement {
    public final AbstractC1241ca0 a;
    public final G90 b;
    public final C3007x90 c;
    public final C2935wN d;

    public F90(G90 g90, C3007x90 c3007x90, AbstractC1241ca0 abstractC1241ca0, C2935wN c2935wN) {
        this.c = c3007x90;
        this.b = g90;
        this.a = abstractC1241ca0;
        this.d = c2935wN;
    }

    @Override // com.android.tools.r8.retrace.RetraceMethodElement
    public final RetraceClassElement getClassElement() {
        return this.c;
    }

    @Override // com.android.tools.r8.retrace.RetraceMethodElement, com.android.tools.r8.retrace.RetraceElement
    public final RetraceResult getParentResult() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetraceMethodElement
    public final RetracedMethodReference getRetracedMethod() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.RetraceMethodElement
    public final RetracedSourceFile getSourceFile() {
        RetracedClassReference holderClass = this.a.getHolderClass();
        C1667ha0 c1667ha0 = this.b.c;
        HashSet hashSet = V90.a;
        return new C1410ea0(holderClass, c1667ha0.a.b(holderClass.getClassReference().getTypeName()));
    }

    @Override // com.android.tools.r8.retrace.RetraceElement
    public final boolean isCompilerSynthesized() {
        C2935wN c2935wN = this.d;
        if (c2935wN == null) {
            return false;
        }
        if (c2935wN.b != null && !this.b.isAmbiguous()) {
            return this.d.b.g();
        }
        List<C3331k.b> listA = this.d.a.a();
        if (!listA.isEmpty()) {
            for (com.android.tools.r8.naming.mappinginformation.e eVar : ((C3331k.b) C2847vL.b(listA)).i) {
                if (eVar.l() || (eVar instanceof GV)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.retrace.RetraceMethodElement
    public final boolean isUnknown() {
        AbstractC1241ca0 abstractC1241ca0 = this.a;
        abstractC1241ca0.getClass();
        return !(abstractC1241ca0 instanceof C1072aa0);
    }
}
