package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0228i5;
import com.android.tools.r8.utils.structural.A;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.graph.i5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0228i5 implements com.android.tools.r8.utils.structural.x<C0228i5> {
    public static final /* synthetic */ boolean c = true;
    public final I2 b;

    public C0228i5(I2 i2) {
        this.b = i2;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: a8h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0228i5) obj).b;
            }
        });
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: b8h
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0228i5.a(a);
            }
        };
    }

    public I2 a() {
        return this.b;
    }
}
