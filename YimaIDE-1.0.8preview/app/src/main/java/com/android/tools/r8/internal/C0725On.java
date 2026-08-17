package com.android.tools.r8.internal;

import defpackage.flg;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.On, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0725On {
    public static final /* synthetic */ boolean d = true;
    public final ConcurrentHashMap a = new ConcurrentHashMap();
    public final IdentityHashMap b = new IdentityHashMap();
    public final Set c = C1755ib0.a();

    public final boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        boolean z = d;
        if (!z && i3 == null) {
            x1f.a();
            return false;
        }
        if (z || i2 != null) {
            return i3 == i2 || i3 == this.b.get(i2);
        }
        x1f.a();
        return false;
    }

    public final Set b(com.android.tools.r8.graph.I2 i2) {
        C0699Nn c0699Nn = (C0699Nn) this.a.get(i2);
        if (C0699Nn.e || c0699Nn.d != null) {
            return C1755ib0.a(c0699Nn.d, new flg());
        }
        x1f.a();
        return null;
    }

    public final boolean c(com.android.tools.r8.graph.I2 i2) {
        return this.a.containsKey(this.b.getOrDefault(i2, i2));
    }

    public final void d(com.android.tools.r8.graph.I2 i2) {
        this.a.remove(this.b.getOrDefault(i2, i2));
    }

    public final AbstractC2554rv a() {
        return AbstractC2554rv.a(this.a.keySet());
    }

    public final com.android.tools.r8.graph.D2 a(com.android.tools.r8.graph.I2 i2) {
        C0699Nn c0699Nn = (C0699Nn) this.a.get((com.android.tools.r8.graph.I2) this.b.getOrDefault(i2, i2));
        if (c0699Nn == null) {
            return null;
        }
        return c0699Nn.a;
    }

    public final void a(Consumer consumer) {
        this.a.values().forEach(consumer);
    }
}
