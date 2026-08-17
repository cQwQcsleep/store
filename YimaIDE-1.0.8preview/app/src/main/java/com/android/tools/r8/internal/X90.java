package com.android.tools.r8.internal;

import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.TypeReference;
import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedFieldReference;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X90 extends Z90 implements RetracedFieldReference.KnownRetracedFieldReference {
    public final FieldReference b;

    public X90(FieldReference fieldReference) {
        this.b = fieldReference;
    }

    @Override // com.android.tools.r8.retrace.RetracedFieldReference
    public final RetracedFieldReference.KnownRetracedFieldReference asKnown() {
        return this;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || X90.class != obj.getClass()) {
            return false;
        }
        return this.b.equals(((X90) obj).b);
    }

    @Override // com.android.tools.r8.retrace.RetracedFieldReference
    public final String getFieldName() {
        return this.b.getFieldName();
    }

    @Override // com.android.tools.r8.retrace.RetracedFieldReference.KnownRetracedFieldReference
    public final FieldReference getFieldReference() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetracedFieldReference.KnownRetracedFieldReference
    public final TypeReference getFieldType() {
        return this.b.getFieldType();
    }

    @Override // com.android.tools.r8.retrace.RetracedClassMemberReference
    public final RetracedClassReference getHolderClass() {
        return new W90(true, this.b.getHolderClass());
    }

    public final int hashCode() {
        return Objects.hash(this.b);
    }
}
