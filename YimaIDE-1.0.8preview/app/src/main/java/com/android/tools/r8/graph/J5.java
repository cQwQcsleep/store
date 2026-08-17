package com.android.tools.r8.graph;

import com.android.tools.r8.graph.J5;
import com.android.tools.r8.utils.structural.A;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class J5 implements com.android.tools.r8.utils.structural.x<J5> {
    public static final /* synthetic */ boolean e = true;
    public final C0245l1 b;
    public final B3.e c;
    public final List d;

    public J5(C0245l1 c0245l1, B3.e eVar, List list) {
        boolean z = e;
        if (!z && c0245l1 == null) {
            x1f.a();
            throw null;
        }
        if (!z && eVar == null) {
            x1f.a();
            throw null;
        }
        if (!z && list == null) {
            x1f.a();
            throw null;
        }
        this.b = c0245l1;
        this.c = eVar;
        this.d = list;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: c97
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((J5) obj).b();
            }
        }).e(new Function() { // from class: d97
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((J5) obj).d();
            }
        });
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public H2 b() {
        return this.b.x0();
    }

    public B3.e c() {
        return this.c;
    }

    public I2 d() {
        return this.b.getType();
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: e97
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                J5.a(a);
            }
        };
    }

    public List<C0285r0> a() {
        return this.d;
    }
}
