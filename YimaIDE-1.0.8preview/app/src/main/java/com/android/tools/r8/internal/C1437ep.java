package com.android.tools.r8.internal;

import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.Reference;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ep, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1437ep extends AbstractC1523fp {
    public final FieldReference a;

    public C1437ep(FieldReference fieldReference) {
        this.a = fieldReference;
    }

    @Override // com.android.tools.r8.internal.AbstractC1523fp
    public final AbstractC1523fp a(ClassReference classReference) {
        return new C1437ep(Reference.field(classReference, this.a.getFieldName(), this.a.getFieldType()));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1437ep.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((C1437ep) obj).a);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1767ii
    public final ClassReference getHolderClass() {
        return this.a.getHolderClass();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1767ii
    public final String getName() {
        return this.a.getFieldName();
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC1523fp
    public final C1437ep a() {
        return this;
    }
}
