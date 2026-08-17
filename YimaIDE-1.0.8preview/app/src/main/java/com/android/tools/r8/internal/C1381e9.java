package com.android.tools.r8.internal;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC3175z9;
import com.android.tools.r8.internal.C1381e9;
import com.android.tools.r8.internal.H8;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.e9, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1381e9 implements InterfaceC1004Zg {
    public static final /* synthetic */ boolean c = true;
    public final Map a;
    public final com.android.tools.r8.graph.G b;

    public C1381e9(Map map, com.android.tools.r8.graph.G g) {
        this.a = map;
        this.b = g;
    }

    public static AbstractC1597gi0 a(H8 h8, final InterfaceC1938ki0 interfaceC1938ki0, Object obj) {
        LinkedHashMap linkedHashMap = h8.f;
        InterfaceC1938ki0 interfaceC1938ki1 = new InterfaceC1938ki0() { // from class: atg
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj2, Object obj3, Object obj4) {
                return C1381e9.a(interfaceC1938ki0, (I2) obj2, (H8) obj3, obj4);
            }
        };
        AbstractC1597gi0 c1512fi0 = new C1512fi0(obj);
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            c1512fi0 = (AbstractC1597gi0) interfaceC1938ki1.a(entry.getKey(), entry.getValue(), c1512fi0.b().f());
            if (c1512fi0.c()) {
                break;
            }
        }
        return c1512fi0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    public final AbstractC1597gi0 b(Object obj, Object obj2, BiFunction biFunction) {
        return AbstractC1683hi0.a(((H8) obj).e, biFunction, obj2);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    public final AbstractC1597gi0 a(Object obj, BiFunction biFunction, AbstractC3159z1 abstractC3159z1) {
        H8 h8 = (H8) obj;
        AbstractC1597gi0 c1512fi0 = new C1512fi0(abstractC3159z1);
        for (int i = h8.a; i <= h8.c; i++) {
            c1512fi0 = (AbstractC1597gi0) biFunction.apply((AbstractC3175z9) this.b.h.get(i), c1512fi0.b().e());
            if (c1512fi0.c()) {
                return c1512fi0;
            }
        }
        return c1512fi0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    public final AbstractC1597gi0 a(Object obj, Object obj2, BiFunction biFunction) {
        return AbstractC1683hi0.a(((H8) obj).d, biFunction, obj2);
    }

    public final AbstractC1597gi0 a(BiFunction biFunction, AbstractC3175z9 abstractC3175z9, Object obj) {
        if (c || this.a.containsKey(abstractC3175z9)) {
            return (AbstractC1597gi0) biFunction.apply((H8) this.a.get(abstractC3175z9), obj);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    public final Object a() {
        AbstractC3175z9 abstractC3175z9 = (AbstractC3175z9) this.b.h.get(0);
        if (c || this.a.containsKey(abstractC3175z9)) {
            return (H8) this.a.get(abstractC3175z9);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    public final /* bridge */ /* synthetic */ AbstractC1597gi0 a(Object obj, InterfaceC1938ki0 interfaceC1938ki0, Object obj2) {
        return a((H8) obj, interfaceC1938ki0, obj2);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final AbstractC1597gi0 d(H8 h8, final BiFunction biFunction) {
        com.android.tools.r8.graph.G g = this.b;
        h8.getClass();
        AbstractC3175z9 abstractC3175z9 = g.H0().get(h8.c);
        com.android.tools.r8.graph.G g2 = this.b;
        int i = h8.c + 1;
        return abstractC3175z9.a(new BiFunction() { // from class: btg
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return this.b.a(biFunction, (AbstractC3175z9) obj, obj2);
            }
        }, i < g2.H0().size() ? g2.H0().get(i) : null, (AbstractC1808j9) null);
    }

    public static /* synthetic */ AbstractC1597gi0 a(InterfaceC1938ki0 interfaceC1938ki0, com.android.tools.r8.graph.I2 i2, H8 h8, Object obj) {
        return (AbstractC1597gi0) interfaceC1938ki0.a(h8, i2, obj);
    }
}
