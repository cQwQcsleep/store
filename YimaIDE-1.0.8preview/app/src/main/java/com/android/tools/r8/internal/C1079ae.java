package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1079ae;
import java.util.Spliterator;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ae, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1079ae extends AbstractC1001Zd {
    public C1079ae(Spliterator spliterator, Spliterator spliterator2, Function function, int i, long j) {
        super(spliterator, spliterator2, function, new InterfaceC0975Yd() { // from class: ucg
            @Override // com.android.tools.r8.internal.InterfaceC0975Yd
            public final Spliterator a(Spliterator spliterator3, Spliterator spliterator4, Function function2, int i2, long j2) {
                return new C1079ae(spliterator3, spliterator4, function2, i2, j2);
            }
        }, i, j);
    }
}
