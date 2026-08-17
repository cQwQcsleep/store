package com.android.tools.r8.retrace;

import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.TypeReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetracedFieldReference extends RetracedClassMemberReference {

    public interface KnownRetracedFieldReference extends RetracedFieldReference {
        FieldReference getFieldReference();

        TypeReference getFieldType();

        @Override // com.android.tools.r8.retrace.RetracedFieldReference
        /* synthetic */ boolean isKnown();

        @Override // com.android.tools.r8.retrace.RetracedFieldReference
        /* synthetic */ boolean isUnknown();
    }

    KnownRetracedFieldReference asKnown();

    String getFieldName();

    boolean isKnown();

    boolean isUnknown();
}
