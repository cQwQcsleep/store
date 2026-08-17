package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.C2753uC;
import com.android.tools.r8.internal.Ck0;
import com.android.tools.r8.internal.EX;
import com.android.tools.r8.shaking.H2;
import com.android.tools.r8.shaking.K3;
import defpackage.kw5;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H2 extends F2 {
    public final AbstractC0551Hu a;

    public H2(Collection collection) {
        this.a = AbstractC0551Hu.a(collection);
    }

    @Override // com.android.tools.r8.shaking.F2
    public final List a() {
        if (this.a.stream().allMatch(new Predicate() { // from class: e26
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return H2.b((K3) obj);
            }
        })) {
            return (List) this.a.stream().map(new Function() { // from class: f26
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((K3) obj).b();
                }
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override // com.android.tools.r8.shaking.F2
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final H2 a(final com.android.tools.r8.graph.B1 b1) {
        return new H2((Collection) this.a.stream().map(new Function() { // from class: h26
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((K3) obj).a(b1);
            }
        }).collect(Collectors.toList()));
    }

    @Override // com.android.tools.r8.shaking.F2
    public final Iterable c() {
        return C2753uC.b(this.a, new kw5());
    }

    @Override // com.android.tools.r8.shaking.F2
    public final int e() {
        return this.a.size();
    }

    @Override // com.android.tools.r8.shaking.F2
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || H2.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.a, ((H2) obj).a);
    }

    @Override // com.android.tools.r8.shaking.F2
    public final int hashCode() {
        return Objects.hash(this.a);
    }

    public static /* synthetic */ boolean b(K3 k3) {
        return k3.b() != null;
    }

    @Override // com.android.tools.r8.shaking.F2
    public final void a(StringBuilder sb) {
        Ck0 it = this.a.iterator();
        boolean z = true;
        while (it.hasNext()) {
            K3 k3 = (K3) it.next();
            if (!z) {
                sb.append(',');
            }
            sb.append(k3);
            z = false;
        }
    }

    @Override // com.android.tools.r8.shaking.F2
    public final boolean a(final com.android.tools.r8.graph.I2 i2) {
        return AbstractC3179zC.b(this.a, new EX() { // from class: g26
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return ((K3) obj).b(i2);
            }
        });
    }

    @Override // com.android.tools.r8.shaking.F2
    public final void a(Consumer consumer) {
        this.a.forEach(consumer);
    }
}
