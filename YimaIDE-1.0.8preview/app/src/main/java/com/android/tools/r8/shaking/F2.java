package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.C2685tU;
import com.android.tools.r8.internal.ZS;
import com.android.tools.r8.shaking.F2;
import com.android.tools.r8.shaking.K3;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class F2 {
    public static Iterable a(F2 f2) {
        return f2 == null ? new defpackage.s() : f2.c();
    }

    public static a b() {
        return new a();
    }

    public abstract List<com.android.tools.r8.graph.I2> a();

    public abstract void a(StringBuilder sb);

    public abstract void a(Consumer consumer);

    public abstract boolean a(com.android.tools.r8.graph.I2 i2);

    public Iterable<S3> c() {
        return new defpackage.s();
    }

    public final boolean d() {
        return c().iterator().hasNext();
    }

    public abstract int e();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        a(sb);
        return sb.toString();
    }

    public static F2 a(K3 k3) {
        return new b(k3);
    }

    public static class b extends F2 {
        final K3 a;

        public b(K3 k3) {
            this.a = k3;
        }

        @Override // com.android.tools.r8.shaking.F2
        public final List a() {
            com.android.tools.r8.graph.I2 i2B = this.a.b();
            if (i2B == null) {
                return null;
            }
            return Collections.singletonList(i2B);
        }

        @Override // com.android.tools.r8.shaking.F2
        public final Iterable c() {
            return this.a.c();
        }

        @Override // com.android.tools.r8.shaking.F2
        public final int e() {
            return 1;
        }

        @Override // com.android.tools.r8.shaking.F2
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return Objects.equals(this.a, ((b) obj).a);
        }

        @Override // com.android.tools.r8.shaking.F2
        public final int hashCode() {
            return Objects.hash(this.a);
        }

        @Override // com.android.tools.r8.shaking.F2
        public final void a(StringBuilder sb) {
            sb.append(this.a.toString());
        }

        @Override // com.android.tools.r8.shaking.F2
        public final boolean a(com.android.tools.r8.graph.I2 i2) {
            return this.a.b(i2);
        }

        @Override // com.android.tools.r8.shaking.F2
        public final F2 a(com.android.tools.r8.graph.B1 b1) {
            return new b(this.a.a(b1));
        }

        @Override // com.android.tools.r8.shaking.F2
        public final void a(Consumer consumer) {
            consumer.accept(this.a);
        }
    }

    public F2 a(com.android.tools.r8.graph.B1 b1) {
        return this;
    }

    public final void a(final Consumer consumer, final Predicate predicate) {
        a(new Consumer() { // from class: uk4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                F2.a(predicate, consumer, (K3) obj);
            }
        });
    }

    public static /* synthetic */ void a(Predicate predicate, Consumer consumer, K3 k3) {
        if (predicate.test(k3)) {
            consumer.accept(k3);
        }
    }

    public static class a {
        public final ZS a = new ZS();

        public F2 a() {
            ZS zs = this.a;
            int i = zs.d;
            while (true) {
                int i2 = i - 1;
                if (i == 0) {
                    ZS zs2 = this.a;
                    if (zs2.d == 1) {
                        return new b((K3) AbstractC3179zC.a(new C2685tU(zs2.d, zs2.b)));
                    }
                    return new H2(new C2685tU(zs2.d, zs2.b));
                }
                if (zs.c[i2]) {
                    return new G2(this.a);
                }
                i = i2;
            }
        }

        public a a(boolean z, K3 k3) {
            this.a.a(k3, z);
            return this;
        }
    }
}
