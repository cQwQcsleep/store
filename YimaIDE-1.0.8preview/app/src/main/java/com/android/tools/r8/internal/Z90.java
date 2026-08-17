package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetracedFieldReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Z90 implements RetracedFieldReference {
    @Override // com.android.tools.r8.retrace.RetracedFieldReference
    public final boolean isKnown() {
        return this instanceof X90;
    }

    @Override // com.android.tools.r8.retrace.RetracedFieldReference
    public boolean isUnknown() {
        return !(this instanceof X90);
    }
}
