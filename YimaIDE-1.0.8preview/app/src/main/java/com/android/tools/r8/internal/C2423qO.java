package com.android.tools.r8.internal;

import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.Reference;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2423qO extends AbstractC2508rO {
    public final MethodReference a;

    public C2423qO(MethodReference methodReference) {
        this.a = methodReference;
    }

    @Override // com.android.tools.r8.internal.AbstractC2508rO
    public final AbstractC2508rO a(ClassReference classReference) {
        return new C2423qO(Reference.method(classReference, this.a.getMethodName(), this.a.getFormalTypes(), this.a.getReturnType()));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2423qO.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((C2423qO) obj).a);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1767ii
    public final ClassReference getHolderClass() {
        return this.a.getHolderClass();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1767ii
    public final String getName() {
        return this.a.getMethodName();
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC2508rO
    public final C2423qO a() {
        return this;
    }
}
