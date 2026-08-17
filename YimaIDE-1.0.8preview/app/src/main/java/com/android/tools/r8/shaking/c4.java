package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.I5;
import com.android.tools.r8.graph.InterfaceC0332x5;
import com.android.tools.r8.graph.S5;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2775uY;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.FX;
import com.android.tools.r8.internal.PY;
import com.android.tools.r8.internal.SJ;
import com.android.tools.r8.shaking.c4;
import defpackage.ejg;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class c4 extends e4 {
    public static final /* synthetic */ boolean o = true;

    public c4(C c, AbstractC0551Hu abstractC0551Hu, Set set, List list) {
        Set set2 = Collections.EMPTY_SET;
        FX fx = new FX();
        Map map = Collections.EMPTY_MAP;
        super(c, abstractC0551Hu, set2, set2, set2, set2, fx, map, map, set2, set, list, PY.c);
    }

    public final c4 a(I5 i5, Ch0 ch0) {
        if (i5.d()) {
            return this;
        }
        ch0.a("Prune MainDexRootSet");
        this.m.forEach(new ejg());
        if (!o && !this.c.isEmpty()) {
            x1f.a();
            return null;
        }
        c4 c4Var = new c4(this.a, this.e, this.m, this.c);
        ch0.b();
        return c4Var;
    }

    @Override // com.android.tools.r8.shaking.e4
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final c4 a(final AbstractC3148ys abstractC3148ys, Ch0 ch0) {
        ch0.a("Rewrite MainDexRootSet");
        if (!abstractC3148ys.l()) {
            final C0473Eu c0473EuG = AbstractC0551Hu.g();
            this.e.forEach(new Consumer() { // from class: gjg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    c4.a(abstractC3148ys, c0473EuG, (F2) obj);
                }
            });
            this.m.forEach(new ejg());
            if (!o && !this.c.isEmpty()) {
                x1f.a();
                return null;
            }
            this = new c4(this.a.a(abstractC3148ys, ch0), c0473EuG.a(), this.m, this.c);
        }
        ch0.b();
        return this;
    }

    public static /* synthetic */ void a(AbstractC3148ys abstractC3148ys, final C0473Eu c0473Eu, com.android.tools.r8.graph.F2 f2) {
        Objects.requireNonNull(c0473Eu);
        SJ.a(abstractC3148ys, f2, new Consumer() { // from class: ijg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c0473Eu.a((F2) obj);
            }
        });
    }

    @Override // com.android.tools.r8.shaking.e4
    public final void a(InterfaceC0332x5 interfaceC0332x5) {
    }

    public static d4 b(C0333y c0333y, AbstractC2775uY abstractC2775uY, S5 s5, Iterable iterable) {
        return new d4(c0333y, abstractC2775uY, s5, iterable);
    }
}
