package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I5 {
    public static final /* synthetic */ boolean h = true;
    public final AbstractC0327x0 a;
    public final Set b;
    public final Map c;
    public final Set d;
    public final Set e;
    public final Set f;
    public final Set g;

    public I5(AbstractC0327x0 abstractC0327x0, Set set, Map map, Set set2, Set set3, Set set4, Set set5) {
        this.a = abstractC0327x0;
        this.b = set;
        this.c = map;
        this.d = set2;
        this.e = set3;
        this.f = set4;
        this.g = set5;
    }

    public final void a(C0322w2 c0322w2, Consumer consumer) {
        if (h || this.c.containsKey(c0322w2)) {
            consumer.accept((B5) this.c.get(c0322w2));
        } else {
            x1f.a();
        }
    }

    public final Set b() {
        return this.e;
    }

    public final boolean c() {
        return !this.e.isEmpty();
    }

    public final boolean d() {
        return this.b.isEmpty() && this.c.isEmpty() && this.d.isEmpty() && this.e.isEmpty() && this.f.isEmpty() && this.g.isEmpty();
    }

    public static G5 a() {
        return new G5();
    }

    public final boolean a(C0245l1 c0245l1) {
        return this.f.contains(c0245l1) || this.e.contains(c0245l1.w0());
    }

    public final boolean a(C0322w2 c0322w2) {
        return this.g.contains(c0322w2) || this.e.contains(c0322w2.w0());
    }

    public final boolean a(F2 f2) {
        return ((Boolean) f2.a(new Function() { // from class: se6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(this.b.a((I2) obj));
            }
        }, new Function() { // from class: ue6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(this.b.a((C0245l1) obj));
            }
        }, new Function() { // from class: we6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(this.b.a((C0322w2) obj));
            }
        })).booleanValue();
    }

    public final boolean a(I2 i2) {
        return this.e.contains(i2);
    }
}
