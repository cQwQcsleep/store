package com.android.tools.r8.graph;

import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.M4;
import com.android.tools.r8.graph.N4;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.C1755ib0;
import com.android.tools.r8.internal.IM;
import defpackage.waa;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class N4 {
    public static final /* synthetic */ boolean e = true;
    public final Function a;
    public final boolean b;
    public final IdentityHashMap c = new IdentityHashMap();
    public final Set d = AbstractC2780ub0.c();

    public N4(Function function, boolean z) {
        this.a = function;
        this.b = z;
    }

    public final /* synthetic */ void a(E0 e0, InterfaceC0174b0 interfaceC0174b0, final Set set, I2 i2, final E0 e1) {
        if (this.b && e0.b0() && !e1.b0()) {
            return;
        }
        if (interfaceC0174b0.q()) {
            set = C1755ib0.a((Iterable) set);
            set.add(new M4(i2));
        }
        final Map map = (Map) this.c.computeIfAbsent(e1, IM.a(new waa()));
        set.forEach(new Consumer() { // from class: xaa
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                N4.a(map, (M4) obj);
            }
        });
        e1.h.forEach(new Consumer() { // from class: yaa
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(e1, set, (I2) obj);
            }
        });
        I2 i3 = e1.g;
        if (i3 != null) {
            a(e1, set, i3);
        }
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final void a(final E0 e0, final Set set, final I2 i2) {
        if (!set.isEmpty() || this.d.add(i2)) {
            final InterfaceC0174b0 interfaceC0174b0 = (InterfaceC0174b0) this.a.apply(i2);
            interfaceC0174b0.b(new Consumer() { // from class: vaa
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(e0, interfaceC0174b0, set, i2, (E0) obj);
                }
            });
        }
    }

    public void a(I2 i2, E0 e0) {
        a(e0, Collections.EMPTY_SET, i2);
    }

    public boolean a(E0 e0) {
        Map map = (Map) this.c.get(e0);
        if (!e && map == null) {
            x1f.a();
            return false;
        }
        for (M4 m4 : map.values()) {
            m4.getClass();
            if (m4 != M4.b) {
                return true;
            }
        }
        return false;
    }

    public static void a(Map map, M4 m4) {
        M4 m5;
        M4 m6 = (M4) map.get(m4.a);
        if (m6 == null) {
            map.put(m4.a, m4);
        } else {
            if (m6 == m4 || m6 == (m5 = M4.b)) {
                return;
            }
            map.put(m4.a, m5);
        }
    }
}
