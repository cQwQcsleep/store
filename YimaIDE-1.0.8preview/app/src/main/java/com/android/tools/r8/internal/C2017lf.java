package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.C0343z2;
import com.android.tools.r8.internal.C0491Fm;
import com.android.tools.r8.internal.InterfaceC1846jf;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2017lf extends AbstractC1590gf implements InterfaceC2102mf {
    public static final /* synthetic */ boolean c = true;
    public final Map b;

    public C2017lf(HashMap map) {
        this.b = map;
        boolean z = c;
        if (!z && map.isEmpty()) {
            x1f.a();
            throw null;
        }
        if (z || !a(AbstractC0439Dm.m()).isUnknown()) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final RO a(C0333y c0333y, Function function, C0343z2 c0343z2, C1418ee0 c1418ee0) {
        boolean z = c;
        if (!z && this.b.isEmpty()) {
            x1f.a();
            return null;
        }
        if (!z && a(AbstractC0439Dm.m()).isUnknown()) {
            x1f.a();
            return null;
        }
        HashMap map = new HashMap();
        for (Map.Entry entry : this.b.entrySet()) {
            C0491Fm c0491Fm = (C0491Fm) function.apply((C0491Fm) entry.getKey());
            if (c0491Fm != null) {
                Object objA = a(c0333y, c0343z2, (InterfaceC1846jf) map.get(c0491Fm), (InterfaceC1846jf) entry.getValue(), c1418ee0);
                if (c0491Fm.l()) {
                    RO ro = (RO) objA;
                    ro.getClass();
                    if (ro instanceof C2969wk0) {
                        return C2969wk0.a;
                    }
                }
                map.put(c0491Fm, objA);
            }
        }
        return map.isEmpty() ? C1209c7.a : new C2017lf(map);
    }

    @Override // com.android.tools.r8.internal.RO, com.android.tools.r8.internal.QO
    public final InterfaceC2102mf i() {
        return this;
    }

    @Override // com.android.tools.r8.internal.RO, com.android.tools.r8.internal.QO
    public final C2017lf j() {
        return this;
    }

    public C2017lf(C0491Fm c0491Fm, InterfaceC1846jf interfaceC1846jf) {
        HashMap map = new HashMap(1);
        this.b = map;
        map.put(c0491Fm, interfaceC1846jf);
        if (c || !a(AbstractC0439Dm.m()).isUnknown()) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final void a(BiConsumer biConsumer) {
        this.b.forEach(biConsumer);
    }

    public final QO a(C0491Fm c0491Fm) {
        InterfaceC1846jf interfaceC1846jf = (InterfaceC1846jf) this.b.get(c0491Fm);
        return interfaceC1846jf != null ? interfaceC1846jf : C1209c7.a;
    }

    @Override // com.android.tools.r8.internal.QO
    public final QO a() {
        boolean z = c;
        if (!z && this.b.isEmpty()) {
            x1f.a();
            return null;
        }
        if (!z && a(AbstractC0439Dm.m()).isUnknown()) {
            x1f.a();
            return null;
        }
        final HashMap map = new HashMap();
        this.b.forEach(new BiConsumer() { // from class: bkh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                map.put((C0491Fm) obj, ((InterfaceC1846jf) obj2).a());
            }
        });
        return new C2017lf(map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static InterfaceC1846jf a(C0333y c0333y, com.android.tools.r8.graph.B2 b2, InterfaceC1846jf interfaceC1846jf, InterfaceC1846jf interfaceC1846jf2, AbstractC1589ge0 abstractC1589ge0) {
        if (interfaceC1846jf == 0) {
            return (InterfaceC1846jf) abstractC1589ge0.a(interfaceC1846jf2);
        }
        RO ro = (RO) interfaceC1846jf;
        if (!(ro instanceof C2969wk0)) {
            RO ro2 = (RO) interfaceC1846jf2;
            ro2.getClass();
            if (!(ro2 instanceof C2969wk0)) {
                if (!c && !(ro instanceof C1676hf)) {
                    x1f.a();
                    return null;
                }
                return interfaceC1846jf.b().a(c0333y, b2, interfaceC1846jf2.b(), abstractC1589ge0);
            }
        }
        return C2969wk0.a;
    }
}
