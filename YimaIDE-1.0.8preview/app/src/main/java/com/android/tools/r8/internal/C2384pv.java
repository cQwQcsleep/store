package com.android.tools.r8.internal;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2384pv extends AbstractC2469qv {
    public Object[] c;
    public int d;
    public int e;
    public int f;

    public C2384pv(C2384pv c2384pv) {
        super(c2384pv);
        Object[] objArr = c2384pv.c;
        this.c = objArr == null ? null : (Object[]) objArr.clone();
        this.d = c2384pv.d;
        this.e = c2384pv.e;
        this.f = c2384pv.f;
    }

    public final void a(int i) {
        int length;
        int i2;
        Object[] objArr = this.c;
        if (objArr == null) {
            length = AbstractC2554rv.j(i);
            this.c = new Object[length];
        } else {
            if (i <= this.e || objArr.length >= 1073741824) {
                return;
            }
            length = objArr.length * 2;
            Object[] objArr2 = this.a;
            int i3 = this.b;
            Object[] objArr3 = new Object[length];
            int i4 = length - 1;
            for (int i5 = 0; i5 < i3; i5++) {
                Object obj = objArr2[i5];
                Objects.requireNonNull(obj);
                int iA = AbstractC1189bt.a(obj.hashCode());
                while (true) {
                    i2 = iA & i4;
                    if (objArr3[i2] == null) {
                        break;
                    } else {
                        iA++;
                    }
                }
                objArr3[i2] = obj;
            }
            this.c = objArr3;
        }
        this.d = AbstractC2410qA.a(length, RoundingMode.UNNECESSARY) * 13;
        this.e = (int) (((double) length) * 0.7d);
    }

    @Override // com.android.tools.r8.internal.AbstractC2469qv
    public final AbstractC2469qv b() {
        return new C2384pv(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC2469qv
    public final AbstractC2469qv c() {
        int i;
        if (this.c != null) {
            int iJ = AbstractC2554rv.j(this.b);
            int i2 = 0;
            if (iJ * 2 < this.c.length) {
                Object[] objArr = this.a;
                int i3 = this.b;
                Object[] objArr2 = new Object[iJ];
                int i4 = iJ - 1;
                for (int i5 = 0; i5 < i3; i5++) {
                    Object obj = objArr[i5];
                    Objects.requireNonNull(obj);
                    int iA = AbstractC1189bt.a(obj.hashCode());
                    while (true) {
                        i = iA & i4;
                        if (objArr2[i] == null) {
                            break;
                        }
                        iA++;
                    }
                    objArr2[i] = obj;
                }
                this.c = objArr2;
                this.d = AbstractC2410qA.a(iJ, RoundingMode.UNNECESSARY) * 13;
                this.e = (int) (((double) iJ) * 0.7d);
            }
            Object[] objArr3 = this.c;
            int iA2 = AbstractC2410qA.a(objArr3.length, RoundingMode.UNNECESSARY) * 13;
            int length = objArr3.length - 1;
            int i6 = 0;
            while (i2 < objArr3.length) {
                if (i2 != i6 || objArr3[i2] != null) {
                    int i7 = i2 + iA2;
                    for (int i8 = i7 - 1; i8 >= i6; i8--) {
                        if (objArr3[i8 & length] == null) {
                            i6 = i7;
                            i2 = i8 + 1;
                        }
                    }
                    return new C2298ov(this);
                }
                i6 = i2 + iA2;
                if (objArr3[(i6 - 1) & length] != null) {
                    i6 = i2 + 1;
                }
                i2 = i6;
            }
        }
        return this;
    }

    public C2384pv(int i) {
        super(i);
        this.c = null;
        this.d = 0;
        this.e = 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC2469qv
    public final AbstractC2554rv a() {
        int i = this.b;
        if (i != 0) {
            Object[] objArrCopyOf = this.a;
            if (i != 1) {
                if (i != objArrCopyOf.length) {
                    objArrCopyOf = Arrays.copyOf(objArrCopyOf, i);
                }
                int i2 = this.f;
                Object[] objArr = this.c;
                Objects.requireNonNull(objArr);
                return new W40(i2, this.c.length - 1, objArrCopyOf, objArr);
            }
            Object obj = objArrCopyOf[0];
            Objects.requireNonNull(obj);
            int i3 = AbstractC2554rv.c;
            return new Cc0(obj);
        }
        int i4 = AbstractC2554rv.c;
        return W40.j;
    }

    @Override // com.android.tools.r8.internal.AbstractC2469qv
    public final AbstractC2469qv a(Object obj) {
        obj.getClass();
        if (this.c == null) {
            if (this.b == 0) {
                b(obj);
                return this;
            }
            a(this.a.length);
            Object obj2 = this.a[0];
            this.b--;
            return c(obj2).a(obj);
        }
        return c(obj);
    }

    public final AbstractC2469qv c(Object obj) {
        Objects.requireNonNull(this.c);
        int iHashCode = obj.hashCode();
        int iA = AbstractC1189bt.a(iHashCode);
        int length = this.c.length - 1;
        for (int i = iA; i - iA < this.d; i++) {
            int i2 = i & length;
            Object obj2 = this.c[i2];
            if (obj2 == null) {
                b(obj);
                this.c[i2] = obj;
                this.f += iHashCode;
                a(this.b);
                return this;
            }
            if (obj2.equals(obj)) {
                return this;
            }
        }
        return new C2298ov(this).a(obj);
    }
}
