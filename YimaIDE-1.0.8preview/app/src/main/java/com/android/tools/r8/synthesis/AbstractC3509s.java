package com.android.tools.r8.synthesis;

import com.android.tools.r8.FeatureSplit;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C2098md;
import com.android.tools.r8.synthesis.AbstractC3509s;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3509s {
    public static final /* synthetic */ boolean c = true;
    public final S.b a;
    public final C3502k b;

    public AbstractC3509s(S.b bVar, C3502k c3502k) {
        boolean z = c;
        if (!z && bVar == null) {
            x1f.a();
            throw null;
        }
        if (!z && c3502k == null) {
            x1f.a();
            throw null;
        }
        this.a = bVar;
        this.b = c3502k;
    }

    public abstract int a(AbstractC3509s abstractC3509s, com.android.tools.r8.utils.structural.t tVar);

    /* JADX WARN: Code duplicated, block: B:49:0x00ab  */
    public final int a(AbstractC3509s abstractC3509s, boolean z, AbstractC3148ys abstractC3148ys, C2098md c2098md) {
        com.android.tools.r8.utils.structural.t tVar;
        int iCompareTo;
        int iCompareTo2;
        int iCompareTo3 = this.a.compareTo(abstractC3509s.a);
        if (iCompareTo3 != 0) {
            return iCompareTo3;
        }
        final I2 type = e().getType();
        final I2 type2 = abstractC3509s.e().getType();
        if (!this.a.g()) {
            return type.compareTo(type2);
        }
        if (z && (iCompareTo2 = this.b.compareTo(abstractC3509s.b)) != 0) {
            return iCompareTo2;
        }
        FeatureSplit featureSplit = this.b.e;
        FeatureSplit featureSplit2 = abstractC3509s.b.e;
        if (featureSplit == featureSplit2) {
            if (abstractC3148ys.n()) {
                final I2 i2A = abstractC3148ys.a(type);
                final I2 i2A2 = abstractC3148ys.a(type2);
                if (type.b(i2A) || type2.b(i2A2)) {
                    tVar = new com.android.tools.r8.utils.structural.t() { // from class: w8i
                        @Override // com.android.tools.r8.utils.structural.t
                        public final I2 a(I2 i2) {
                            return AbstractC3509s.a(type2, i2A, i2A2, type, i2);
                        }
                    };
                } else {
                    tVar = null;
                }
            } else {
                tVar = null;
            }
            if (tVar == null) {
                tVar = new com.android.tools.r8.utils.structural.t() { // from class: y8i
                    @Override // com.android.tools.r8.utils.structural.t
                    public final I2 a(I2 i2) {
                        return AbstractC3509s.a(type2, type, i2);
                    }
                };
            }
            return a(abstractC3509s, tVar);
        }
        c2098md.getClass();
        boolean z2 = C2098md.c;
        if (!z2 && featureSplit == null) {
            x1f.a();
            return 0;
        }
        if (!z2 && featureSplit2 == null) {
            x1f.a();
            return 0;
        }
        if (featureSplit == featureSplit2) {
            iCompareTo = 0;
        } else if (featureSplit.isBase()) {
            iCompareTo = 1;
        } else {
            iCompareTo = featureSplit2.isBase() ? -1 : ((String) c2098md.b.get(featureSplit)).compareTo((String) c2098md.b.get(featureSplit2));
        }
        if (c || iCompareTo != 0) {
            return iCompareTo;
        }
        x1f.a();
        return 0;
    }

    public abstract void a(com.android.tools.r8.utils.structural.m mVar, com.android.tools.r8.utils.structural.t tVar);

    public r b() {
        return null;
    }

    public Z c() {
        return null;
    }

    public final C3502k d() {
        return this.b;
    }

    public abstract E0 e();

    public final S.b f() {
        return this.a;
    }

    public boolean g() {
        return this instanceof C3507p;
    }

    public boolean h() {
        return this instanceof O;
    }

    public abstract boolean i();

    public abstract a0 j();

    public static /* synthetic */ I2 a(I2 i2, I2 i3, I2 i4, I2 i5, I2 i6) {
        return (I2.a(i6, i2) || I2.a(i6, i3) || I2.a(i6, i4)) ? i5 : i6;
    }

    public static I2 a(I2 i2, I2 i3, I2 i4) {
        i2.getClass();
        return I2.a(i2, i4) ? i3 : i4;
    }
}
