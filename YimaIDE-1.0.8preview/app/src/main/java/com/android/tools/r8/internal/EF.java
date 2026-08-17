package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC3114yW;
import com.android.tools.r8.internal.BE;
import com.android.tools.r8.internal.EF;
import com.android.tools.r8.internal.I2;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EF extends AbstractC0927Wh {
    public static final /* synthetic */ boolean c = true;
    public final C2960wg a;
    public final M3 b;

    public EF(AbstractC3114yW abstractC3114yW) {
        C2960wg c2960wg = new C2960wg(abstractC3114yW);
        this.a = c2960wg;
        c2960wg.a("constraints", EnumC2874vg.b);
        c2960wg.a("constraintAdditions", EnumC2874vg.c);
        M3 m3 = new M3(abstractC3114yW, new Function() { // from class: v34
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new I2((AbstractC3114yW) obj);
            }
        });
        this.b = m3;
        m3.a("constrainAnnotations", F2.b);
        m3.e = new BiConsumer() { // from class: w34
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((List) obj, (AbstractC3114yW) obj2);
            }
        };
    }

    public static void a(C1815jF c1815jF, BE be) {
        TE te;
        be.getClass();
        if (be == BE.c) {
            te = TE.b;
        } else if (be == BE.d) {
            te = TE.c;
        } else {
            te = be == BE.e ? TE.d : new TE(be);
        }
        c1815jF.b.add(te);
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh
    public final AbstractC0551Hu b() {
        return AbstractC0551Hu.a(this.a, this.b);
    }

    public final AbstractC2072mF c() {
        final C1815jF c1815jFA;
        if (!a()) {
            return null;
        }
        if (!this.b.a()) {
            if (c || this.a.a()) {
                return (AbstractC2072mF) this.a.getValue();
            }
            x1f.a();
            return null;
        }
        if (this.a.a()) {
            c1815jFA = new C1815jF().a((AbstractC2072mF) this.a.getValue());
            if (!c) {
                c1815jFA.a();
            }
        } else {
            c1815jFA = new C1815jF().a(C1986lF.b);
        }
        ((List) this.b.getValue()).forEach(new Consumer() { // from class: u34
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                EF.a(c1815jFA, (BE) obj);
            }
        });
        C1900kF c1900kF = new C1900kF(c1815jFA.b);
        return c1815jFA.a ? new C1731iF(c1900kF) : c1900kF;
    }

    public final AbstractC2072mF d() {
        return a() ? c() : C1986lF.b;
    }

    public final void a(List list, AbstractC3114yW abstractC3114yW) {
        if (list.isEmpty()) {
            abstractC3114yW.getClass();
            throw new C3096yE(abstractC3114yW, "Expected non-empty array of annotation patterns");
        }
    }
}
