package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0297s5;
import com.android.tools.r8.internal.UK;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.iai;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.graph.s5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0297s5 implements UK, com.android.tools.r8.utils.structural.x {
    public final C0245l1 b;

    public C0297s5(C0245l1 c0245l1) {
        this.b = c0245l1;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: kai
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0297s5) obj).b;
            }
        }).j(new Function() { // from class: mai
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0297s5.b((C0297s5) obj);
            }
        });
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.internal.UK
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        iai iaiVar = new iai();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        iaiVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    public final boolean equals(Object obj) {
        return com.android.tools.r8.utils.structural.k.a(this, obj);
    }

    public final int hashCode() {
        return Objects.hash(this.b, null);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new iai();
    }

    public final String toString() {
        return this.b.toString();
    }

    @Override // com.android.tools.r8.internal.UK
    public final int y() {
        return 13;
    }

    public static /* synthetic */ C0297s5 b(C0297s5 c0297s5) {
        c0297s5.getClass();
        return null;
    }

    public final void a(Consumer consumer) {
        consumer.accept(this.b);
    }

    @Override // com.android.tools.r8.internal.UK
    public final int a(UK uk, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (C0297s5) uk, new iai());
    }
}
