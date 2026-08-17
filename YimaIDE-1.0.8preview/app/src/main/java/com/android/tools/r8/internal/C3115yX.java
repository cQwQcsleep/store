package com.android.tools.r8.internal;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C3115yX;
import com.android.tools.r8.internal.InterfaceC1517fl;
import com.android.tools.r8.shaking.C3403i;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3115yX {
    public static final /* synthetic */ boolean b = true;
    public final C1907kM a;

    public C3115yX(AbstractC3148ys abstractC3148ys) {
        this.a = C1907kM.a(abstractC3148ys);
    }

    public final C3200zX a(final C0333y c0333y, KO ko, ExecutorService executorService, Ch0 ch0) {
        Set set = ((C3403i) c0333y.g()).z;
        if (!set.isEmpty()) {
            final UY uyC = UY.c();
            set.forEach(new Consumer() { // from class: bvi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    C3115yX.a(c0333y, uyC, (C0322w2) obj);
                }
            });
            a(uyC);
        }
        if (this.a.d.isEmpty()) {
            return null;
        }
        UY uyA = this.a.a(c0333y);
        if (b || !c0333y.M().Z0 || uyA.stream().allMatch(new Predicate() { // from class: cvi
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((B5) obj).e().I0();
            }
        })) {
            return new C3200zX(c0333y, new C3199zW(c0333y, uyA).a(executorService, ch0), ko);
        }
        x1f.a();
        return null;
    }

    public final void a(AbstractC3148ys abstractC3148ys, com.android.tools.r8.graph.B5 b5) {
        this.a.a(abstractC3148ys, b5);
    }

    public final void a(Collection collection, final AbstractC3148ys abstractC3148ys) {
        collection.forEach(new Consumer() { // from class: yui
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(abstractC3148ys, (B5) obj);
            }
        });
    }

    public final void a(UY uy) {
        uy.forEach(new Consumer() { // from class: zui
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((B5) obj);
            }
        });
    }

    public final C3115yX a(C0333y c0333y) {
        C1907kM c1907kM = this.a;
        c1907kM.getClass();
        c1907kM.c(c0333y.A());
        return this;
    }

    public final void a(com.android.tools.r8.graph.B5 b5) {
        this.a.d.add(b5.getReference());
    }

    public static void a(C0333y c0333y, UY uy, C0322w2 c0322w2) {
        c0333y.getClass();
        com.android.tools.r8.graph.D2 d2B = com.android.tools.r8.graph.D2.b(c0333y.d(c0322w2.f));
        C0231j1 c0231j1C = c0322w2.c((com.android.tools.r8.graph.E0) d2B);
        if (c0231j1C != null) {
            uy.getClass();
            uy.add(new com.android.tools.r8.graph.B5(d2B, c0231j1C));
        }
    }

    public final void a(C1858jl c1858jl) throws IOException {
        final C1907kM c1907kM = this.a;
        Objects.requireNonNull(c1907kM);
        InterfaceC1936kh0 interfaceC1936kh0 = new InterfaceC1936kh0() { // from class: avi
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                c1907kM.a((InterfaceC1517fl) obj);
            }
        };
        InterfaceC1517fl interfaceC1517flA = ((C1431el) c1858jl.a).a();
        try {
            interfaceC1936kh0.accept(interfaceC1517flA);
            interfaceC1517flA.close();
        } catch (Throwable th) {
            try {
                interfaceC1517flA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
