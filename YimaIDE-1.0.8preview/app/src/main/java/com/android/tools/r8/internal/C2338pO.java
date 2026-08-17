package com.android.tools.r8.internal;

import com.android.tools.r8.references.ClassReference;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2338pO extends AbstractC2508rO {
    public final ClassReference a;
    public final String b;

    public C2338pO(ClassReference classReference, String str) {
        this.a = classReference;
        this.b = str;
    }

    @Override // com.android.tools.r8.internal.AbstractC2508rO
    public final AbstractC2508rO a(ClassReference classReference) {
        return new C2338pO(classReference, this.b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && C2338pO.class == obj.getClass()) {
            C2338pO c2338pO = (C2338pO) obj;
            if (this.a.equals(c2338pO.a) && this.b.equals(c2338pO.b)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1767ii
    public final ClassReference getHolderClass() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1767ii
    public final String getName() {
        return this.b;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }
}
