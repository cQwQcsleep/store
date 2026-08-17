package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedMethodReference;
import com.android.tools.r8.retrace.RetracedSingleFrame;
import com.android.tools.r8.retrace.RetracedSourceFile;
import java.util.HashSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.da0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1325da0 implements RetracedSingleFrame {
    public final C90 a;
    public final RetracedMethodReference b;
    public final int c;

    public C1325da0(C90 c90, RetracedMethodReference retracedMethodReference, int i) {
        this.a = c90;
        this.b = retracedMethodReference;
        this.c = i;
    }

    @Override // com.android.tools.r8.retrace.RetracedSingleFrame
    public final int getIndex() {
        return this.c;
    }

    @Override // com.android.tools.r8.retrace.RetracedSingleFrame
    public final RetracedMethodReference getMethodReference() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetracedSingleFrame
    public final RetracedSourceFile getSourceFile() {
        C90 c90 = this.a;
        RetracedMethodReference retracedMethodReference = this.b;
        c90.getClass();
        RetracedClassReference holderClass = retracedMethodReference.getHolderClass();
        C1667ha0 c1667ha0 = c90.b.c;
        HashSet hashSet = V90.a;
        return new C1410ea0(holderClass, c1667ha0.a.b(holderClass.getClassReference().getTypeName()));
    }
}
