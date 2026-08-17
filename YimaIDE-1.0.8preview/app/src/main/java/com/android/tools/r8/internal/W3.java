package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class W3 implements Ua0 {
    public final /* synthetic */ Object[] a;

    public W3(Object[] objArr) {
        this.a = objArr;
    }

    @Override // com.android.tools.r8.internal.Ua0
    public final Iterator iterator() {
        Object[] objArr = this.a;
        KB.c(objArr, "array");
        return new I3(objArr);
    }
}
