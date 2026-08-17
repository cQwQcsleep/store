package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.Kk0;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.graph.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0180c extends AbstractC0187d {
    public static final C0180c a = new C0180c();

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final void a(Consumer consumer) {
        throw new Kk0("Should never be iterating the access contexts when they are unknown");
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final int b() {
        throw new Kk0("Should never be querying the number of access contexts when they are unknown");
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final boolean c() {
        return false;
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final AbstractC0187d a(InterfaceC0189d1 interfaceC0189d1, AbstractC3148ys abstractC3148ys) {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final boolean b(Predicate predicate) {
        return false;
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final AbstractC0187d a(AbstractC0187d abstractC0187d) {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final void a(C0245l1 c0245l1) {
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final boolean a(C0231j1 c0231j1) {
        return true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final boolean a(Predicate predicate) {
        return true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final AbstractC0187d a(I5 i5) {
        return this;
    }
}
