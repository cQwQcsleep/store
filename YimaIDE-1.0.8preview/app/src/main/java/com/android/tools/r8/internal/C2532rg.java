package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0336y2;
import com.android.tools.r8.internal.C2532rg;
import com.android.tools.r8.utils.structural.A;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.ToIntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2532rg implements com.android.tools.r8.utils.structural.x {
    public static final /* synthetic */ boolean g = true;
    public final int b;
    public final com.android.tools.r8.graph.H2 c;
    public final com.android.tools.r8.graph.I2 d;
    public final C0336y2 e;
    public final List f;

    public C2532rg(int i, com.android.tools.r8.graph.H2 h2, com.android.tools.r8.graph.I2 i2, C0336y2 c0336y2, ArrayList arrayList) {
        if (!g && !arrayList.isEmpty()) {
            x1f.a();
            throw null;
        }
        this.b = i;
        this.c = h2;
        this.d = i2;
        this.e = c0336y2;
        this.f = arrayList;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: b8i
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((C2532rg) obj).b;
            }
        });
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final boolean equals(Object obj) {
        return com.android.tools.r8.utils.structural.k.a(this, obj);
    }

    public final int hashCode() {
        return Objects.hash(this.c, this.d, this.e, this.f);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: a8i
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C2532rg.a(a);
            }
        };
    }
}
