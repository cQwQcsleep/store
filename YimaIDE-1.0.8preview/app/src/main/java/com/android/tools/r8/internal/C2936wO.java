package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0343z2;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2936wO {
    public static final /* synthetic */ boolean d = true;
    public final com.android.tools.r8.graph.B1 a;
    public final C0723Ol b;
    public final R5 c;

    public C2936wO(com.android.tools.r8.graph.B1 b1, C0723Ol c0723Ol, C0860Ts c0860Ts) {
        this.a = b1;
        this.b = c0723Ol;
        this.c = c0860Ts;
    }

    public final C0322w2 a(C0322w2 c0322w2, com.android.tools.r8.graph.E2 e2, BiConsumer biConsumer) {
        C0723Ol c0723Ol = this.b;
        com.android.tools.r8.graph.B2 b2 = (com.android.tools.r8.graph.B2) c0723Ol.b.get(AbstractC0507Gc.a(c0322w2, c0322w2));
        if (b2 != null) {
            if (d || b2.b() == e2) {
                return this.a.a(c0322w2.w0().z(), b2.b(), b2.a());
            }
            x1f.a();
            return null;
        }
        C0322w2 c0322w2A = this.a.a(c0322w2.f, e2, c0322w2.g);
        if (a(c0322w2A)) {
            c0322w2A = this.a.a(c0322w2A.x0().toString(), c0322w2A.C0(), c0322w2A.w0(), new Predicate() { // from class: eoi
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.c((C0322w2) obj);
                }
            });
        }
        if (d || !a(c0322w2A)) {
            biConsumer.accept(c0322w2, c0322w2A);
            return c0322w2A;
        }
        x1f.a();
        return null;
    }

    public final C0322w2 b(C0322w2 c0322w2, com.android.tools.r8.graph.E2 e2) {
        return a(c0322w2, e2, new BiConsumer() { // from class: doi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((C0322w2) obj, (C0322w2) obj2);
            }
        });
    }

    public final /* synthetic */ boolean c(C0322w2 c0322w2) {
        return !a(c0322w2);
    }

    public final /* synthetic */ boolean b(C0322w2 c0322w2) {
        return !this.c.containsValue(c0322w2);
    }

    public final C0322w2 a(C0322w2 c0322w2, com.android.tools.r8.graph.E2 e2, com.android.tools.r8.graph.I2 i2) {
        boolean z = d;
        if (!z) {
            C0723Ol c0723Ol = this.b;
            if (c0723Ol.b.containsKey(AbstractC0507Gc.a(c0322w2, c0322w2))) {
                x1f.a();
                return null;
            }
        }
        C0322w2 c0322w3 = (C0322w2) this.c.get(c0322w2);
        if (c0322w3 != null) {
            if (z || c0322w3.C0() == e2) {
                return c0322w3.a(c0322w2.w0(), this.a);
            }
            x1f.a();
            return null;
        }
        C0322w2 c0322w2A = this.a.a(c0322w2.f, e2, c0322w2.g);
        if (this.c.containsValue(c0322w2A)) {
            c0322w2A = this.a.a(c0322w2A, i2, new Predicate() { // from class: foi
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.b((C0322w2) obj);
                }
            });
        }
        if (z || !this.c.containsValue(c0322w2A)) {
            this.c.put(c0322w2, c0322w2A);
            return c0322w2A;
        }
        x1f.a();
        return null;
    }

    public final C0322w2 a(C0322w2 c0322w2, com.android.tools.r8.graph.E2 e2) {
        final R5 r5 = this.c;
        Objects.requireNonNull(r5);
        return a(c0322w2, e2, new BiConsumer() { // from class: coi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                r5.put((C0322w2) obj, (C0322w2) obj2);
            }
        });
    }

    public final void a(C0322w2 c0322w2, C0322w2 c0322w3) {
        C0723Ol c0723Ol = this.b;
        c0322w2.getClass();
        C0343z2 c0343z2 = new C0343z2(c0322w2);
        c0322w3.getClass();
        c0723Ol.b.put(c0343z2, new C0343z2(c0322w3));
    }

    public final boolean a(C0322w2 c0322w2) {
        if (this.c.containsValue(c0322w2)) {
            return true;
        }
        return this.b.b.containsValue(AbstractC0507Gc.a(c0322w2, c0322w2));
    }

    public final C0322w2 a(C0231j1 c0231j1, com.android.tools.r8.graph.E2 e2, com.android.tools.r8.graph.I2 i2) {
        C0322w2 reference = c0231j1.getReference();
        if (c0231j1.m1()) {
            if (d || reference.C0() == e2) {
                return reference;
            }
            x1f.a();
            return null;
        }
        if (c0231j1.q1()) {
            if (d || i2 != null) {
                return a(reference, e2, i2);
            }
            x1f.a();
            return null;
        }
        if (c0231j1.u1()) {
            return b(reference, e2);
        }
        return a(reference, e2);
    }
}
