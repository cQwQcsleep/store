package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.MissingFieldInfo;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.references.FieldReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class o extends j implements MissingFieldInfo {
    public final FieldReference b;

    public o(FieldReference fieldReference, AbstractC0551Hu abstractC0551Hu) {
        super(abstractC0551Hu);
        this.b = fieldReference;
    }

    @Override // com.android.tools.r8.diagnostic.MissingFieldInfo
    public final FieldReference getFieldReference() {
        return this.b;
    }
}
