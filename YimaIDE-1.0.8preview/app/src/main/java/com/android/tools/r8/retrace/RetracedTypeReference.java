package com.android.tools.r8.retrace;

import com.android.tools.r8.references.TypeReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetracedTypeReference {
    String getTypeName();

    TypeReference getTypeReference();

    boolean isVoid();

    TypeReference toArray(int i);
}
