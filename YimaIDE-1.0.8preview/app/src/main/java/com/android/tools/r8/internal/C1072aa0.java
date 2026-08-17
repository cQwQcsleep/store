package com.android.tools.r8.internal;

import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.TypeReference;
import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedMethodReference;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aa0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1072aa0 extends AbstractC1241ca0 implements RetracedMethodReference.KnownRetracedMethodReference {
    public static final /* synthetic */ boolean e = true;
    public final MethodReference d;

    public C1072aa0(MethodReference methodReference, OptionalInt optionalInt) {
        super(optionalInt);
        if (e || methodReference != null) {
            this.d = methodReference;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1241ca0
    public final C1072aa0 a() {
        return this;
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference
    public final RetracedMethodReference.KnownRetracedMethodReference asKnown() {
        return this;
    }

    @Override // java.lang.Comparable
    public final int compareTo(RetracedMethodReference retracedMethodReference) {
        return AbstractC1241ca0.c.compare(this, retracedMethodReference);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && C1072aa0.class == obj.getClass()) {
            C1072aa0 c1072aa0 = (C1072aa0) obj;
            if (this.b == c1072aa0.b && this.d.equals(c1072aa0.d)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference.KnownRetracedMethodReference
    public final List getFormalTypes() {
        return this.d.getFormalTypes();
    }

    @Override // com.android.tools.r8.retrace.RetracedClassMemberReference
    public final RetracedClassReference getHolderClass() {
        return new W90(true, this.d.getHolderClass());
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference
    public final String getMethodName() {
        return this.d.getMethodName();
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference.KnownRetracedMethodReference
    public final MethodReference getMethodReference() {
        return this.d;
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference.KnownRetracedMethodReference
    public final TypeReference getReturnType() {
        if (e || !isVoid()) {
            return this.d.getReturnType();
        }
        x1f.a();
        return null;
    }

    public final int hashCode() {
        return Objects.hash(this.d, this.b);
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference.KnownRetracedMethodReference
    public final boolean isVoid() {
        return this.d.getReturnType() == null;
    }
}
