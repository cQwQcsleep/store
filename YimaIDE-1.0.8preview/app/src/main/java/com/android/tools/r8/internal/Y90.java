package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedFieldReference;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Y90 extends Z90 {
    public final AbstractC1523fp b;

    public Y90(AbstractC1523fp abstractC1523fp) {
        this.b = abstractC1523fp;
    }

    @Override // com.android.tools.r8.retrace.RetracedFieldReference
    public final /* bridge */ /* synthetic */ RetracedFieldReference.KnownRetracedFieldReference asKnown() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Y90.class != obj.getClass()) {
            return false;
        }
        return this.b.equals(((Y90) obj).b);
    }

    @Override // com.android.tools.r8.retrace.RetracedFieldReference
    public final String getFieldName() {
        return this.b.getName();
    }

    @Override // com.android.tools.r8.retrace.RetracedClassMemberReference
    public final RetracedClassReference getHolderClass() {
        return new W90(false, this.b.getHolderClass());
    }

    public final int hashCode() {
        return Objects.hash(this.b);
    }
}
