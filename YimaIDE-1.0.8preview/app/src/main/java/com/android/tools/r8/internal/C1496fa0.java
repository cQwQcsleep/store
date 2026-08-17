package com.android.tools.r8.internal;

import com.android.tools.r8.references.Reference;
import com.android.tools.r8.references.TypeReference;
import com.android.tools.r8.retrace.RetracedTypeReference;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fa0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1496fa0 implements RetracedTypeReference {
    public static final /* synthetic */ boolean b = true;
    public final TypeReference a;

    public C1496fa0(TypeReference typeReference) {
        this.a = typeReference;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1496fa0.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((C1496fa0) obj).a);
    }

    @Override // com.android.tools.r8.retrace.RetracedTypeReference
    public final String getTypeName() {
        if (b || !isVoid()) {
            return this.a.getTypeName();
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.retrace.RetracedTypeReference
    public final TypeReference getTypeReference() {
        return this.a;
    }

    public final int hashCode() {
        return Objects.hash(this.a);
    }

    @Override // com.android.tools.r8.retrace.RetracedTypeReference
    public final boolean isVoid() {
        return this.a == null;
    }

    @Override // com.android.tools.r8.retrace.RetracedTypeReference
    public final TypeReference toArray(int i) {
        return Reference.array(this.a, i);
    }
}
