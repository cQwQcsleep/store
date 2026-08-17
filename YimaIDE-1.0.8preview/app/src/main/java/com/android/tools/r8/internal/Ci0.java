package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ci0 extends AbstractC3220zi0 {
    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
        c2754uD.d();
        int length = atomicIntegerArray.length();
        for (int i = 0; i < length; i++) {
            c2754uD.a(atomicIntegerArray.get(i));
        }
        c2754uD.f();
    }
}
