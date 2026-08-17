package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedMethodReference;
import java.util.OptionalInt;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ba0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1158ba0 extends AbstractC1241ca0 {
    public final AbstractC2508rO d;

    public C1158ba0(AbstractC2508rO abstractC2508rO, OptionalInt optionalInt) {
        super(optionalInt);
        this.d = abstractC2508rO;
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference
    public final /* bridge */ /* synthetic */ RetracedMethodReference.KnownRetracedMethodReference asKnown() {
        return null;
    }

    @Override // java.lang.Comparable
    public final int compareTo(RetracedMethodReference retracedMethodReference) {
        return AbstractC1241ca0.c.compare(this, retracedMethodReference);
    }

    @Override // com.android.tools.r8.retrace.RetracedClassMemberReference
    public final RetracedClassReference getHolderClass() {
        return new W90(false, this.d.getHolderClass());
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference
    public final String getMethodName() {
        return this.d.getName();
    }
}
