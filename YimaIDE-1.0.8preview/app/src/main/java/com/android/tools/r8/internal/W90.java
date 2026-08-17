package com.android.tools.r8.internal;

import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedTypeReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class W90 implements RetracedClassReference {
    public static final /* synthetic */ boolean c = true;
    public final ClassReference a;
    public final boolean b;

    public W90(boolean z, ClassReference classReference) {
        if (!c && classReference == null) {
            x1f.a();
            throw null;
        }
        this.a = classReference;
        this.b = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || W90.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((W90) obj).a);
    }

    @Override // com.android.tools.r8.retrace.RetracedClassReference
    public final String getBinaryName() {
        return this.a.getBinaryName();
    }

    @Override // com.android.tools.r8.retrace.RetracedClassReference
    public final ClassReference getClassReference() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.RetracedClassReference
    public final String getDescriptor() {
        return this.a.getDescriptor();
    }

    @Override // com.android.tools.r8.retrace.RetracedClassReference
    public final RetracedTypeReference getRetracedType() {
        return new C1496fa0(this.a);
    }

    @Override // com.android.tools.r8.retrace.RetracedClassReference
    public final String getTypeName() {
        return this.a.getTypeName();
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.retrace.RetracedClassReference
    public final boolean isKnown() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetracedClassReference
    public final boolean isUnknown() {
        return !this.b;
    }
}
