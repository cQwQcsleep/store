package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C1693ho;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ho, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1693ho extends AbstractC1522fo {
    public static final /* synthetic */ boolean b = true;
    public final com.android.tools.r8.graph.I2[] a;

    public C1693ho(com.android.tools.r8.graph.I2[] i2Arr) {
        if (b || Arrays.stream(i2Arr).noneMatch(new Predicate() { // from class: q4h
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.isNull((I2) obj);
            }
        })) {
            this.a = i2Arr;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1522fo
    public final AbstractC1522fo a(final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2) {
        com.android.tools.r8.graph.I2[] i2Arr = (com.android.tools.r8.graph.I2[]) R3.a((Object[]) this.a, new Function() { // from class: r4h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C1693ho.a(abstractC3148ys, abstractC3148ys2, (I2) obj);
            }
        }, (Object[]) com.android.tools.r8.graph.I2.h);
        com.android.tools.r8.graph.I2 i2 = i2Arr[0];
        for (com.android.tools.r8.graph.I2 i3 : i2Arr) {
            if (i3 != i2) {
                return new C1693ho(i2Arr);
            }
        }
        return new C1607go(i2Arr[0]);
    }

    @Override // com.android.tools.r8.internal.AbstractC1522fo
    public final boolean equals(Object obj) {
        if (obj instanceof C1693ho) {
            return Arrays.equals(this.a, ((C1693ho) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.a);
    }

    @Override // com.android.tools.r8.internal.AbstractC1522fo
    public final com.android.tools.r8.graph.I2 a(int i) {
        if (b || (i >= 0 && i < this.a.length)) {
            return this.a[i];
        }
        x1f.a();
        return null;
    }

    public static /* synthetic */ com.android.tools.r8.graph.I2 a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.I2 i2C = abstractC3148ys.c(abstractC3148ys2, i2);
        if (b || i2C.M0()) {
            return i2C;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1522fo
    public final boolean a(C0333y c0333y) {
        if (b || this.a.length > 0) {
            return c0333y.R().b(this.a[0]);
        }
        x1f.a();
        return false;
    }
}
