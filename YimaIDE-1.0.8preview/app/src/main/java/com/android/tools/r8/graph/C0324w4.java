package com.android.tools.r8.graph;

import com.android.tools.r8.internal.C1819jJ;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.w4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0324w4 implements C4 {
    public static final /* synthetic */ boolean c = true;
    public final H0 b;

    public C0324w4(C1819jJ c1819jJ, H0 h0) {
        if (c || c1819jJ != null) {
            this.b = h0;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.graph.C4
    public final void a(Consumer consumer, Consumer consumer2) {
        consumer2.accept(this);
    }

    @Override // com.android.tools.r8.graph.C4
    public final boolean n() {
        return true;
    }

    @Override // com.android.tools.r8.graph.C4
    public final C0324w4 o() {
        return this;
    }
}
