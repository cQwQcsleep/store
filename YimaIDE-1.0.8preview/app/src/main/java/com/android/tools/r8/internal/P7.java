package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class P7 extends U7 {
    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new L7(this);
    }
}
